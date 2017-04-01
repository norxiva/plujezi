package plujezi.payment.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Info;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import plujezi.payment.bean.OrderMessage;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;


@Api(tags = "Payment Orders", description = "Operations about payment order")
@Slf4j
@Singleton
@Path("order")
public class OrderEndpoint {


    private ObjectMapper objectMapper;

    @Inject
    public OrderEndpoint(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }


    @ApiOperation(value = "Create Order.")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@ApiParam OrderMessage orderMessage, @Context Vertx vertx) throws JsonProcessingException {

        log.info("request body: {}", objectMapper.writeValueAsString(orderMessage));
        Map<String, String> map = Maps.newHashMap();
        map.put("name", "hello");

        return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_JSON)
                .entity(map).build();
    }
}
