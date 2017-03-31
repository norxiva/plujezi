package plujezi.example.vertx.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import plujezi.example.vertx.service.PaymentOrderService;

import java.util.Map;

@Slf4j
public class PaymentOrderServiceImpl implements PaymentOrderService {

    private ObjectMapper objectMapper;

    @Inject
    public PaymentOrderServiceImpl(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Override
    public Map<String, Object> create(Map<String, Object> record) {
        try {
            log.info(objectMapper.writeValueAsString(record));
            Map<String, Object> response = Maps.newHashMap();
            response.put("name", "jessie");
            response.put("gender", "female");
            return response;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
