package br.com.kproj.salesman.infrastructure.configuration;


import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.math.BigDecimal;
import java.util.Locale;

@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {


    @Bean
    public FilterRegistrationBean hiddenHttpMethodFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ProductPriceFormatter());
    }

    public class ProductPriceFormatter implements Converter<String, BigDecimal> {


        @Override
        public BigDecimal convert(String source) {
            String valueTreat = source.replaceAll("(\\.|,)", "");

            if (valueTreat.length() < 3) {
                return new BigDecimal(valueTreat);
            }
            StringBuilder builder = new StringBuilder(valueTreat);
            StringBuilder converted = builder.insert(valueTreat.length() - 2, ".");


            return new BigDecimal(converted.toString().trim());
        }
    }
}
