package plujezi.payment.verticle;

import com.englishtown.vertx.jersey.JerseyOptions;
import com.englishtown.vertx.jersey.JerseyServer;
import lombok.extern.slf4j.Slf4j;
import plujezi.vertx.JerseyGuiceVerticle;
import plujezi.vertx.VertxConstant;

import javax.inject.Inject;

@Slf4j
public class BootstrapVerticle extends JerseyGuiceVerticle {

    @Inject
    public BootstrapVerticle(JerseyServer jerseyServer, JerseyOptions options) {
        super(jerseyServer, options);
    }

    @Override
    public void start() throws Exception {
        deploy();
    }

    private void deploy(){
        String orderVerticleName = VertxConstant.PREFIX_GUICE + OrderVerticle.class.getName();
        String transactionVerticleName = VertxConstant.PREFIX_GUICE + TransactionVerticle.class.getName();
        vertx.deployVerticle(orderVerticleName,
                deploymentOptions(OrderVerticle.defaultConfig()),
                ar -> deployHandler(ar, orderVerticleName));
        vertx.deployVerticle(transactionVerticleName,
                deploymentOptions(TransactionVerticle.defaultConfig()),
                ar -> deployHandler(ar, transactionVerticleName));
    }
}
