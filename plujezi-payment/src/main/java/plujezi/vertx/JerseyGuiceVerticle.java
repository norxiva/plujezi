package plujezi.vertx;

import com.englishtown.vertx.jersey.JerseyOptions;
import com.englishtown.vertx.jersey.JerseyServer;
import io.vertx.core.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class JerseyGuiceVerticle extends AbstractBaseVerticle {

    protected JerseyServer jerseyServer;
    protected JerseyOptions options;

    public JerseyGuiceVerticle(JerseyServer jerseyServer, JerseyOptions options){
        this.jerseyServer = jerseyServer;
        this.options = options;
    }

    @Override
    public void start(final Future<Void> startedResult) throws Exception {
        this.start();
        jerseyServer.start(ar -> {
            if (ar.succeeded()) {
                startedResult.complete();
            } else {
                startedResult.fail(ar.cause());
            }
        });
    }

    @Override
    public void stop() {
        jerseyServer.close();
        jerseyServer = null;
    }

}
