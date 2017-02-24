package br.com.kproj.salesman.medias.media.infrastructure.repository;



import br.com.kproj.salesman.medias.media.domain.media.FileContentRaw;
import br.com.kproj.salesman.medias.media.infrastructure.helpers.ImageBase64InfoHelper;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ImageRepositoryS3 {

    private ProducerTemplate producerTemplate;

    @Autowired
    public ImageRepositoryS3(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }


    public S3Info store(FileContentRaw rawFile, String storage) {
        final String fileName = UUID.randomUUID().toString();
        final String type = ImageBase64InfoHelper.getType(rawFile.getFile());

        Map<String, Object> headers = new HashMap() {
            {
                put("FileName", fileName);
                put("FileType", type);
            }
        };
        ByteArrayInputStream inputStream = ImageBase64InfoHelper.decodeBase64(rawFile.getFile());

        final Object link = producerTemplate.requestBodyAndHeaders(
                "direct:store-".concat(storage), inputStream,  headers);

        return new S3Info(fileName, type, link.toString());
    }

}
