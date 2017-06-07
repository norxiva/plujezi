package plujezi.payment.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import plujezi.payment.bean.CryptMessage;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Slf4j
@Singleton
@Path("notification")
public class NotificationEndpoint {
    private ObjectMapper objectMapper;

    @Inject
    public NotificationEndpoint(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Path("vfinance")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void notifyFromVFinance(@ApiParam String requestBody, @Context Vertx vertx,
                       @Suspended final AsyncResponse asyncResponse) {

    }

}
