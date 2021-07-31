package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class SpartanTestWithParameters {

    // baseURI'i configuration properties'den de verebiliriz.
    @BeforeClass
    public void beforeClass(){
        baseURI = "http://34.228.41.120:8000";
    }

    // tekrar baseURI yazmamiza gerek kalmiyor, get() metodu, baseURI ile kendi parametresini birlestiriyor

    /* @Test
    public void test1(){
        get("api/spartans");
    } */

    /*  TASK
    *   Given accept type is Json
    *   And id parameter value is 500
    *     // id=50 yaptim, Postman'da {{spartansurl}}/api/spartans/:id yapinca, value boxa yaziyoruz
    *   When user sends GET request to api/spartans/{id}
    *   Then response code status code should be 200
    *   And response content-type: application/json;charset=UTF-8
    *                       //benimkinde charset yok, teste koymadim
    *   And "Blythe" should be in response payload(body)
    * */
    @Test
    public void getSpartanID_Positive_PathParam(){
        Response response = given().accept(ContentType.JSON)
                // id -> variable name 50 -> variable value
                .and().pathParam("id",50)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Bori"));
    }

    /*  TASK
     *   Given accept type is Json
     *   id parameter value is 500
     *   When user sends GET request to api/spartans/{id}
     *   Then response code status code should be 404
     *   And response content-type: application/json;charset=UTF-8
     *                       //benimkinde charset yok, teste koymadim
     *   And "Spartan Not Found" should be in response payload(body)
     * */
    @Test
    public void getSpartanID_Negative_PathParam(){
        Response response = given().accept(ContentType.JSON)
                // id -> variable name 500 -> variable value
                .and().pathParam("id",500)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 404);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Not Found"));

    }

    /*  TASK
     *   Given accept type is Json
     *   And query parameter values are:
     *      gender|Female
     *      nameContains|e
     *   When user sends GET request to api/spartans/search
     *  // sorguyu su sekilde gonderdik
     *      {{spartansurl}}/api/spartans/search?gender=Female&nameContains=e
     *    // Query Parameters'a gender ve nameContains'i yazdik
     *     // search i da url'e ekledik
     *
     *   Then response code status code should be 200
     *   And response content-type: application/json;charset=UTF-8
     *                       //benimkinde charset yok, teste koymadim
     *   And "Female" should be in response payload(body)
     *   And "Janette" should be in response payload(body)
     * */

    @Test
    public void positiveTestWithQueryParam(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Leelah"));
        
    }

    @Test
    public void positiveTestWithQueryParamWithMaps(){
        // create a map and add query parameters
        Map<String, Object> queryMap = new HashMap<>();

        queryMap.put("gender", "Female");
        queryMap.put("nameContains", "e");

        Response response = given().accept(ContentType.JSON)
                            .and().queryParams(queryMap)
                            .when().get("api/spartans/search");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Leelah"));


    }
}
