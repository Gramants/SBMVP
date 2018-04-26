package demo.ste.mvpcleanarch.interfaces;


import demo.ste.mvpcleanarch.model.ApiResponseTransactionList;
import demo.ste.mvpcleanarch.model.ApiResponseCreateGoal;
import demo.ste.mvpcleanarch.model.ApiResponseSaveMoneyIntoGoal;

public interface InteractorInterface {
    interface DatafromServer {
        void onSetData(ApiResponseTransactionList data);

        void onSetData(ApiResponseCreateGoal data);

        void onSetData(ApiResponseSaveMoneyIntoGoal data);

        void setError(String message);
    }

    void getTransactionsUseCase(DatafromServer datafromServer, String from, String to);

    void createGoalUseCase(DatafromServer datafromServer, String goalName, String currency, int target);

    void saveMoneyIntoGoalUseCase(DatafromServer datafromServer, String savingsGoalUid, String currency, int moneyToSave);
}
