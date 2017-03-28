package plujezi.example.vertx.guice;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import plujezi.example.vertx.service.BusinessOrderService;
import plujezi.example.vertx.service.impl.BusinessOrderServiceImpl;

import java.text.SimpleDateFormat;

public class BusinessOrderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BusinessOrderService.class).to(BusinessOrderServiceImpl.class).in(Singleton.class);
        bind(ObjectMapper.class).toInstance(objectMapper());

    }

    private ObjectMapper objectMapper(){
        return new ObjectMapper(new JsonFactory())
                .registerModule(new JavaTimeModule())
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    }
}
