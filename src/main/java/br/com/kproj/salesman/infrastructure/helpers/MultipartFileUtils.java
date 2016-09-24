package br.com.kproj.salesman.infrastructure.helpers;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MultipartFileUtils {

	public static MultipartFile safe(MultipartFile file) {
		return file == null ? new MultiPartFileSafeNull() : file; 
	}
	
	public static class MultiPartFileSafeNull implements MultipartFile {

		@Override
		public byte[] getBytes() throws IOException {
			return null;
		}

		@Override
		public String getContentType() {
			return null;
		}

		@Override
		public InputStream getInputStream() throws IOException {
			return null;
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public String getOriginalFilename() {
			return null;
		}

		@Override
		public long getSize() {
			return 0l;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public void transferTo(File arg0) throws IOException,
				IllegalStateException {
			
		}
		
	}
}
