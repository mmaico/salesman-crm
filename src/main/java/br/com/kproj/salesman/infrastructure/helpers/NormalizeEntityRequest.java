package br.com.kproj.salesman.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.azeckoski.reflectutils.ReflectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Set;



@Component
public class NormalizeEntityRequest {

	private Log log = LogFactory.getLog(NormalizeEntityRequest.class);
	
	public void  doNestedReference(Identifiable entity) {
		try {
			doNestedReference(entity, null);
		} catch (Exception e) {
			log.info("Falha ao fazer um nested reference"+ entity, e);
		}
	}


	@SuppressWarnings("rawtypes")
	public void addFieldsToUpdate(Identifiable entity) {
		
		Set<String> keyParams = getRequest();
		
		for (String attr : keyParams) {
			 try {
                if (!attr.matches(".+\\..+")) {
                	entity.addFields(attr.trim().replaceAll("\\[.+\\]", ""));
                } else {
                	doObjectReference(entity, attr);
                	
                    int lastIndexOf = attr.lastIndexOf(".");
                    String prefix = attr.substring(0, lastIndexOf);
                    String fieldName = attr.substring(++lastIndexOf, attr.length());
                    
                    Object fieldsList = ReflectUtils.getInstance().getFieldValue(entity, prefix + ".fields");
                    
                    if (fieldsList instanceof Collection) {
                        ((Collection)fieldsList).add(fieldName.trim().replaceAll("\\[.+\\]", ""));
                    }
                }
	                
            } catch (Exception e) {}
		}
	}
	/**
	 * Adiciona referencia de objetos para atualizacao
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private void doObjectReference(Identifiable entity, String path) {
	
		int firstIndexOf = path.indexOf(".");
        String prefix = path.substring(0, firstIndexOf);
        String suffix = path.substring(firstIndexOf + 1, path.length());
		
        Collection fieldsList =(Collection) ReflectUtils.getInstance().getFieldValue(entity, "fields");
        fieldsList.add(prefix.replaceAll("\\[.+?\\]", ""));
        
        if (suffix.matches(".+\\..+")) {
        	Object object = ReflectUtils.getInstance().getFieldValue(entity, prefix);
        	if (object instanceof Identifiable) {
        		doObjectReference((Identifiable)object, suffix);
        	}
        }	
        
	}
	
	/**
	 * Metodo que faz referencia da Classe principal para as subclasses.
	 * Exemplo:
	 *   Company
	 *      contact
	 *      
	 *   Ele vai colocar uma referencia de company em contact para o hibernate fazer a referencia
	 *   no banco de dados.
	 */
	private void doNestedReference(Object target, Object parent) throws Exception  {


		List<Field> fields = ReflectionsHelper.getAllFields(target.getClass());
    
        for (Field field : fields) {
            
        	if (field.getName().equals("class")) continue;
        	
        	if (parent != null) {
                
                if (field.getType() != null && Identifiable.class.isAssignableFrom(field.getType()) && ((Identifiable) target).getId() == null) {

                    if (field.getType().isInstance(parent)) {
                        ReflectUtils.getInstance().setFieldValue(target, field.getName(), parent);
                        continue;
                    }
                }    
            }
            
            Object nestedObject = ReflectUtils.getInstance().getFieldValue(target, field.getName());
            
            if (nestedObject instanceof Identifiable) {
            	doNestedReference(nestedObject, target);
            } else if (nestedObject instanceof Iterable) {
                @SuppressWarnings("rawtypes")
                Iterable items = (Iterable)nestedObject;
                
                for (Object item : items) {
                    if (item instanceof Identifiable) {
                    	doNestedReference(item, target);
                    } else {
                        break;
                    }    
                }
            }
        }
    }
	
	@SuppressWarnings("rawtypes")
	Set getRequest() {
		HttpServletRequest request = 
				((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		
		return request.getParameterMap().keySet();
	}
	
	
}
