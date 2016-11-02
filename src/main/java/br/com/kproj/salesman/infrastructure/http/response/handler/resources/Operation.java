package br.com.kproj.salesman.infrastructure.http.response.handler.resources;


import org.apache.commons.lang3.StringUtils;

public class Operation {
    private String op = StringUtils.EMPTY;
    private String path = StringUtils.EMPTY;
    private String value = StringUtils.EMPTY;
    private String from = StringUtils.EMPTY;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
