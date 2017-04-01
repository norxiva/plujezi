package plujezi.payment.verticle;

import lombok.extern.slf4j.Slf4j;
import plujezi.vertx.AbstractBaseVerticle;
import plujezi.vertx.VertxConstant;

@Slf4j
public class MainVerticle extends AbstractBaseVerticle{

    @Override
    public void start() throws Exception {
        String orderVerticleName = VertxConstant.PREFIX_GUICE + OrderVerticle.class.getName();
        String paymentVerticleName = VertxConstant.PREFIX_GUICE + PaymentVerticle.class.getName();
        String transactionVerticleName = VertxConstant.PREFIX_GUICE + TransactionVerticle.class.getName();

        vertx.deployVerticle(orderVerticleName, deploymentOptions(), ar -> deployHandler(ar, orderVerticleName));
        vertx.deployVerticle(paymentVerticleName, deploymentOptions(), ar -> deployHandler(ar, paymentVerticleName));
        vertx.deployVerticle(transactionVerticleName, deploymentOptions(), ar -> deployHandler(ar, transactionVerticleName));
    }

}
