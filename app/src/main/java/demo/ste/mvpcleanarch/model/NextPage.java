package demo.ste.mvpcleanarch.model;


import com.google.gson.annotations.SerializedName;

public class NextPage {

    @SerializedName("href")

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}