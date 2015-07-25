package br.com.kproj.salesman.register.view.dto;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.helpers.ReflectionsHelper;

public class UserVO extends User {

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
	
	public User getUser() {
		User user = new User();
		ReflectionsHelper.copyProperties(user, this);
		try {
			user.setAvatar(avatarFile != null ? avatarFile.getBytes(): null);
		} catch (IOException e) {
			
		}
		return user;
		
	}
}
