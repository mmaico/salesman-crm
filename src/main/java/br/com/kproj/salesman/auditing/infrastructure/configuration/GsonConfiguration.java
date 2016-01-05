package br.com.kproj.salesman.auditing.infrastructure.configuration;

import br.com.kproj.salesman.auditing.infrastructure.GsonExclusionStrategy;
import br.com.kproj.salesman.auditing.infrastructure.HibernateProxyTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfiguration {


    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setExclusionStrategies(GsonExclusionStrategy.create())
                .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .create();
    }
}
