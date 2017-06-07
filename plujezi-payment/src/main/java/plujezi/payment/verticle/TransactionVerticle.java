package plujezi.payment.verticle;

import io.vertx.core.json.JsonObject;
import plujezi.payment.guice.OrderBinder;
import plujezi.vertx.AbstractBaseVerticle;
import plujezi.vertx.VertxConstant;

public class TransactionVerticle extends AbstractBaseVerticle {
    @Override
    public void start() throws Exception {
        consumer();
    }

    public static JsonObject defaultConfig(){
        return new JsonObject().put(VertxConstant.CONFIG_GUICE, OrderBinder.class.getName());
    }

    private void consumer(){
        vertx.eventBus().consumer("");
    }
}
