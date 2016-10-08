package br.com.kproj.salesman.infrastructure.http.response.handler;

import br.com.kproj.salesman.infrastructure.http.response.handler.annotation.ErrorResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.annotation.ResourceWrapper;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ErrorHandlerResult;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import br.com.uol.rest.infrastructure.libraries.SelectableArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.ReflectionUtils.newInstance;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class JsonResponseResolverAdvice implements ResponseBodyAdvice<Object> {

    private static final String SELECTOR = "selector";

    @Autowired
    private MapperResourceModelFactory factory;

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter methodParamter, final MediaType mediaType,
                                  final Class<? extends HttpMessageConverter<?>> converter,
                                  final ServerHttpRequest request, final ServerHttpResponse response) {
        MultiValueMap<String, String> queryParams =
            UriComponentsBuilder.fromUri(request.getURI()).build().getQueryParams();

        final String uri = request.getURI().getPath();

        if (body != null) {
            if (methodParamter.getMethod().isAnnotationPresent(ResourceWrapper.class)) {
                if (body instanceof Collection) {
                    Collection resultList = (Collection) ((Collection) body)
                        .stream().map(item -> convertResource(item, queryParams.get(SELECTOR)))
                        .collect(Collectors.toList());

                    if (resultList.isEmpty()) {
                        return resourceNotFound(response, uri);
                    }

                    return new ResourceItems(resultList, uri);
                } else {
                    if (body instanceof Optional) {
                        Optional bodyOptional = (Optional) body;
                        if (!bodyOptional.isPresent()) {
                            return resourceNotFound(response, uri);
                        }
                        return new ResourceItem(convertResource(bodyOptional.get(), queryParams.get(SELECTOR)), uri);
                    } else {
                        return new ResourceItem(convertResource(body, queryParams.get(SELECTOR)), uri);
                    }
                }
            }

            if (methodParamter.getParameterType().isAnnotationPresent(ErrorResource.class)) {
                final ErrorHandlerResult errorHandlerResult = (ErrorHandlerResult) body;
                errorHandlerResult.setUri(uri);
                return errorHandlerResult;
            }
        }

        return body;
    }


    private Object resourceNotFound(final ServerHttpResponse response, final String uri) {
        final ErrorHandlerResult errorHandlerResult = new ErrorHandlerResult();
        errorHandlerResult.addErrors("resource not found", 404);
        errorHandlerResult.setUri(uri);
        response.setStatusCode(HttpStatus.NOT_FOUND);
        return errorHandlerResult;
    }

    private Item convertResource(final Object body, List<String> selectors) {
        Class<?> resource = factory.getResource(body.getClass());
        Item itemResource = (Item) newInstance(resource);

        if (selectors != null && !selectors.isEmpty()) {
            ContextArguments context = ContextArguments.create(SelectableArguments.create(selectors.get(0)), "");
            ConverterToResource.convert(body, itemResource, context);
        } else {
            ConverterToResource.convert(body, itemResource);
        }

        return itemResource;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converter) {
        return true;
    }
}
