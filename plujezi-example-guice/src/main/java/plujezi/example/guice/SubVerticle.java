package plujezi.example.guice;

import io.vertx.core.AbstractVerticle;

import javax.inject.Inject;

public class SubVerticle extends AbstractVerticle {

    private Dependency dependency;

    @Inject
    public SubVerticle(Dependency dependency) {
        this.dependency = dependency;
    }

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("test.sub");
    }
}
