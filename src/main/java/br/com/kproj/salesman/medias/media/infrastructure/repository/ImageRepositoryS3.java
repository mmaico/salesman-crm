package br.com.kproj.salesman.medias.media.infrastructure.repository;



import br.com.kproj.salesman.medias.media.domain.media.FileContentRaw;
import br.com.kproj.salesman.medias.media.infrastructure.helpers.ImageBase64InfoHelper;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


    public String store(FileContentRaw imageRaw, String storage) {
        Map<String, Object> headers = new HashMap() {
            {
                put("FileName", UUID.randomUUID().toString());
                put("FileType", ImageBase64InfoHelper.getType(imageRaw.getFile()));
            }
        };
        ImageBase64InfoHelper.decodeBase64(imageRaw.getFile());
        final Object link = producerTemplate.requestBodyAndHeaders(
                "direct:store-".concat(storage), imageRaw.getFile(),  headers);

        return link.toString();
    }

}
