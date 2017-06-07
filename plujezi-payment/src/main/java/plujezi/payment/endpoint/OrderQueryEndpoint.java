package plujezi.payment.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import plujezi.payment.bean.CryptMessage;

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

@Api(tags = "Payment Order Queries", description = "Operations about payment order queries")
@Slf4j
@Singleton
@Path("order_query")
public class OrderQueryEndpoint {

    @ApiOperation(value = "Query Order.")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(@ApiParam @NotNull CryptMessage cryptMessage, @Context Vertx vertx,
                       @Suspended final AsyncResponse asyncResponse) {

    }
}
