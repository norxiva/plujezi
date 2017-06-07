package plujezi.example.guice;

import com.google.inject.AbstractModule;
import plujezi.example.guice.impl.MyDependency;

import javax.inject.Singleton;

public class Binder extends AbstractModule {
    @Override
    protected void configure() {
        bind(Dependency.class).to(MyDependency.class).in(Singleton.class);
    }
}
