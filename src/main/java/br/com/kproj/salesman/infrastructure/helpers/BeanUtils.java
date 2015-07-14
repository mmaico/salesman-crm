package br.com.kproj.salesman.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.apache.commons.beanutils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Map.Entry;

/*
    * Copyright 2001-2004 The Apache Software Foundation.
    *
    * Licensed under the Apache License, Version 2.0 (the "License");
    * you may not use this file except in compliance with the License.
    * You may obtain a copy of the License at
    *
    *      http://www.apache.org/licenses/LICENSE-2.0
    *
    * Unless required by applicable law or agreed to in writing, software
    * distributed under the License is distributed on an "AS IS" BASIS,
    * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    * See the License for the specific language governing permissions and
    * limitations under the License.
*/
public class BeanUtils extends BeanUtilsBean {
	private static Log log = LogFactory.getLog(BeanUtils.class);
	
	private List<Object> tops = new ArrayList<Object>();

    public static BeanUtils create() {
        return new BeanUtils();
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void copyProperties(Object dest, Object orig) {
		
		
		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified for origin " + orig);
		}

		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}
		tops.add(orig);
	
		
		if (log.isDebugEnabled()) {
			log.debug("BeanUtils.copyProperties(" + dest + ", " + orig	+ ")");
		}

		if (orig instanceof DynaBean) {
			DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass().getDynaProperties();

			for (int i = 0; i < origDescriptors.length; ++i) {
				String name = origDescriptors[i].getName();
				if (getPropertyUtils().isWriteable(dest, name)) {
					Object value = ((DynaBean) orig).get(name);
					try {
						copyProperty(dest, name, value);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
					}
				}
			}
		} else if (orig instanceof Map) {
			Iterator names = ((Map) orig).keySet().iterator();
			while (names.hasNext()) {
				String name = (String) names.next();
				if (getPropertyUtils().isWriteable(dest, name)) {
					Object value = ((Map) orig).get(name);
					try {
						copyProperty(dest, name, value);
					} catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
					}
				}
			}
		} else {
			PropertyDescriptor[] origDescriptors = getPropertyUtils().getPropertyDescriptors(orig);

			for (int i = 0; i < origDescriptors.length; ++i) {
				String name = origDescriptors[i].getName();
				
				if ("class".equals(name)) {
					continue;
				}
				
				if (orig instanceof Identifiable) {
					if (!((Identifiable) orig).getFields().contains(name)) {
						continue;
					}
				}
				
				if ((!(getPropertyUtils().isReadable(orig, name)))
						|| (!(getPropertyUtils().isWriteable(dest, name))))
					continue;

				try {
					Object value = getPropertyUtils().getSimpleProperty(orig,name);
					
					if (value instanceof Identifiable) {
												
						if (tops.contains(value))
							continue;
						
						Identifiable valueBaseComponent = (Identifiable) value;
												
						Object valueDest = getPropertyUtils().getSimpleProperty(dest,name);
						
						if (valueDest != null && (valueBaseComponent.getId() == null || 
								valueBaseComponent.getId().equals(((Identifiable)valueDest).getId()))) {
							copyProperties(valueDest, value);
						} else {
							org.apache.commons.beanutils.BeanUtils.copyProperty(dest, name, value);
						}
						
					} else if (value instanceof List){
						List listDest = (List) getPropertyUtils().getSimpleProperty(dest,name);
						List listOrig = (List) value;
						
						for (Object item : listOrig) {
							
							if (((Identifiable)item).getId() != null && contains(item, listDest) != null) {
								Object objectItemDest = contains(item, listDest);
								copyProperties(objectItemDest, item);
							} else {
								listDest.add(item);
							}
						}
						
					} else {
						
						Identifiable abEntity = (Identifiable) orig;
						
						copyProperty(dest, name, value, abEntity);
					}
				} catch (NoSuchMethodException e) {
					
				} catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
				}
			}
		}
	}

	private Object contains(Object item, List listDest) {
		
		for (Object object : listDest) {
			if (object instanceof Identifiable) {
				if (((Identifiable)object).getId().equals(((Identifiable)item).getId())) {
					return object;
				}
			}
		}
		
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void copyProperty(Object dest, String fieldName, Object value, Identifiable orig) throws IllegalAccessException, InvocationTargetException {
		if (log.isTraceEnabled()) {
			createLogCopyProperty(dest, fieldName, value);
		}
		
		if (!orig.getFields().contains(fieldName)) {
			return;
		}	
		
		Object target = dest;
		int delim = fieldName.lastIndexOf(46);
		if (delim >= 0) {
			try {
				target = getPropertyUtils().getProperty(dest,
						fieldName.substring(0, delim));
			} catch (NoSuchMethodException e) {
				return;
			}
			fieldName = fieldName.substring(delim + 1);
			if (log.isTraceEnabled()) {
				log.trace("    Target bean = " + target);
				log.trace("    Target name = " + fieldName);
			}

		}

		String propName = null;
		Class type = null;
		int index = -1;
		String key = null;

		propName = fieldName;
		int i = propName.indexOf(91);
		if (i >= 0) {
			int k = propName.indexOf(93);
			try {
				index = Integer.parseInt(propName.substring(i + 1, k));
			} catch (NumberFormatException e) {
			}
			propName = propName.substring(0, i);
		}
		int j = propName.indexOf(40);
		if (j >= 0) {
			int k = propName.indexOf(41);
			try {
				key = propName.substring(j + 1, k);
			} catch (IndexOutOfBoundsException e) {
			}
			propName = propName.substring(0, j);
		}

		if (target instanceof DynaBean) {
			DynaClass dynaClass = ((DynaBean) target).getDynaClass();
			DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
			if (dynaProperty == null) {
				return;
			}
			type = dynaProperty.getType();
		} else {
			PropertyDescriptor descriptor = null;
			try {
				descriptor = getPropertyUtils().getPropertyDescriptor(target, fieldName);

				if (descriptor == null)
					return;
			} catch (NoSuchMethodException e) {
				return;
			}
			type = descriptor.getPropertyType();
			if (type == null) {
				if (log.isTraceEnabled()) {
					log.trace("    target type for property '" + propName
							+ "' is null, so skipping ths setter");
				}

				return;
			}
		}
		if (log.isTraceEnabled()) {
			log.trace("    target propName=" + propName + ", type=" + type
					+ ", index=" + index + ", key=" + key);
		}

		if (index >= 0) {
			Converter converter = getConvertUtils().lookup(
					type.getComponentType());
			if (converter != null) {
				log.trace("        USING CONVERTER " + converter);
				value = converter.convert(type, value);
			}
			try {
				getPropertyUtils().setIndexedProperty(target, propName, index,
						value);
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		} else if (key != null) {
			try {
				getPropertyUtils().setMappedProperty(target, propName, key,
						value);
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		} else {
			Converter converter = getConvertUtils().lookup(type);
			if (converter != null) {
				log.trace("        USING CONVERTER " + converter);
				value = converter.convert(type, value);
			}
			try {
				if (value instanceof Map) {
					Map mapDest = (Map) getPropertyUtils().getSimpleProperty(dest, fieldName);
					
					if (mapDest != null) {
						Map mapOrig = (Map)value;
						Set entrySet = mapOrig.entrySet();
						for (Object object : entrySet) {
							Entry entryOrig = (Entry) object;
							
							if (entryOrig.getValue() instanceof Identifiable) {
								if (mapDest.containsKey(entryOrig.getKey())) {
									Object destValue = mapDest.get(entryOrig.getKey());
									
									new BeanUtils().copyProperties(destValue, entryOrig.getValue());
								} else {
									mapDest.put(entryOrig.getKey(), entryOrig.getValue());
								}
							} else {
								mapDest.put(entryOrig.getKey(), entryOrig.getValue());
							}
							
							
							
						}
					} else {
						getPropertyUtils().setSimpleProperty(target, propName, value);
					}
				} else {
					getPropertyUtils().setSimpleProperty(target, propName, value);
				}	
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		}
	}

	private void createLogCopyProperty(Object bean, String name, Object value) {
		StringBuffer sb = new StringBuffer("  copyProperty(");
		sb.append(bean);
		sb.append(", ");
		sb.append(name);
		sb.append(", ");
		if (value == null) {
			sb.append("<NULL>");
		} else if (value instanceof String) {
			sb.append((String) value);
		} else if (value instanceof String[]) {
			String[] values = (String[]) value;
			sb.append('[');
			for (int i = 0; i < values.length; ++i) {
				if (i > 0) {
					sb.append(',');
				}
				sb.append(values[i]);
			}
			sb.append(']');
		} else {
			sb.append(value.toString());
		}
		sb.append(')');
		log.trace(sb.toString());
	}
	
		
		
}
