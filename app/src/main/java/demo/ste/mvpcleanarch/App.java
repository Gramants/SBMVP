package demo.ste.mvpcleanarch;

import android.app.Application;

import demo.ste.mvpcleanarch.components.ApiInterfaceComponent;
import demo.ste.mvpcleanarch.components.DaggerApiInterfaceComponent;


public class App extends Application {

    private ApiInterfaceComponent apiInterfaceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        apiInterfaceComponent = DaggerApiInterfaceComponent.builder().build();
    }

    public ApiInterfaceComponent getApiInterface() {
        return apiInterfaceComponent;
    }
}
