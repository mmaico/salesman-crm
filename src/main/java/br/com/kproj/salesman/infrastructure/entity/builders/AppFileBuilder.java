package br.com.kproj.salesman.infrastructure.entity.builders;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.apache.commons.lang.StringUtils.isBlank;


public class AppFileBuilder extends AbstractBuilder<AppFileEntity> {

	private AppFileEntity appfile = new AppFileEntity();

    public AppFileBuilder() {}

    public AppFileBuilder(Long id) {
        appfile.setId(id);
    }
	public AppFileBuilder withFile(byte[] file) {
		this.appfile.setFile(file);
		return this;
	}
	
	public AppFileBuilder withMimeType(String mimeType) {
		this.appfile.setMimeType(mimeType);
		return this;
	}
	
	public AppFileBuilder withOriginalName(String name) {
		this.appfile.setOriginalName(name);
		return this;
	}
	
	public AppFileBuilder withSize(Integer size) {
		this.appfile.setSize(size);
		return this;
	}

    public AppFileBuilder withWidth(Integer width) {
        this.appfile.setWidth(width);
        return this;
    }

    public AppFileBuilder withHeight(Integer height) {
        this.appfile.setHeight(height);
        return this;
    }

    public AppFileBuilder addDimensionsIfImage() {

        if (!isBlank(appfile.getMimeType()) && appfile.getFile() != null && appfile.getMimeType().startsWith("image/")) {
            try {
                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(appfile.getFile()));
                if (bufferedImage != null) {
                    appfile.setWidth(bufferedImage.getWidth());
                    appfile.setHeight(bufferedImage.getHeight());
                }

            } catch (IOException e) {}
        }

        return this;
    }

    public Boolean hasFile() {
        return this.appfile.getFile() != null && this.appfile.getFile().length > 0;
    }


    public static AppFileBuilder create(Long id) {
        return new AppFileBuilder(id);
    }

    public static AppFileBuilder create(MultipartFile multipart) throws IOException {
        return null;
//        return new AppFileBuilder().withFile(safe(multipart).getBytes())
//                    .withMimeType(safe(multipart).getContentType())
//                    .withOriginalName(safe(multipart).getOriginalFilename())
//                    .withSize(safe(multipart).getSize());
    }

	public AppFileEntity build() {
		return this.appfile;
	}


	
}
