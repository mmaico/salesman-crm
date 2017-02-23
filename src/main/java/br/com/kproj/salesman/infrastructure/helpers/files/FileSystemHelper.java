package br.com.kproj.salesman.infrastructure.helpers.files;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Responsavel por manipular arquivos no filesystem.
 *
 * Uma entidade deve ser anotada como @Media(name="card")
 * e o atributo que representa os arquivos deve ser anotada como
 * @MediaStorage(name="image")
 *
 * Exemplo:
 *
 *   @Media("card")
 *   class Card {
 *
 *       @MediaStorage(name="image")
 *       private List<Appfile> images;
 *   }
 *
 * 
 * */
@Component
public class FileSystemHelper {

	static Logger logger = Logger.getLogger(FileSystemHelper.class);
	
	private @Autowired
    FileSystemPathHelper pathUtils;
	
	public boolean mkdirs(String fullPath) {
		File file = createFile(fullPath);
		return file.mkdirs();
	}

	File createFile(String fullPath) {
		return new File(fullPath);
	}
	
	public byte[] getFile(Identifiable base, AppFileEntity appFileEntity) {
		try {
			String urlFile = pathUtils.getPathFile(base, appFileEntity);
			
			FileInputStream fileInput = new FileInputStream(createFile(urlFile));
			
			return IOUtils.toByteArray(fileInput);
		} catch (IOException e) {
			logger.error(e);
			return new byte[]{};
		} 
	}
	
	public void writeFile(String fullPath, byte[] file) {
		try {
			write(file, new FileOutputStream(fullPath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	void write(byte[] file, FileOutputStream out) throws IOException {
		IOUtils.write(file, out);
	}
	
	public String getBasePath(Identifiable object) {
		return this.pathUtils.mountBasePathFile(object);
	}
	
	public String getPathFile(Identifiable base, AppFileEntity appFileEntity) {
		return this.pathUtils.getPathFile(base, appFileEntity);
	}
}
