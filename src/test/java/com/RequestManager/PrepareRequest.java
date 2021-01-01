package com.RequestManager;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;

public class PrepareRequest {

    RequestSpecification requestSpecification;
    String apiKey;
    public String searchData;

    @Before
    public void setUp() throws Exception
    {
        init();
    }

    private void init() throws Exception
    {
        RestAssured.baseURI="http://www.omdbapi.com/";
        apiKey = "88489135";
        searchData="Harry Potter";
    }

    @After
    public void tearDown()
    {

    }
}
