package Day6_POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilies.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("spartan_api_url");

    }

/*  Given accept type and Content Type is JSON
    And request JSON body is:
        {   "gender": "Male",
            "name": "MikeEU",
            "phone": 8877445596
        }

    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response should contain:
        "A Spartan is Born!" message
        and same date what is posted
** */
    @Test
    public void PostNewSpartan(){

        // Burada requesti String olarak gonderiyorum,
        // 6 response verify yontemden 2. yontem olan "path" ile ve testng assert metoduyla verify yapiyorum


        String jsonBody = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Mikehkl\",\n" +
                "  \"phone\": 127374748\n" +
                "}";
        Response response = given().log().all() // requestin tamamini console'da gormek icin
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(jsonBody)
                .when()
                .post("api/spartans");


        response.prettyPrint(); // to see whole the response

        // verify status code 201 for post
        assertEquals(response.statusCode(), 200);

        // verify content type
        assertEquals(response.contentType(), "application/json");

        //verify successful message

        String actualMessage = response.path("success");
        String expectedMessage = "A Spartan is Born!";

        assertEquals(actualMessage, expectedMessage);

        // short way
        // assertEquals (response.path("success"), "A Spartan is Born!");

        // assertion for spartan data
        String name =  response.path("data.name");
        String gender =  response.path("data.gender");
        long phone =  response.path("data.phone");

        assertEquals(name, "MikeEU");
        assertEquals(gender, "Female");
        assertEquals(phone, 127374748);

    }

    @Test
    public void PostNewSpartan2(){
     // create a map to keep request json body information

     // Burada Post requesti tek Map olarak gonderiyorum, response'un verify islemini de burada yapiyoruz
        // 6 cesit response verify yontemi vardi, 4. yontem olan hamcrest chaining kullaniyoruz

     Map<String, Object> requestMap = new HashMap<>();

     // Adding value that we want to post
        requestMap.put("name", "MikeEU2");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 8877445596l);

        // bu kod ile post metodun icinde response'u da verify yapabiliyoruz
        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when()
                .post("api/spartans")
                .then().log().all()
                .statusCode(201)
                .and()
                .contentType("application/json")
                // assertion icin burada hamcrest matchers kullaniyoruz
                /// body metodu, otomatik olarak serialization yapiyor
                // serialization islemini daha once gson ile yapmistik
                .body("success",is(" A Spartan is Born!"),
                        // is metodu ile equalTo metodu ayni islevde, ikisi de kullanilabilir
                        "data.name", equalTo("MikeEu2"),
                        "data.gender", equalTo("Male"),
                        "data.phone", equalTo(8877445596l));


    }
    @Test
    public void PostNewSpartan3(){

       Spartan spartanEU = new Spartan();

       spartanEU.setName("MikeEU3");
       spartanEU.setGender("Male");
       spartanEU.setPhone(8877445596l);

         // bu kod ile post metodun icinde response'u da verify yapabiliyoruz
        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(spartanEU)
                .when()
                .post("api/spartans")
                .then().log().all()
                .statusCode(201)
                .and()
                .contentType("application/json")
                // assertion icin burada hamcrest matchers kullaniyoruz
                /// body metodu, otomatik olarak serialization yapiyor
                .body("success",is(" A Spartan is Born!"),
                        // is metodu ile equalTo metodu ayni islevde, ikisi de kullanilabilir
                        "data.name", equalTo("MikeEu2"),
                        "data.gender", equalTo("Male"),
                        "data.phone", equalTo(8877445596l));


    }
}
