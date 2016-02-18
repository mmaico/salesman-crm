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


	public Set<String> hasFileAndRequiredInfos(AppFile target) {

		Set<String> errors = new HashSet<>();

		if (isBlank(target.getOriginalName())) {
			errors.add("app.file.error.name");
		}

		if (isBlank(target.getMimeType())) {
			errors.add("app.file.error.mimetype");
		}

		if (target.getSize() == null || target.getSize() < 1) {
			errors.add("app.file.error.size");
		}

		if (target.getFile() == null || target.getFile().length == 0) {
			errors.add("app.file.error.file");
		}

		return errors;

	}

}
