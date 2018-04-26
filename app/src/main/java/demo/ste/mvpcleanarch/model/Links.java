package demo.ste.mvpcleanarch.model;


import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("nextPage")

    private NextPage nextPage;

    public NextPage getNextPage() {
        return nextPage;
    }

    public void setNextPage(NextPage nextPage) {
        this.nextPage = nextPage;
    }

}