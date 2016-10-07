package br.com.kproj.salesman.infrastructure.http.response.handler;



import br.com.kproj.salesman.infrastructure.http.response.handler.custom.MappingJackson2HttpMessageConverterCustom;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Configuration
public class ResponseHandlerConfig extends WebMvcConfigurerAdapter {

  private final Map<String, String> parameterMap = Collections.singletonMap("charset", StandardCharsets.UTF_8.displayName());
  private final MediaType mediaTypeDefault = new MediaType("application", "json", parameterMap);

    
  @Override
  public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
    final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    final MappingJackson2HttpMessageConverterCustom jacksonConverter = new MappingJackson2HttpMessageConverterCustom(builder.build());
    
    final ObjectMapper objectMapper = jacksonConverter.getObjectMapper();

    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    jacksonConverter.setSupportedMediaTypes(Arrays.asList(mediaTypeDefault, MediaType.APPLICATION_JSON));

    converters.add(jacksonConverter);
    super.configureMessageConverters(converters);    
  }
  
  @Override
  public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
    configurer.favorPathExtension(false)
                .favorParameter(true)
                .ignoreAcceptHeader(false)
                .useJaf(false)
                .defaultContentType(mediaTypeDefault)
                .mediaType("application/json", MediaType.APPLICATION_JSON);
    super.configureContentNegotiation(configurer);
  }
}
