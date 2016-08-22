package br.com.kproj.salesman.register.view.dto;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.helpers.ReflectionsHelper;

public class UserVO extends UserEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1224149976031181831L;
	
	private MultipartFile avatarFile;

	public MultipartFile getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(MultipartFile avatarFile) {
		this.avatarFile = avatarFile;
	}
	
	public UserEntity getUser() {
		UserEntity user = new UserEntity();
		ReflectionsHelper.copyProperties(user, this);
		try {
			user.setAvatar(avatarFile != null ? avatarFile.getBytes(): null);
		} catch (IOException e) {
			
		}
		return user;
		
	}
}
