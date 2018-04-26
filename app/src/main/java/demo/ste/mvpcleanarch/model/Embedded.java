package demo.ste.mvpcleanarch.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Embedded {

    @SerializedName("transactions")

    private List<Transaction> transactions = null;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}