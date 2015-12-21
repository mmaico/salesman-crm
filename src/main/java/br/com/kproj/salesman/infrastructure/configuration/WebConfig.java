package br.com.kproj.salesman.infrastructure.configuration;


import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.math.BigDecimal;

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
