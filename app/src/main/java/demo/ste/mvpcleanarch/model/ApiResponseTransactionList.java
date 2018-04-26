package demo.ste.mvpcleanarch.model;


import com.google.gson.annotations.SerializedName;

public class ApiResponseTransactionList {

    @SerializedName("_links")

    private Links links;
    @SerializedName("_embedded")

    private Embedded embedded;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

}