package demo.ste.mvpcleanarch.model;


import com.google.gson.annotations.SerializedName;

public class Links_ {

    @SerializedName("detail")

    private Detail detail;

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

}