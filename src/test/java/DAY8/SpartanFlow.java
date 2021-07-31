package DAY8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilies.ConfigurationReader;


import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;


public class SpartanFlow {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("spartan_api_url");

    }

    @Test
    public void test1() {

        Random rd = new Random();
        int idToDelete = rd.nextInt(700)+1;
        System.out.println("This spartan id: " +idToDelete+ " will be deleted. Say good bye!");
        given()
                .pathParam("id",idToDelete)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204).log().all();
    }

    @Test (priority = 1)
    public void POSTNewSpartan(){

    }

    @Test (priority = 2)
    public void PUTExistingSpartan(){

    }

    @Test (priority = 3)
    public void PATCHExistingSpartan(){

    }

    @Test (priority = 4)
    public void GETThatSpartan(){

    }

    @Test (priority = 5)
    public void DELETEThatSpartan(){
// content type vs yazmaya gerek yok..
        given()
                .pathParam("id", 296)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204).log().all();
    }


    }