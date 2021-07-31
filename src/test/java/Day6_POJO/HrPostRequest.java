package Day6_POJO;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilies.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class HrPostRequest {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("hr_api_url");

    }

    @Test
    public void PostRegion1(){
        RegionPost regionPost = new RegionPost(502, "Cybertek Germanny");

        given().log().all()
                .and()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(regionPost)
                .when()
                .post("/regions/")
                .then().log().all()
                .statusCode(201)
                .body("region_id", is(502));



    }


}
