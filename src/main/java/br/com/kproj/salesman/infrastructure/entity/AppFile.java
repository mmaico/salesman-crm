package br.com.kproj.salesman.infrastructure.entity;

import br.com.kproj.salesman.infrastructure.helpers.files.MimetypeUtils;

import javax.persistence.*;
import java.util.Date;


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

	private Long size;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
	
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

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getExtension() {
        return MimetypeUtils.findExtension(this.mimeType);
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
