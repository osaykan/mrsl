package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utilies.ConfigurationReader;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class hrApiWithPath {
    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("hr_api_url");

    }

    @Test
    public void getCountriesWithPath(){
        Response response = given().accept(ContentType.JSON)
                            .and().queryParam("q", "{\"region_id\":2}")
                            .when().get("/countries");

        assertEquals(response.statusCode(), 200);

        System.out.println(response.path("limit").toString());
        System.out.println("response.path(\"hasMore\").toString() = " + response.path("hasMore").toString());
        
        String firstCountryId = response.path("items.country_id[0]");
        System.out.println("firstCountryId = " + firstCountryId);
        
        // to see USA: 
        String secondCountryName = response.path("items.country_name[4]");
        System.out.println("secondCountryName = " + secondCountryName);

        // items are array of jsons but links are array of jsons too
        // bizim hrurl'deki links tek elemanli bir array, o yuzden 0. indexi veriyoruz
        // 3 elemanli olsaydi ve 2. elemani isteseydik, href[1] i verecektik
        String link2 = response.path("items.links[2].href[0]");
        System.out.println("href = " + link2);

        // get all countries
        
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all region id=2
        List<Integer> regionIds = response.path("items.region_id");
        for (int regionID : regionIds) {
             assertEquals(regionID, 2);
        }

    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));

        List<String> job_Ids = response.path("items.job_id");
        for (String jobID: job_Ids) {
              assertEquals(jobID, "IT_PROG");
        }

    }

    // make sure we have only IT_PROG as a job_id


}
