package br.com.kproj.salesman.administration.users.view.validators;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

import static br.com.kproj.salesman.infrastructure.helpers.MultipartFileUtils.safe;

@Component
public class AvatarValidator {

	private static final Integer MAX_SIZE = 1024;
	private final Set<String> mimeTypes = Sets.newHashSet("image/png", "image/jpeg", "image/gif");


    public void validate(Object target, Set<String> errors) {
    	MultipartFile file = (MultipartFile) target;
    	
    	try {
			byte[] bytes = safe(file).getBytes();
			
			if (bytes == null) {
				return;
			}
			
			long size = safe(file).getSize();
			if (size > MAX_SIZE) {
				errors.add("product.avatar.invalid.size");
			}
			
			String contentType = safe(file).getContentType();
			
			if (!mimeTypes.contains(contentType)) {
				errors.add("product.avatar.invalid.mimetype");
			}
		} catch (IOException e) {
			errors.add("product.avatar.is.invalid");
		}
    	
    }

}
