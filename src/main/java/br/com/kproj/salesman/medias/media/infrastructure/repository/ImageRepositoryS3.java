package br.com.kproj.salesman.medias.media.infrastructure.repository;



import br.com.kproj.salesman.medias.media.domain.FileContentRaw;
import br.com.kproj.salesman.medias.media.infrastructure.helpers.ImageBase64InfoHelper;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ImageRepositoryS3 {

    private static final String LOCAL_STORAGE = "sales-man";


    @Autowired
    private ProducerTemplate producerTemplate;


    public String store(FileContentRaw imageRaw) {
        Map<String, Object> headers = new HashMap() {
            {
                put("FileName", UUID.randomUUID().toString());
                put("FileType", ImageBase64InfoHelper.getType(imageRaw.getImage()));
            }
        };
        ImageBase64InfoHelper.decodeBase64(imageRaw.getImage());
        final Object link = producerTemplate.requestBodyAndHeaders(
                "direct:store-".concat(LOCAL_STORAGE), imageRaw.getImage(),  headers);

        return link.toString();
    }

}
