package plujezi.payment.guice;

import com.englishtown.vertx.guice.GuiceJerseyBinder;
import com.google.inject.AbstractModule;

public class MainBinder extends AbstractModule{

    @Override
    protected void configure() {
        install(new GuiceJerseyBinder());
        install(new JsonBinder());
    }
}
