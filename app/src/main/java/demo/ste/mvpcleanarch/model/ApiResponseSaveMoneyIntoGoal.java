package demo.ste.mvpcleanarch.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ApiResponseSaveMoneyIntoGoal {

    @SerializedName("transferUid")

    private String transferUid;
    @SerializedName("success")

    private Boolean success;
    @SerializedName("errors")

    private List<Object> errors = null;

    public String getTransferUid() {
        return transferUid;
    }

    public void setTransferUid(String transferUid) {
        this.transferUid = transferUid;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }
}
