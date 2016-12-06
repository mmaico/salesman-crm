package br.com.kproj.salesman.infrastructure.http.response.handler.resources;



import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

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
