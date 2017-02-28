package br.com.kproj.salesman.timelines.activities.domain.model.activities.activity;


public enum Tag {

    CALL, NOTE, EMAIL, MEETING;


    public static Boolean has(String tag) {

        for (Tag item: values()) {
            if (item.name().equalsIgnoreCase(tag)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}
