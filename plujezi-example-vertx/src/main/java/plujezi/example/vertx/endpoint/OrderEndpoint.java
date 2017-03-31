package plujezi.example.vertx.endpoint;


import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Slf4j
@Singleton
@Path("rs/business_order")
public class OrderEndpoint {

    @GET
    public String doGet(@Context Vertx vertx) {
        if (vertx == null) {
            log.info("test1");
        }
        return "Instance of injected!";
    }
}
