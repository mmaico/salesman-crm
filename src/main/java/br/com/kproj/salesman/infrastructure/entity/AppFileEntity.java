package br.com.kproj.salesman.infrastructure.entity;

import org.apache.commons.io.FilenameUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name="app_file")
public class AppFileEntity extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="system_name")
    private String systemname;

	@Column(name="original_name")
	private String originalName;
	
	@Column(name="mime_type")
	private String mimeType;

	private Integer size;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lasMotification;

    private Integer width;

    private Integer height;

    @Column(name="cdn_url")
    private String cdnUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    @NotNull
    private StorageEntity storageEntity;
	
	@Transient
	private byte[] file;
	
	public AppFileEntity(){}
	
	public AppFileEntity(Long id) {
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
	
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Date getLasMotification() {
        return lasMotification;
    }

    public void setLasMotification(Date lasMotification) {
        this.lasMotification = lasMotification;
    }

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    public String getCdnUrl() {
        return cdnUrl;
    }

    public void setCdnUrl(String cdnUrl) {
        this.cdnUrl = cdnUrl;
    }

    public StorageEntity getStorageEntity() {
        return storageEntity;
    }

    public void setStorageEntity(StorageEntity storageEntity) {
        this.storageEntity = storageEntity;
    }
}
