package plujezi.example.vertx.service;

import plujezi.example.vertx.bean.BusinessOrder;

import java.util.Map;

public interface BusinessOrderService {
    Map<String, String> create(BusinessOrder businessOrder);
}
