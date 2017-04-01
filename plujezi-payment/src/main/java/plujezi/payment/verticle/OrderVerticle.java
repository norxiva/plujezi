package plujezi.payment.verticle;

import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderVerticle extends AbstractVerticle{


    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("plujezi.order");

    }
}
