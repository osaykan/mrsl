package DAY8;


import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class BookItAuthTest {

     @BeforeClass
    public void before(){
         baseURI = "https://cybertek-reservation-api-qa2.herokuapp.com/api/students";

     }

     String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5MiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.oavXVdYWljg-3gsDy3q_smbKYuzf9rOC88eXTgQBULE";

     @Test
    public void getAllCampuses(){

         Response response = given().header("Authorization", accessToken)
                 .when().get("/api/campuses");

         response.prettyPrint();

         System.out.println(response.statusCode());

     }
}
