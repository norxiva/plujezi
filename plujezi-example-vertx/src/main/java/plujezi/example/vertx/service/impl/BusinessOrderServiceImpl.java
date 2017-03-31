package plujezi.example.vertx.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import plujezi.example.vertx.bean.BusinessOrder;
import plujezi.example.vertx.service.BusinessOrderService;
import plujezi.example.vertx.service.PaymentOrderService;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
public class BusinessOrderServiceImpl implements BusinessOrderService {

    private PaymentOrderService paymentOrderService;

    private ObjectMapper objectMapper;

    private Vertx vertx;

    @Inject
    public  BusinessOrderServiceImpl(PaymentOrderService paymentOrderService,
                                     ObjectMapper objectMapper,
                                     Vertx vertx){
        this.paymentOrderService = paymentOrderService;
        this.objectMapper = objectMapper;
        this.vertx = vertx;
    }

//    @PostConstruct
//    public void after(){
//        log.info("execute after method.");
//
//    }

    @Override
    public Map<String, String> create(BusinessOrder businessOrder){

        Preconditions.checkNotNull(vertx, "vertx is no injected.");
        try {
            log.info(objectMapper.writeValueAsString(businessOrder));

            Map<String, Object> record = Maps.newHashMap();
            record.put("user", "zi");
            record.put("pwd", "zi123");
            Map<String, Object> respMap = paymentOrderService.create(record);
            log.info("response map: {}", objectMapper.writeValueAsString(respMap));

            Map<String, String> response = Maps.newHashMap();
            response.put("success", "true");
            response.put("respCode", "000000");
            response.put("respMsg", "succeed");
            return response;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return Maps.newHashMap();
        }


    }

}
