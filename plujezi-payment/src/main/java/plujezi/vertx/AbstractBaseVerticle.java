package plujezi.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractBaseVerticle extends AbstractVerticle {

    protected DeploymentOptions deploymentOptions() {
        return new DeploymentOptions().setWorker(true).setMultiThreaded(true).setInstances(1);
    }

    protected void deployHandler(AsyncResult ar, String name) {
        if(ar.succeeded()){
            log.info("deploy verticle {}[{}] succeed.", name, ar.result());
        }else{
            log.error("deploy verticle {}[{}] failed", name, ar.result());
        }
    }

    protected void deployHandler(AsyncResult ar) {
        deployHandler(ar, "");
    }

}
