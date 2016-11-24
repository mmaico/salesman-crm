package br.com.kproj.salesman.infrastructure.http.response.handler.resources;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.data.domain.Page;

public class ResourceHolder {

    public static String getUri(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURI());
        String queryString = request.getQueryString();

        return queryString == null
                ? requestURL.toString()
                : requestURL.append('?').append(queryString).toString();
    }

    public static void setInfoPageable(Iterable<?> items, ResourceItems resourceItems) {
        if (items instanceof Page) {
            Page page = (Page) items;
            resourceItems.setPageSize(page.getSize());
            resourceItems.setTotalItems(page.getTotalElements());
        }
    }
}
