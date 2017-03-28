package plujezi.example.vertx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import plujezi.example.vertx.bean.BusinessOrder;
import plujezi.example.vertx.service.BusinessOrderService;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class BusinessOrderVerticle extends AbstractVerticle{

    private BusinessOrderService businessOrderService;

    private ObjectMapper objectMapper;

    @Inject
    public BusinessOrderVerticle(BusinessOrderService businessOrderService, ObjectMapper objectMapper) {
        this.businessOrderService = businessOrderService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void start() throws Exception {
        if (this.businessOrderService == null) {
            throw new IllegalStateException("Dependency was not injected!");
        }

        if (this.objectMapper == null) {
            throw new IllegalStateException("Dependency was not injected!");
        }

        vertx.eventBus().consumer("plujezi.business_order").handler(msg -> {
            try {
                BusinessOrder businessOrder = objectMapper.readValue(((JsonObject)msg.body()).encode(), BusinessOrder.class);
                Map<String, String> respMap = businessOrderService.create(businessOrder);
                String retVal = objectMapper.writeValueAsString(respMap);
                msg.reply(retVal);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                msg.fail(2, e.getLocalizedMessage());
            }
//            businessOrderService
        });
    }
}
