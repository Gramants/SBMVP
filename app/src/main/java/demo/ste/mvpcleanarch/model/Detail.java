package demo.ste.mvpcleanarch.model;


import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("href")

    private String href;
    @SerializedName("templated")

    private Boolean templated;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getTemplated() {
        return templated;
    }

    public void setTemplated(Boolean templated) {
        this.templated = templated;
    }

}