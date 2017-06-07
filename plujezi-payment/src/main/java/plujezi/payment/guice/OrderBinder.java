package plujezi.payment.guice;

import com.google.inject.AbstractModule;
import plujezi.payment.service.OrderQueryService;
import plujezi.payment.service.OrderService;
import plujezi.payment.service.TransactionService;
import plujezi.payment.service.impl.OrderQueryServiceImpl;
import plujezi.payment.service.impl.OrderServiceImpl;
import plujezi.payment.service.impl.TransactionServiceImpl;

import javax.inject.Singleton;

public class OrderBinder extends AbstractModule {
    @Override
    protected void configure() {
        bind(OrderService.class).to(OrderServiceImpl.class).in(Singleton.class);
        bind(OrderQueryService.class).to(OrderQueryServiceImpl.class).in(Singleton.class);
        bind(TransactionService.class).to(TransactionServiceImpl.class).in(Singleton.class);
    }
}
