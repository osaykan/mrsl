package Day6_POJO;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class Pojo_deserialize {

    @Test
    public void oneSpartanPojo(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("http://34.228.41.120:8000/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);

        // JSON to POJO --> de serialize to java custom class
        // JSON to our Spartan Object

        Spartan spartan15 = response.body().as(Spartan.class);

        System.out.println(spartan15);

        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());

        // assertion
        assertEquals(spartan15.getId(), 15);
        assertEquals(spartan15.getName(), "Meta");
    }

    @Test
    public void gson_example(){

        Gson gson = new Gson();

        // Json to Java Collections or Pojo --> De-serialization

        String myJsonData = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 11111111111\n" +
                "}";

        Map<String, Object> map = gson.fromJson(myJsonData, Map.class);
        System.out.println(map);

        Spartan spartan15 = gson.fromJson(myJsonData, Spartan.class);
        System.out.println(spartan15);

        //==========       SERIALIZATION    =============/

        //Java Collection or POJO to JSON
        Spartan spartanEU = new Spartan(200, "Mike", "Male",11111111111L );

        String jsonSpartanEU = gson.toJson(spartanEU);
        System.out.println(jsonSpartanEU);




    }
}
