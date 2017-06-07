package plujezi.example.guice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
public class MainVerticle extends AbstractVerticle {

    private Dependency dependency;

    @Inject
    public MainVerticle(Dependency dependency) {
        this.dependency = dependency;
    }

    @Override
    public void start() throws Exception {

        vertx.deployVerticle("java-guice:" + SubVerticle.class.getName(),
                new DeploymentOptions().setConfig(config()).setWorker(true).setMultiThreaded(true).setInstances(1),
                ar -> {
            log.info("deploy result: {}", ar.succeeded(), ar.cause());
        });
    }
}
