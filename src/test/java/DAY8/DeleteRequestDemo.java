package DAY8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilies.ConfigurationReader;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;


public class DeleteRequestDemo {
    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("spartan_api_url");

    }
    @Test
    public void test1(){
// content type vs yazmaya gerek yok..
        given()
                .pathParam("id", 296)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204).log().all();
    }

}
