package br.com.kproj.salesman.register.infrastructure.validators;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

import static br.com.kproj.salesman.infrastructure.helpers.MultipartFileUtils.safe;

@Component
public class AvatarValidator implements Validator {

	private static final Integer MAX_SIZE = 1024;
	private final Set<String> mimeTypes = Sets.newHashSet("image/png", "image/jpeg", "image/gif");
    

    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	MultipartFile file = (MultipartFile) target;
    	
    	try {
			byte[] bytes = safe(file).getBytes();
			
			if (bytes == null) {
				return;
			}
			
			long size = safe(file).getSize();
			if (size > MAX_SIZE) {
				errors.rejectValue("avatarFile", "product.avatar.invalid.size");
			}
			
			String contentType = safe(file).getContentType();
			
			if (!mimeTypes.contains(contentType)) {
				errors.rejectValue("avatarFile", "product.avatar.invalid.mimetype");
			}
					
		} catch (IOException e) {
			errors.rejectValue("avatarFile","product.avatar.is.invalid");
		}
    	
    }

}
