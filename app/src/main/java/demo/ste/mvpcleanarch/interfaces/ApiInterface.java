package demo.ste.mvpcleanarch.interfaces;

import com.google.gson.JsonObject;

import demo.ste.mvpcleanarch.model.ApiResponseTransactionList;
import demo.ste.mvpcleanarch.model.ApiResponseCreateGoal;
import demo.ste.mvpcleanarch.model.ApiResponseSaveMoneyIntoGoal;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET("/api/v1/transactions")
    Observable<ApiResponseTransactionList> getTransactionsInTimeRange(
            @Header("Accept") String accept,
            @Header("Authorization") String authorization,
            @Header("Content-Type") String contentType,
            @Query("from") String fromData,
            @Query("to") String toData
    );

    @PUT("/api/v1/savings-goals/{id}")
    Observable<ApiResponseCreateGoal> createGoalObservable(@Header("Accept") String accept,
                                                           @Header("Authorization") String authorization,
                                                           @Header("Content-Type") String contentType,
                                                           @Path("id") String uuid,
                                                           @Body JsonObject jsonObject
    );

    @PUT("/api/v1/savings-goals/{GoalUid}/add-money/{id}")
    Observable<ApiResponseSaveMoneyIntoGoal> saveMoneyIntoGoalObservable(@Header("Accept") String accept,
                                                                         @Header("Authorization") String authorization,
                                                                         @Header("Content-Type") String contentType,
                                                                         @Path("GoalUid") String goalUid,
                                                                         @Path("id") String uuid,
                                                                         @Body JsonObject jsonObject
    );


}
