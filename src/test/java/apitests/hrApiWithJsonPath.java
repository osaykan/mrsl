package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utilies.ConfigurationReader;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class hrApiWithJsonPath {
    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.get("hr_api_url");

    }
    @Test
    public void test1(){
        Response response = get("/countries");

        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items.counrty_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        // get all country ids
        List<String> countryIDs = jsonPath.getList("items.country_id");

        // get all country names where their region id is equal to 2
        List<String> countryNamesRegionId2 = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println("countryNamesRegionId2 = " + countryNamesRegionId2);
    }

    @Test
    public void test2(){
        Response response = given().queryParam("limit", 107)
                            .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        // get all firstnames of employees who is working as IT_PROG
        List<String> firstNamesEmployees = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.first_name");
        System.out.println("employees = " + firstNamesEmployees);

        // get all firstnames of employees who is making more than 10000
        List<String> firstNamesGreatSalary = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("firstNamesGreatSalary = " + firstNamesGreatSalary);
        
        // get me first name of who is making highest salary
        String kingName = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("kingName = " + kingName);
    }
}
