package plujezi.payment.verticle;

import com.englishtown.vertx.jersey.JerseyOptions;
import com.englishtown.vertx.jersey.JerseyServer;
import io.vertx.core.Future;

import javax.inject.Inject;

public class MainLauncher extends MainVerticle {

    private JerseyServer jerseyServer;
    private JerseyOptions options;

    @Inject
    public MainLauncher(JerseyServer jerseyServer, JerseyOptions options) {
        this.jerseyServer = jerseyServer;
        this.options = options;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        jerseyServer.close();
        jerseyServer = null;
    }

}
