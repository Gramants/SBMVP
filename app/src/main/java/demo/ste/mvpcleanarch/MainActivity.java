package demo.ste.mvpcleanarch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.ste.mvpcleanarch.interfaces.ApiInterface;
import demo.ste.mvpcleanarch.interfaces.ViewInterface;
import demo.ste.mvpcleanarch.model.ApiResponseTransactionList;
import demo.ste.mvpcleanarch.model.ApiResponseCreateGoal;
import demo.ste.mvpcleanarch.model.ApiResponseSaveMoneyIntoGoal;
import demo.ste.mvpcleanarch.presenter.PresenterImpl;
import demo.ste.mvpcleanarch.util.CheckInternet;
import demo.ste.mvpcleanarch.util.Config;


public class MainActivity extends AppCompatActivity implements ViewInterface<ApiResponseTransactionList> {

    @Inject
    ApiInterface apiInterface;

    private PresenterImpl presenter;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.edit_goalname)
    EditText editName;

    @BindView(R.id.button_sendname)
    Button buttonSendName;
    @BindView(R.id.button_savemoney)
    Button buttonSaveMoney;
    @BindView(R.id.button_redo)
    Button buttonRedo;

    @BindView(R.id.text_recap)
    TextView recapText;
    @BindView(R.id.text_status)
    TextView statusText;

    private Double calcResult;
    private String goalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplicationContext()).getApiInterface().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }


    private void init() {
        showFirstStep(false);
        showSecondStep(false);

        if (CheckInternet.isOnline(this)) {
            showProgress();
            presenter = new PresenterImpl(this, apiInterface);
            presenter.getTransactionsInRange(Config.DATE_FROM, Config.DATE_TO);
        } else {
            statusMessage("Please check your internet connection!");
            showRedo();
        }


    }

    private void showFirstStep(boolean show) {
        if (show) {
            buttonSendName.setVisibility(View.VISIBLE);
            editName.setVisibility(View.VISIBLE);
        } else {
            buttonSendName.setVisibility(View.INVISIBLE);
            editName.setVisibility(View.INVISIBLE);
        }
    }


    private void showSecondStep(boolean show) {
        if (show) {
            recapText.setVisibility(View.VISIBLE);
            buttonSaveMoney.setVisibility(View.VISIBLE);
        } else {
            recapText.setVisibility(View.INVISIBLE);
            buttonSaveMoney.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String errorMessage) {
        hideProgress();
        statusMessage(errorMessage);
        showFirstStep(false);
        showSecondStep(false);
        showRedo();

    }


    @Override
    public void onTransactionsReceived(ApiResponseTransactionList model) {
        hideProgress();
        presenter.calcValue(model.getEmbedded().getTransactions());
    }

    private void statusMessage(String msg) {
        statusText.setText(msg);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onValueSetToCreateGoal(double round) {
        statusMessage("Transaction retrieved, calculation result: " + String.valueOf(round) + " " + Config.DEFAULT_CURRENCY);
        calcResult = round;
        showFirstStep(true);
        showSecondStep(false);

        buttonSendName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goalName = editName.getText().toString();
                presenter.createSavingGoals(editName.getText().toString(), Config.DEFAULT_CURRENCY);
                statusMessage("");
                showProgress();
            }
        });


    }

    @Override
    public void showSaveMoneyButton(ApiResponseCreateGoal data) {
        hideProgress();
        showFirstStep(false);
        showSecondStep(true);
        recapText.setText("Save " + String.valueOf(calcResult) + " " + Config.DEFAULT_CURRENCY + " to Goal " + goalName);

        buttonSaveMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.saveMoneyIntoGoal(data.getSavingsGoalUid(), Config.DEFAULT_CURRENCY, (int) (calcResult * 100));
                statusMessage("");
                showProgress();
            }
        });

    }

    @Override
    public void showResult(ApiResponseSaveMoneyIntoGoal data) {
        hideProgress();
        showFirstStep(false);
        showSecondStep(false);
        showRedo();

        if (data.getSuccess()) {
            statusMessage("Money have been saved into the Goal");
        } else {
            statusMessage(data.getErrors().get(0).toString());
        }


    }

    public void showRedo() {

        buttonRedo.setVisibility(View.VISIBLE);
        buttonRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusMessage("");
                editName.setText("");
                buttonRedo.setVisibility(View.INVISIBLE);
                init();
            }
        });

    }

}
