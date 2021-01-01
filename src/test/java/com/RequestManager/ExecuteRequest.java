package com.RequestManager;

import com.Data;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.CoreMatchers.equalTo;

public class ExecuteRequest extends PrepareRequest{

    private RequestSpecification setDefaultsRequest(){
        requestSpecification = given().
                param(Data.TYPE.getData(), "movie").
                param(Data.YEAR.getData(), "").
                param(Data.DATA_TYPE.getData(), "json").
                param(Data.CALLBACK.getData(), "").
                param(Data.API_VERSION.getData(), "1");
        return requestSpecification;
    }

    private RequestSpecification requestBySearch(String searchData)
    {
        requestSpecification = setDefaultsRequest().given().
                param(Data.API_KEY.getData(), apiKey).
                param(Data.FILM_NAME.getData(), searchData).
                param(Data.PAGE.getData(), "1");
        return requestSpecification;
    }

    public RequestSpecification requestByID(String id)
    {
        requestSpecification = setDefaultsRequest().given().
                param(Data.API_KEY.getData(), apiKey).
                param(Data.ID.getData(), id).
                param(Data.TITLE.getData(), "").
                param(Data.PLOT.getData(), "short");
        return requestSpecification;
    }

    public String getFilmID(int filmIndex)
    {
        requestSpecification = requestBySearch(searchData);
        Response response = requestSpecification.when().get(baseURI).then().extract().response();
        String findFilm = new StringBuilder().append("Search[").append(filmIndex).append("].imdbID").toString();
        JsonPath json = response.jsonPath();
        return json.getString(findFilm);
    }

    private void searchWithID(String imdbID)
    {
        requestSpecification = requestByID(imdbID);
        requestSpecification.when().get(baseURI).then()
                .statusCode(HttpStatus.SC_OK).and()
                .body("Title", equalTo("Harry Potter and the Sorcerer's Stone")).and()
                .body("Year",equalTo("2001")).and()
                .body("Released",equalTo("16 Nov 2001"));
    }

    @Test
    public void testImdbID()
    {
        String filmID = getFilmID(1);
        searchWithID(filmID);
    }

}
