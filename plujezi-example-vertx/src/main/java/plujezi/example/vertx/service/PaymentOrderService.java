package plujezi.example.vertx.service;

import java.util.Map;

public interface PaymentOrderService {
    Map<String, Object> create(Map<String, Object> record);

}
