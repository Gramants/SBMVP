package demo.ste.mvpcleanarch.interactors;

import com.google.gson.JsonObject;

import java.util.UUID;

import demo.ste.mvpcleanarch.interfaces.ApiInterface;
import demo.ste.mvpcleanarch.interfaces.InteractorInterface;
import demo.ste.mvpcleanarch.model.ApiResponseTransactionList;
import demo.ste.mvpcleanarch.model.ApiResponseCreateGoal;
import demo.ste.mvpcleanarch.model.ApiResponseSaveMoneyIntoGoal;
import demo.ste.mvpcleanarch.util.Config;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class InteractorImpl implements InteractorInterface {

    private ApiInterface apiInterface;

    public InteractorImpl(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    @Override
    public void getTransactionsUseCase(DatafromServer datafromServer, String from, String to) {
        getTransactionsObservable(from, to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getTransactionsObserver(datafromServer));
    }

    @Override
    public void createGoalUseCase(DatafromServer datafromServer, String goalName, String currency, int target) {
        createGoalObservable(goalName, currency, target)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createGoalObserver(datafromServer));
    }

    @Override
    public void saveMoneyIntoGoalUseCase(DatafromServer datafromServer, String savingsGoalUid, String currency, int moneyToSave) {
        saveMoneyIntoGoalObservable(savingsGoalUid, currency, moneyToSave)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(saveMoneyIntoGoalObserver(datafromServer));
    }


    public Observable<ApiResponseTransactionList> getTransactionsObservable(String from, String to) {
        return apiInterface.getTransactionsInTimeRange("application/json", "Bearer " + Config.ACCESS_TOKEN, "application/json", from, to);

    }


    public Observable<ApiResponseCreateGoal> createGoalObservable(String goalName, String goalCurrency, int target) {
        return apiInterface.createGoalObservable("application/json", "Bearer " + Config.ACCESS_TOKEN, "application/json", UUID.randomUUID().toString(), createSavingsGoal(goalName, goalCurrency, target, ""));

    }

    public Observable<ApiResponseSaveMoneyIntoGoal> saveMoneyIntoGoalObservable(String savingsGoalUID, String goalCurrency, int moneyToSave) {
        return apiInterface.saveMoneyIntoGoalObservable("application/json", "Bearer " + Config.ACCESS_TOKEN, "application/json", savingsGoalUID, UUID.randomUUID().toString(), saveMoneyIntoGoal(moneyToSave, goalCurrency));

    }


    public Observer<ApiResponseTransactionList> getTransactionsObserver(final DatafromServer datafromServer) {
        return new Observer<ApiResponseTransactionList>() {

            @Override
            public void onError(Throwable e) {
                datafromServer.setError("Something went wrong");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ApiResponseTransactionList apiResponseModel) {
                datafromServer.onSetData(apiResponseModel);
            }
        };
    }


    public Observer<ApiResponseCreateGoal> createGoalObserver(final DatafromServer datafromServer) {
        return new Observer<ApiResponseCreateGoal>() {

            @Override
            public void onError(Throwable e) {
                datafromServer.setError("Something went wrong.");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ApiResponseCreateGoal apiResponseModel) {
                datafromServer.onSetData(apiResponseModel);
            }
        };
    }


    public Observer<ApiResponseSaveMoneyIntoGoal> saveMoneyIntoGoalObserver(final DatafromServer datafromServer) {
        return new Observer<ApiResponseSaveMoneyIntoGoal>() {

            @Override
            public void onError(Throwable e) {
                datafromServer.setError("Something went wrong");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ApiResponseSaveMoneyIntoGoal apiResponseModel) {
                datafromServer.onSetData(apiResponseModel);
            }
        };
    }


    public JsonObject createSavingsGoal(String name, String currency, int targetAmount, String photo) {
        JsonObject savingsGoalRequestJson = new JsonObject();
        JsonObject targetJson = new JsonObject();
        savingsGoalRequestJson.addProperty("name", name);
        savingsGoalRequestJson.addProperty("currency", currency);
        savingsGoalRequestJson.addProperty("photo", photo);
        targetJson.addProperty("currency", currency);
        targetJson.addProperty("minorUnits", targetAmount);
        savingsGoalRequestJson.add("target", targetJson);
        return savingsGoalRequestJson;
    }


    public JsonObject saveMoneyIntoGoal(int moneyToSave, String goalCurrency) {
        JsonObject savingsGoalRequestJson = new JsonObject();
        JsonObject amountJson = new JsonObject();
        amountJson.addProperty("currency", goalCurrency);
        amountJson.addProperty("minorUnits", moneyToSave);
        savingsGoalRequestJson.add("amount", amountJson);
        return savingsGoalRequestJson;
    }


}
