package com;

public enum Data {
    API_KEY("apikey"),
    FILM_NAME("s"),
    TYPE("type"),
    YEAR("y"),
    PLOT("plot"),
    DATA_TYPE("r"),
    CALLBACK("callback"),
    API_VERSION("v"),
    PAGE("page"),
    ID("i"),
    TITLE("t");

    private String data;

    Data(String data)
    {
        this.data = data;
    }

    public String getData()
    {
        return data;
    }

}
