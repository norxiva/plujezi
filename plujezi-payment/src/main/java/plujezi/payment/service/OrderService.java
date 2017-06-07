package plujezi.payment.service;

import io.vertx.core.eventbus.Message;

public interface OrderService {


    void create(Message<Object> message);


}
