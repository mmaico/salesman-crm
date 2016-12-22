package br.com.kproj.salesman.assistants.calendar.infrastructure.configuration;

import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.specializations.CalendarActiityContactToActivityConverter;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.specializations.CalendarActiityNegotiationToActivityConverter;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.specializations.CalendarActiitySaleableToActivityConverter;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityType;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityType.*;

@Configuration
public class ActivityConverterFactory {

    @Bean(name="activitiesConverters")
    public Map<CalendarActivityType, Converter<? extends Identifiable, ? extends ModelIdentifiable>> converters (
            CalendarActiityContactToActivityConverter activityContactConverter,
            CalendarActiitySaleableToActivityConverter actiitySaleableConverter,
            CalendarActiityNegotiationToActivityConverter actiityNegotiationConverter
    ) {

        Map<CalendarActivityType, Converter<? extends Identifiable, ? extends ModelIdentifiable>> converters = new HashMap<>();
        converters.put(CONTACT, activityContactConverter);
        converters.put(SALEABLE, actiitySaleableConverter);
        converters.put(NEGOTIATION, actiityNegotiationConverter);

        return converters;
    }
}
