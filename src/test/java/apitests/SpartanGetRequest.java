package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SpartanGetRequest {

    String spartanURL= "http://34.228.41.120:8000";

    @Test
    public void test1() {
        // requesti when ile gonderiyoruz, cevabi response'da sakliyoruz
        Response response = when().get(spartanURL+"/api/spartans");

        System.out.println(response.statusCode());

        response.prettyPrint();

    }
    /* TASK
    *  When users send a request to api/spartans/3
    *  Then status code should be 200
    *  And content type should be application/json;charset=UTF-8
    *  And json body should contain Fidole
    * */

    @Test
    public void test2(){

        Response response = when().get(spartanURL+"/api/spartans/17");

        Assert.assertEquals(response.statusCode(), 200);
        //Assert.assertEquals(response.contentType(), "application/json;charset=UTF-8");
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertTrue(response.body().asString().contains("Wash"));

    }

    /* TASK
        Given no headers provided
        When users send GET request to /api/hello (spartans hello controller)
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain date
        And Content-Length should be 17
        And body should be "Hello from Sparta"

     */

    @Test
    public void helloTest(){
        // request
        Response response = given().get(spartanURL + "/api/hello");

        // verify content type
        Assert.assertEquals(response.statusCode(), 200);

        // verify content type
        Assert.assertEquals(response.contentType(), "text/plain;charset=UTF-8");

        //verify we have headers named date
        // Postman'da responseun headerindan key degerine bakiyoruz
        System.out.println(response.header("Date"));
        System.out.println(response.header("Content-Length"));

        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
        Assert.assertTrue(response.headers().hasHeaderWithName("Content-Length"));
        Assert.assertEquals(response.header("Content-Length"), "17");
        Assert.assertEquals(response.getBody().asString(), "Hello from Sparta");

    }


}
