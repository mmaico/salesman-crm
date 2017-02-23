package br.com.kproj.salesman.medias.media.infrastructure.repository.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws.s3.S3Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AWSS3Route extends RouteBuilder {

    @Value("${storage.linkTemplate}")
    private String linkTemplate;
    @Value("${storage.serviceTemplate}")
    private String serviceTemplate;

    @Value("${storage.credential.client}")
    private String client;
    @Value("${storage.credential.secret}")
    private String secret;

    @Value("${storage.region}")
    private String region;
    @Value("${storage.category}")
    private String category;
    @Value("${storage.bucket}")
    private String bucket;

    @Override
    public void configure() throws Exception {

        from("direct:store-" + category)
            .setHeader(S3Constants.CONTENT_TYPE, simple("${in.header.FileType}"))
            .setHeader(S3Constants.CONTENT_LENGTH, simple("${in.body.available}"))
            .setHeader(S3Constants.KEY, simple(category + "/" + "${in.header.FileName}"))

            .to(String.format(serviceTemplate, bucket, region, client, secret))
            .setBody(simple(String.format(linkTemplate, region, bucket, category, "${in.header.FileName}")));
    }
}
