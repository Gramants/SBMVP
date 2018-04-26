package demo.ste.mvpcleanarch.interfaces;


import demo.ste.mvpcleanarch.model.ApiResponseCreateGoal;
import demo.ste.mvpcleanarch.model.ApiResponseSaveMoneyIntoGoal;
import demo.ste.mvpcleanarch.model.ApiResponseTransactionList;

public interface ViewInterface<T> {
    void showProgress();

    void hideProgress();

    void onError(String type);

    void onValueSetToCreateGoal(double round);

    void onTransactionsReceived(ApiResponseTransactionList model);

    void showSaveMoneyButton(ApiResponseCreateGoal data);

    void showResult(ApiResponseSaveMoneyIntoGoal data);
}
