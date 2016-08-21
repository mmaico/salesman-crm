package br.com.kproj.salesman.infrastructure.helpers.view;


import com.trex.clone.NormalizeAttributesUpdate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Component
public class NormalizeAttrUpdateHelper {


    public void addAttributesToUpdate(Object entity) {
        Set<String> request = getRequest();
        new NormalizeAttributesUpdate().addAttributesToUpdate(entity, request);
    }

    private static Set<String> getRequest() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        return request.getParameterMap().keySet();
    }

}
