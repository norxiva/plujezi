package plujezi.example.vertx;

import io.vertx.core.*;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;
import plujezi.example.vertx.guice.BusinessOrderModule;

@Slf4j
public class ApplicationVerticle extends AbstractVerticle{

//    private Vertx vertx;
//
//    public static void main(String[] args) throws Exception{
//        new ApplicationVerticle().start();
//    }

    public void start() throws Exception {
//        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");

//        VertxOptions vertxOptions = new VertxOptions();
//
//        vertx = Vertx.vertx(vertxOptions);

        vertx.eventBus().addInterceptor( context -> {

            log.info("message to [{}]: body: [{}}",
                    context.message().address(),
                    context.message().body().toString());
            context.next();
        });

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.post("/business_order").consumes("application/json").produces("application/json").handler(rc -> {
            log.info(rc.getBodyAsString());
            vertx.eventBus().send("plujezi.business_order", rc.getBodyAsJson(),reply -> handleReply(reply, rc));
        });

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
        Context context =  vertx.getOrCreateContext();
        context.config().put("guice_binder", BusinessOrderModule.class.getName());
//                .put("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");

        vertx.deployVerticle("java-guice:" + BusinessOrderVerticle.class.getName(),
                new DeploymentOptions().setWorker(true).setMultiThreaded(true).setInstances(1).setConfig(context.config()), res -> {
            if(res.succeeded()){
                log.info("deploy verticle [{}] succeeded", "java-guice:" + BusinessOrderVerticle.class.getName());
            }else {
                log.error("deploy verticle [{}] failed", "java-guice:" + BusinessOrderVerticle.class.getName());
            }
        });

    }

    private void handleReply(AsyncResult<Message<Object>> reply, RoutingContext rc) {
        if (reply.succeeded()) {
            Message<Object> replyMsg = reply.result();
            if (reply.succeeded()) {
                rc.response()
                        .setStatusMessage("OK")
                        .setStatusCode(200)
                        .putHeader("Content-Type", "application/json")
                        .end(replyMsg.body().toString());
            } else {
                rc.response()
                        .setStatusCode(500)
                        .setStatusMessage("Server Error")
                        .end(reply.cause().getLocalizedMessage());
            }
        }
    }

}
