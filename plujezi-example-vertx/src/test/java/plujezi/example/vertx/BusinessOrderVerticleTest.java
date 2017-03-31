package plujezi.example.vertx;

import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.SLF4JLogDelegateFactory;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import plujezi.example.vertx.guice.BusinessOrderModule;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@RunWith(VertxUnitRunner.class)
public class BusinessOrderVerticleTest {
    private Vertx vertx;
    private HttpServer server;

    @Before
    public void before(TestContext context) {
        vertx = Vertx.vertx();

        vertx.exceptionHandler(context.exceptionHandler());
        Context ctx =  vertx.getOrCreateContext();
        ctx.config().put("guice_binder", BusinessOrderModule.class.getName())
                .put("vertx.logger-delegate-factory-class-name", SLF4JLogDelegateFactory.class.getName());
        log.info("test1");

        vertx.deployVerticle("java-guice:" + BusinessOrderVerticle.class.getName(),
                new DeploymentOptions().setWorker(true).setMultiThreaded(true).setInstances(1).setConfig(ctx.config()),
                res -> {
                    if(res.succeeded()){
                        log.info("deploy verticle [{}] succeeded", "java-guice:plujezi.example.vertx.BusinessOrderVerticle");
                    }else {
                        log.error("deploy verticle [{}] failed", "java-guice:plujezi.example.vertx.BusinessOrderVerticle");
                    }
                });

        server = vertx.createHttpServer()
                .requestHandler(req -> req.response().end("foo")).
                        listen(8080, context.asyncAssertSuccess());
    }

    @After
    public void after(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }


    @Test
    public void testBusinessOrder(TestContext context){

        JsonObject jsonObject = new JsonObject("{\"merchantReturnUrl\":\"xxx\",\"orderNo\":\"123\"}");
        Async async = context.async();

        vertx.eventBus().send("plujezi.business_order", jsonObject, ar -> {
            assertThat(ar.succeeded(), is(true));
            log.info("ar body: " + ar.result().body().toString());
            async.complete();
        });

    }

    @Test
    public void testBusinessOrder2(TestContext context){

        JsonObject jsonObject = new JsonObject("{\"merchantReturnUrl\":\"xxx\",\"orderNo\":\"123\"}");
        Async async = context.async();

        vertx.eventBus().send("plujezi.business_order", jsonObject, ar -> {
            assertThat(ar.succeeded(), is(true));
            log.info("ar body: " + ar.result().body().toString());
            async.complete();
        });

    }


}
