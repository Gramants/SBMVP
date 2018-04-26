package demo.ste.mvpcleanarch;

import android.accounts.NetworkErrorException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import demo.ste.mvpcleanarch.interfaces.ApiInterface;
import demo.ste.mvpcleanarch.interfaces.ViewInterface;
import demo.ste.mvpcleanarch.model.ApiResponseTransactionList;
import demo.ste.mvpcleanarch.presenter.PresenterImpl;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PresenterTest {
    private static final String ANY_STRING = "anystring";
    private static final String ANY_DATE_FROM = "2018-04-1";
    private static final String ANY_DATE_TO = "2018-04-30";


    @Mock
    ApiInterface apiInterface;
    @Mock
    ViewInterface view;


    @InjectMocks
    PresenterImpl testee;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testee = new PresenterImpl(view, apiInterface);

    }


    @Test
    public void getTransactionList_Success() {
        Mockito.when(apiInterface.getTransactionsInTimeRange(ANY_STRING, ANY_STRING, ANY_STRING, ANY_DATE_FROM, ANY_DATE_TO)).thenReturn(Observable.just(new ApiResponseTransactionList()));
        TestObserver<ApiResponseTransactionList> testSubscriber = TestObserver.create();
        apiInterface.getTransactionsInTimeRange(ANY_STRING, ANY_STRING, ANY_STRING, ANY_DATE_FROM, ANY_DATE_TO).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertSubscribed();
        testSubscriber.assertComplete();
    }

    @Test
    public void getTransactionList_Failure() {
        Mockito.when(apiInterface.getTransactionsInTimeRange(ANY_STRING, ANY_STRING, ANY_STRING, ANY_STRING, ANY_STRING)).thenReturn(get403ForbiddenError());
        TestObserver<ApiResponseTransactionList> testSubscriber = TestObserver.create();
        apiInterface.getTransactionsInTimeRange(ANY_STRING, ANY_STRING, ANY_STRING, ANY_STRING, ANY_STRING).subscribe(testSubscriber);
        testSubscriber.assertError(HttpException.class);
    }

    private Observable<ApiResponseTransactionList> get403ForbiddenError() {
        return Observable.error(new HttpException(
                Response.error(403, ResponseBody.create(MediaType.parse("application/json"), "Forbidden"))));

    }

}
