package br.com.kproj.salesman.infrastructure.helpers.files;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.helpers.ReflectionsHelper;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.Media;
import br.com.kproj.salesman.infrastructure.helpers.files.annotations.MediaStorage;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.MirrorList;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;



@Component
public class FileSystemPathHelper {

	//@Value("${system.repository.storage}")
	private String basePath;
	
	public String mountBasePathFile(Identifiable object) {
		
		if (object == null)
			return basePath;
		
		Media media = new Mirror().on(object.getClass()).reflect().annotation(Media.class).atClass();
		
		MirrorList<Field> matching = ReflectionsHelper.findFields(object, MediaStorage.class);

		String fullPath = basePath +"/" + media.name();
		
		if (!matching.isEmpty()) {
			MediaStorage fileAnn = new Mirror().on(matching.get(0)).reflect().annotation(MediaStorage.class);
			
			fullPath = fullPath + "/"+ fileAnn.name() + "/";
		}
		
		return fullPath + object.getId();
	}
	
	public String getPathFile(Identifiable base, AppFile appFile) {
		
		String mountFilePath = mountBasePathFile(base);
		String fullPath =  mountFilePath;
		
		return fullPath + "/"+ appFile.getId() + appFile.getExtension();
	}
}
