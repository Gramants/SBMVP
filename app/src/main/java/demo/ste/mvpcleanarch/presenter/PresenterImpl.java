package demo.ste.mvpcleanarch.presenter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import demo.ste.mvpcleanarch.interactors.InteractorImpl;
import demo.ste.mvpcleanarch.interfaces.ApiInterface;
import demo.ste.mvpcleanarch.interfaces.InteractorInterface;
import demo.ste.mvpcleanarch.interfaces.PresenterInterface;
import demo.ste.mvpcleanarch.interfaces.ViewInterface;
import demo.ste.mvpcleanarch.model.ApiResponseTransactionList;
import demo.ste.mvpcleanarch.model.ApiResponseCreateGoal;
import demo.ste.mvpcleanarch.model.ApiResponseSaveMoneyIntoGoal;
import demo.ste.mvpcleanarch.model.Transaction;
import demo.ste.mvpcleanarch.util.Config;


public class PresenterImpl implements PresenterInterface, InteractorInterface.DatafromServer {


    private InteractorImpl interactor;
    private ViewInterface viewInterface;

    public PresenterImpl(ViewInterface viewInterface, ApiInterface apiInterface) {
        this.viewInterface = viewInterface;
        interactor = new InteractorImpl(apiInterface);
    }

    @Override
    public void onSetData(ApiResponseTransactionList data) {
        viewInterface.onTransactionsReceived(data);
    }

    @Override
    public void onSetData(ApiResponseCreateGoal data) {
        viewInterface.showSaveMoneyButton(data);
    }

    @Override
    public void onSetData(ApiResponseSaveMoneyIntoGoal data) {
        viewInterface.showResult(data);
    }


    @Override
    public void setError(String message) {
        viewInterface.onError(message);
    }

//https://medium.com/mindorks/small-things-when-unit-testing-rxjava-in-android-7f7c336ccabd

    @Override
    public void getTransactionsInRange(String from, String to) {
        interactor.getTransactionsUseCase(this, from, to);
    }


    @Override
    public void calcValue(List<Transaction> transactions) {
        Double sum = 0.0;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getDirection().equals(Config.TRANSACTION_DIRECTION)) {
                sum = sum + (Math.ceil(transactions.get(i).getAmount()) - transactions.get(i).getAmount());
            }
        }
        viewInterface.onValueSetToCreateGoal(round(sum, 2));
    }

    @Override
    public void createSavingGoals(String goalName, String goalCurrency) {
        interactor.createGoalUseCase(this, goalName, goalCurrency, Config.TARGET_VALUE);
    }

    @Override
    public void saveMoneyIntoGoal(String goalUid, String currency, int moneyToSave) {
        interactor.saveMoneyIntoGoalUseCase(this, goalUid, currency, moneyToSave);
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
