package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import utilies.ConfigurationReader;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;


public class SpartanTestWithJsonPath {
    @BeforeClass
    public void beforeClass(){
        baseURI = "http://34.228.41.120:8000";
    }

    /* Given accept type is JSON
        And path param spartan id is 11
        When user sends a get request to /spartans/{id}
        Then status code is 200
        And content type is Json
        And "id": 11,
        "name":"Nona",
        "gender": "Female",
        "phone": 7959094216
     *
    * */

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertEquals(response.path("id").toString(),"11");
        assertEquals(response.path("name"),"Nona");
        assertEquals(response.path("gender"),"Female");
        assertEquals(response.path("phone").toString(), "7959094216");

        // ********* 2. METHOD ******

        JsonPath jsonPath = response.jsonPath();

        int idJson = jsonPath.getInt("id");
        String nameJson = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        //Print the values
        System.out.println("idJson = " + idJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(idJson,11);
        assertEquals(nameJson, "Nona");
        assertEquals(gender, "Female");
        assertEquals(phone, 7959094216L);


    }
}
