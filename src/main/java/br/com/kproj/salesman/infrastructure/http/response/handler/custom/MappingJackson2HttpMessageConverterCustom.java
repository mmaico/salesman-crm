package br.com.kproj.salesman.infrastructure.http.response.handler.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;


public class MappingJackson2HttpMessageConverterCustom extends MappingJackson2HttpMessageConverter {


    private final Charset defaultCharset = Charset.defaultCharset();

    public MappingJackson2HttpMessageConverterCustom(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (object instanceof String) {
            Charset charset = this.getContentTypeCharset(outputMessage.getHeaders().getContentType());

            StreamUtils.copy(object.toString(), charset, outputMessage.getBody());
        } else {
            super.writeInternal(object, outputMessage);
        }
    }

    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        if (type.getTypeName().equals(String.class.getName())) {
            return IOUtils.toString(inputMessage.getBody());
        } else {
            return super.read(type, contextClass, inputMessage);
        }
    }

    private Charset getContentTypeCharset(MediaType contentType) {
        return contentType != null && contentType.getCharSet() != null ? contentType.getCharSet() : this.defaultCharset;
    }
}
