package br.com.kproj.salesman.infrastructure.helpers.files;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isBlank;




public class MimetypeUtils {

	private static Map<String, String> mimes = new HashMap<String, String>();
	
	static {
		mimes.put("application/vnd.ms-excel", ".xls");
		mimes.put("application/msexcel", ".xls");
		mimes.put("application/x-msexcel", ".xls");
		mimes.put("application/x-ms-excel", ".xls");
		mimes.put("application/x-excel", ".xls");
		mimes.put("application/x-dos_ms_excel", ".xls");
		mimes.put("application/xls", ".xls");
		mimes.put("application/x-xls", ".xls");
		mimes.put("application/vnd.ms-excel", ".xls");
		mimes.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx");
		mimes.put("image/jpg", ".jpg");
		mimes.put("image/png", ".png");
		mimes.put("image/gif", ".gif");
		
		
	}
	
	public static boolean existExtension(String mimeType) {
		
		String extension = mimes.get(mimeType);
		
		return isBlank(extension) ? FALSE : TRUE;
	}
	
	public static String findExtension(String mimeType) {
		
		String extension = mimes.get(mimeType);
		
		return isBlank(extension) ? EMPTY : extension;
	}
	
	
}
