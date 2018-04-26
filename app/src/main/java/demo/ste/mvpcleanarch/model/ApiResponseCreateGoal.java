package demo.ste.mvpcleanarch.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ApiResponseCreateGoal {
    @SerializedName("savingsGoalUid")

    private String savingsGoalUid;
    @SerializedName("success")

    private Boolean success;
    @SerializedName("errors")

    private List<Object> errors = null;

    public String getSavingsGoalUid() {
        return savingsGoalUid;
    }

    public void setSavingsGoalUid(String savingsGoalUid) {
        this.savingsGoalUid = savingsGoalUid;
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
