
package Day6_POJO;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Accept Class
@Generated("jsonschema2pojo")
public class Region {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("hasMore")
    @Expose
    private Boolean hasMore;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("links")
    @Expose
    private List<Link__1> links = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Region() {
    }

    /**
     * 
     * @param offset
     * @param hasMore
     * @param limit
     * @param count
     * @param links
     * @param items
     */
    public Region(List<Item> items, Boolean hasMore, Integer limit, Integer offset, Integer count, List<Link__1> links) {
        super();
        this.items = items;
        this.hasMore = hasMore;
        this.limit = limit;
        this.offset = offset;
        this.count = count;
        this.links = links;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Region withItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Region withHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Region withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Region withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Region withCount(Integer count) {
        this.count = count;
        return this;
    }

    public List<Link__1> getLinks() {
        return links;
    }

    public void setLinks(List<Link__1> links) {
        this.links = links;
    }

    public Region withLinks(List<Link__1> links) {
        this.links = links;
        return this;
    }

}

/*
Homework-1
1- create csv file from mackaroo website,
2- download csv file
3- using testng data provider and apachi poi create data driven posting from spartan

Homework-2
create one mackaroo api for name, gender, phone
send get request to retrieve random info from that api
use those info to send post request to spartan

hmw-3
after that, send a get request
*
* */
