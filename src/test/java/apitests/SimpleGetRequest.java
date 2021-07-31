package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SimpleGetRequest {



    String hrurl = "3.80.189.73:1000/ords/hr/regions";
    String spartansUrl = "http://34.228.41.120:8000/api/spartans";

    @Test
    public void test1(){

        // RestAssured.get returns a Response object
       // Response response = RestAssured.get(spartansUrl);

        Response response = get(spartansUrl);

        System.out.println(response.statusCode());

        response.prettyPrint();
    }

    /*
    *   Given accept type is json
    *   When user sends get request to student endpoint
    *   Then response status code must be 200
    *   and body is json format
    * */

    @Test
    public void test2(){
      //  Response response = RestAssured.given().accept(ContentType.JSON)
        Response response = given().accept(ContentType.JSON)
                .when().get(spartansUrl);

         // verify response code
        Assert.assertEquals(response.statusCode(), 200);

        // verify content-type is application/json
        Assert.assertEquals(response.contentType(), "application/json");

    }

    @Test
    public void test3(){
       // RestAssured.given().accept(ContentType.JSON)
                    given().accept(ContentType.JSON)
                    .when().get(spartansUrl)
                    .then()
                    .assertThat().statusCode(200)
                    .and().contentType("application/json");
    }

    /** Given accept type is json
     *  When user sends get request ro regions/2
     *  Then response status code must be 200
     *  and body is json format
     *  and response body contains Americas (bu adimi spartansa gore "Niki" olarak degistirdim)
     */

    @Test
    public void test4(){

       // RestAssured.given().accept(ContentType.JSON)
            Response response = given().accept(ContentType.JSON)
           //when().get(hrurl+"/2");
            .when().get(spartansUrl);

            //verify status code
            Assert.assertEquals(response.getStatusCode(), 200);

            // verify content type
            Assert.assertEquals(response.contentType(), "application/json");

            // verify body contains Americas (in my app-Niki)
            // tum body'i Stringe cevirdik
            response.body().asString().contains("Niki");

            Assert.assertTrue(response.body().asString().contains("Niki"));



    }

}
