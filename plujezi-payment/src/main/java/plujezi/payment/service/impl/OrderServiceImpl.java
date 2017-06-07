package plujezi.payment.service.impl;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import lombok.extern.slf4j.Slf4j;
import plujezi.payment.service.OrderService;

import javax.inject.Inject;

@Slf4j
public class OrderServiceImpl implements OrderService {

    private Vertx vertx;

    @Inject
    public OrderServiceImpl(Vertx vertx){
        this.vertx = vertx;
    }


    @Override
    public void create(Message<Object> message) {

    }
}
