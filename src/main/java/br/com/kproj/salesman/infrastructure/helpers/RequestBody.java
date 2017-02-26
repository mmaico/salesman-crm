package br.com.kproj.salesman.infrastructure.helpers;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestBody {

    private JsonObject jsonObject;

    public RequestBody(HttpServletRequest request) {

        try {
            String json = IOUtils.toString(request.getInputStream());
            this.jsonObject =  new JsonParser().parse(json).getAsJsonObject();
        } catch (Exception e) {
            this.jsonObject = null;
        }
    }

    public static RequestBody body(HttpServletRequest request) {
        return new RequestBody(request);
    }

    public Boolean has(String attribute) {
        if (jsonObject == null) return Boolean.TRUE;
        return jsonObject.get(attribute) != null;
    }
}
