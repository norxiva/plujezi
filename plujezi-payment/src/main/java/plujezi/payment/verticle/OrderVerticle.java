package plujezi.payment.verticle;

import com.englishtown.vertx.jersey.JerseyOptions;
import com.englishtown.vertx.jersey.JerseyServer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import plujezi.payment.guice.OrderBinder;
import plujezi.payment.service.OrderService;
import plujezi.vertx.AbstractBaseVerticle;
import plujezi.vertx.JerseyGuiceVerticle;
import plujezi.vertx.VertxConstant;

import javax.inject.Inject;


@Slf4j
public class OrderVerticle extends AbstractBaseVerticle {

    private OrderService orderService;

    @Inject
    public OrderVerticle(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void start() throws Exception {
        consumer();
    }

    public static JsonObject defaultConfig(){
        return new JsonObject().put(VertxConstant.CONFIG_GUICE, OrderBinder.class.getName());
    }

    private void consumer(){
        vertx.eventBus().consumer("plujezi.order.create", msg -> {
            log.info(((JsonObject)msg.body()).encode());
            msg.reply("{\"success\":true, \"method\":\"haha\"}");
        });

        vertx.eventBus().consumer("plujezi.order.validate", msg -> {
            log.info(((JsonObject)msg.body()).encode());
            msg.reply("{\"success\":true, \"method\":\"haha\"}");
        });

        vertx.eventBus().consumer("plujezi.order.decrypt", msg -> {

        });
        vertx.eventBus().consumer("plujezi.order.encrypt", msg -> {

        });
    }

}
