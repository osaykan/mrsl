package apitests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;


import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {
   /* TASK

      Given accept type is JSON
      And path param id is 15
      When user sends a get request to spartans/{id}
      Then status code is 200
      And content type is Json
      And Json data has following
            "id": 15,
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106 // 11111111111
    */

// REQUEST ve RESPONSE u ayni satirda yaziyoruz
    @Test
    public void oneSpartanWithHamcrest(){
            given().accept(ContentType.JSON)
                    .and().pathParam("id", 15)
                    .when().get("http://34.228.41.120:8000/api/spartans/{id}")
                    .then().statusCode(200)
                    .and().assertThat().contentType("application/json")
                    .and().assertThat().body("id", equalTo(15),
                    "name", equalTo("Meta"),
                    "gender", equalTo("Female"),
                    "phone", equalTo(11111111111L));

            // Matchers verifyleri yalin kullanmak icin static import ettik
    }

    @Test
    public void teacherData(){
        given().accept(ContentType.JSON)
                .and().pathParam("id", 3783)
                // requestimi consolda görmek icin
                .when().log().all().get("http://api.cybertektraining.com/teacher/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                .and().header("Content-Length", equalTo("242"))
                .and().header("Connection", equalTo("Keep-Alive"))
                .and().header("Date", notNullValue())
                // assertThat() yazabiliriz de yazmayabiliriz de
                .and().assertThat().body("teachers.firstName[0]", equalTo("James"),
                                            "teachers.lastName[0]",equalTo("Bond"),
                                                "teachers.gender[0]",equalTo("Female"))
                .log().all()        // herseyi consolde gormek icin
        ;
    }
    @Test
    public void teachersWithDepartments(){

        given().accept(ContentType.JSON)
                .and().pathParam("name", "Computer")
                .when().log().all().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().statusCode(200).and()
                        .contentType(equalTo("application/json;charset=UTF-8"))
                        .and().body("teachers.firstName", hasItems("Alexander", "Marteen"));

    }


}
