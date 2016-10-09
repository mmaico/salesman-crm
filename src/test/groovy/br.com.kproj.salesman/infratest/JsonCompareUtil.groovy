package br.com.kproj.salesman.infratest

import com.fasterxml.jackson.databind.ObjectMapper


class JsonCompareUtil {

    def static ObjectMapper mapper = new ObjectMapper()


    def static isEquals(String json, String json2) {
        mapper.readTree(json) == mapper.readTree(json2)
    }
}
