package br.com.kproj.salesman.infrastructure.entity;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="app_file")
public class AppFile extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
	
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

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "." + FilenameUtils.getExtension(this.getOriginalName());
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

    public Boolean isValid() {
        AppFile nullObject = new AppFile();
        return EqualsBuilder.reflectionEquals(this, nullObject);
    }
		
}
