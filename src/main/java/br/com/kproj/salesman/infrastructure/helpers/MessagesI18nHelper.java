package br.com.kproj.salesman.infrastructure.helpers;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Component
public class MessagesI18nHelper {


    @Resource(name="messageSource")
    private MessageSource messageSource;

    public String get(String key) {

        if (StringUtils.isBlank(key)) {
            return StringUtils.EMPTY;
        }

        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, new Object[0], locale);
    }

}
