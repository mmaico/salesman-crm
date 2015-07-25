package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="app_file")
public class AppFile extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="original_name")
	private String originalName;
	
	@Column(name="mime_type")
	private String mimeType;
	
	@Column(name="size")
	private Long size;
	
	@Transient
	private byte[] file;
	
	public AppFile(){}
	
	public AppFile(Long id) {
		this.setId(id);
	}
	
	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getExtension() {
		
		if (originalName == null || originalName.isEmpty())
			return "notDefined";
		
		String[] split = originalName.split("\\.");
		
		if (split.length  < 2) {
			return "notDefined";
		}
		
		return split[split.length -1];
	}

	
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
	
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
		
}
