package plujezi.payment.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import plujezi.payment.bean.CryptMessage;
import plujezi.payment.service.OrderService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Api(tags = "Payment Orders", description = "Operations about payment order")
@Slf4j
@Singleton
@Path("order")
public class OrderEndpoint {



    @ApiOperation(value = "Create Order.")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(@ApiParam @NotNull CryptMessage cryptMessage, @Context Vertx vertx,
                       @Suspended final AsyncResponse asyncResponse) {
        log.info("request parameters: {}", cryptMessage);

        vertx.eventBus().send("plujezi.order.create", JsonObject.mapFrom(cryptMessage), ar -> {
            log.info("reply: {}", ar.succeeded());
            log.info("reply: {}", ar.result().body().toString());

//            asyncResponse.resume(ar.result().body().toString());
            asyncResponse.resume(Response.ok(ar.result().body()).type(MediaType.APPLICATION_JSON)
                    .build());
        });





    }



}
