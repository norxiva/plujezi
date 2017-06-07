package plujezi.payment.guice;

import com.google.inject.AbstractModule;
import plujezi.payment.service.TransactionService;
import plujezi.payment.service.impl.TransactionServiceImpl;

import javax.inject.Singleton;

public class TransactionBinder extends AbstractModule {
    @Override
    protected void configure() {
        bind(TransactionService.class).to(TransactionServiceImpl.class).in(Singleton.class);
    }
}
