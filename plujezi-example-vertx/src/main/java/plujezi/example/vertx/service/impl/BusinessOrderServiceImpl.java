package plujezi.example.vertx.service.impl;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import plujezi.example.vertx.bean.BusinessOrder;
import plujezi.example.vertx.service.BusinessOrderService;

import java.util.Map;

@Slf4j
public class BusinessOrderServiceImpl implements BusinessOrderService {

    @Override
    public Map<String, String> create(BusinessOrder businessOrder){
        Map<String, String> response = Maps.newHashMap();
        response.put("success", "true");
        response.put("respCode", "000000");
        response.put("respMsg", "succeed");
        return response;
    }

}
