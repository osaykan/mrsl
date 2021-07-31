package Day6_POJO;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

// Post Request Class
public class RegionPost {
// I create this class just for POST option

    // I keep the same variable names as the API
    private int region_id;
    private String region_name;

    // eger api'deki ayni varible names kullanmayacaksak, o zaman @SerializedName anno. ekliyoruz
    // @SerializedName("region_id")
    // private int regionID


    public RegionPost(int region_id, String region_name) {
        this.region_id = region_id;
        this.region_name = region_name;
    }

    public RegionPost() {
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
