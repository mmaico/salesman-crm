package br.com.kproj.salesman.register.view.dto;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.MultipartFileUtils;

import com.google.common.collect.Sets;

public class AvatarVO {

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public byte[] get() {
		try {
			return MultipartFileUtils.safe(file).getBytes();
		} catch (IOException e) {
			throw new ValidationException(Sets.newHashSet("file.invalid"));
		}
	}
	
}
