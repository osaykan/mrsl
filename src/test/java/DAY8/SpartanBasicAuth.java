package DAY8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilies.ConfigurationReader;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class SpartanBasicAuth {

    @Test
    public void test1(){
        given()
                .accept(ContentType.JSON)
                .and()
                // authorization ekledik
                .auth().basic("admin", "admin")
                .when()
                .get("http://34.228.41.120:8000/api/spartans")
                .then().log().all()
                .statusCode(200);

    }
}
