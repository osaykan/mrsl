package DAY8;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.*;
import utilies.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;


public class JsonSchemaValidationDemo {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("spartan_api_url");

    }

    @Test
    public void JsonSchemaValidationForSpartan(){
        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));


    }
}
