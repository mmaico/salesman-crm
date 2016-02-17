package br.com.kproj.salesman.infrastructure.validators;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang.StringUtils.isBlank;



@Component("appFileValidator")
public class AppFileValidator {


	public Boolean hasFileAndRequiredInfos(AppFile target) {

		if (isBlank(target.getOriginalName())) {
			throw new ValidationException(Sets.newHashSet("app.file.error.name"));
		}
		
		if (isBlank(target.getMimeType())) {
			throw new ValidationException(Sets.newHashSet("app.file.error.mimetype"));
		}
		
		if (target.getSize() == null || target.getSize() < 1) {
			throw new ValidationException(Sets.newHashSet("app.file.error.size"));
		}
		
		if (target.getFile() == null || target.getFile().length == 0) {
			throw new ValidationException(Sets.newHashSet("app.file.error.file"));
		}
		
		return Boolean.TRUE;
	}

}
