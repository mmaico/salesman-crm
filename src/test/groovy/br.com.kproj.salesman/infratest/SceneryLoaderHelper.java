package br.com.kproj.salesman.infratest;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SceneryLoaderHelper {

    static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    static final ObjectMapper mapper = new ObjectMapper();
    {

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, Boolean.TRUE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    private final static List<Scenery> scenarios = Lists.newArrayList();


    public static void load(String... files) throws IOException {
        for (String fileName : files) {
            InputStream stream = SceneryLoaderHelper.class.getResourceAsStream("/jsonunit/scenarios" + fileName);
            final String json = IOUtils.toString(stream);

            final List<LinkedHashMap<String, String>> list = JsonPath.parse(json).read("$.[*]", ArrayList.class);
            list.stream().forEach(scenarie -> {
                Scenery scenery = new Scenery();
                try {
                    scenery.setDescription(scenarie.get("scenery"));
                    scenery.setJson(mapper.writeValueAsString(scenarie.get("json")));
                    scenarios.add(scenery);
                } catch (JsonProcessingException e) {
                    throw new IllegalArgumentException(e);
                }

            });

        }
    }

    public static Scenery scenery(String description) {

        int indexOf = scenarios.indexOf(SceneryLoaderHelper.ExternalEquals.externalEquals(description));

        if (indexOf > -1) {
            return scenarios.get(indexOf);
        }

        throw new IllegalArgumentException("Scenario not found [" + description +"]" + scenarios);
    }

    public static class Scenery {

        private String json;
        private String description;

        Scenery() {
        }

        Scenery(String description) {
            this.description = description;
        }

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
    }

    private static class ExternalEquals {

        private final Scenery scenery;

        public ExternalEquals(String description) {
            this.scenery = new Scenery(description);
        }

        public static ExternalEquals externalEquals(String description) {
            return new ExternalEquals(description);
        }

        @Override
        public boolean equals(Object e) {
            if (e == null) {
                return Boolean.FALSE;
            }
            if (!(e instanceof Scenery)) {
                return Boolean.FALSE;
            }
            Scenery sceneryParam = (Scenery) e;

            if (sceneryParam.getDescription().equalsIgnoreCase(this.scenery.getDescription())) {
                return Boolean.TRUE;
            }

            return Boolean.FALSE;
        }
    }

}
