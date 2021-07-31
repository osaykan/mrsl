package DAY8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilies.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;


public class PutRequestDemo {
    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("spartan_api_url");

    }
    @Test
    public void test1(){
        // Create one map for put request json body
        Map<String, Object> putRequestMap = new HashMap<>();

        putRequestMap.put("name", "PutName");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone",123123123123L);

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 628)
                .and()
                .body(putRequestMap)
                .when()
                .put("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);


    }
    @Test
    public void PatchTest()
    {
        // Create one map for put request json body

        Map<String, Object> putRequestMap = new HashMap<>();

        putRequestMap.put("name", "PatchName");
       // putRequestMap.put("gender", "Male");
       // putRequestMap.put("phone",123123123123L);


        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 628)
                .and()
                .body(putRequestMap)
                .when()
                .patch("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);

    }
}
