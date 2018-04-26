package demo.ste.mvpcleanarch.model;


import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("_links")

    private Links_ links;
    @SerializedName("id")

    private String id;
    @SerializedName("currency")

    private String currency;
    @SerializedName("amount")

    private Double amount;
    @SerializedName("direction")

    private String direction;
    @SerializedName("created")

    private String created;
    @SerializedName("narrative")

    private String narrative;
    @SerializedName("source")

    private String source;
    @SerializedName("balance")

    private Double balance;

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}