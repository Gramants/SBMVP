package demo.ste.mvpcleanarch.interfaces;


import java.util.List;

import demo.ste.mvpcleanarch.model.Transaction;

public interface PresenterInterface {


    void getTransactionsInRange(String from, String to);

    void calcValue(List<Transaction> transactions);

    void createSavingGoals(String myGoal, String currency);

    void saveMoneyIntoGoal(String goalUid, String currency, int moneyToSave);
}
