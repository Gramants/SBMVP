package demo.ste.mvpcleanarch.components;

import javax.inject.Singleton;

import dagger.Component;
import demo.ste.mvpcleanarch.MainActivity;
import demo.ste.mvpcleanarch.modules.NetModule;

@Singleton
@Component(modules = {NetModule.class})
public interface ApiInterfaceComponent {

    void inject(MainActivity mainActivity);
}
