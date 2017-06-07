package plujezi.payment.guice;

import com.englishtown.vertx.guice.GuiceJerseyBinder;
import com.google.inject.AbstractModule;

public class BootstrapBinder extends AbstractModule{

    @Override
    protected void configure() {
        install(new GuiceJerseyBinder());
    }
}
