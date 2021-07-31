package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilies.ConfigurationReader;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("cbt_api_url");

    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 20941)
                .when().get("/student/{id}");

        assertEquals(response.statusCode(), 200);

        // assign response to jsonpath

        JsonPath jsonPath = response.jsonPath();

        // get firstname of student where id = 21563
        String firstName = jsonPath.getString("students.firstName[0]");

        System.out.println("firstName = " + firstName);

        Long phoneNumber = jsonPath.getLong("students.contact[0].phone");
        System.out.println("l = " + phoneNumber);

        String city = jsonPath.getString("students.company[0].address.city");
        System.out.println("city = " + city);

        String zipCode =  jsonPath.getString("students.company[0].address.zipCode");
        System.out.println("zipCode = " + zipCode);

    }

}
