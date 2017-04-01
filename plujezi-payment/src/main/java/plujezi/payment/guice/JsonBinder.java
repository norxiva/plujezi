package plujezi.payment.guice;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;

import java.text.SimpleDateFormat;

public class JsonBinder extends AbstractModule {
    @Override
    protected void configure() {
        bind(ObjectMapper.class).toInstance(objectMapper());
    }

    private ObjectMapper objectMapper(){
        return new ObjectMapper(new JsonFactory())
                .registerModule(new JavaTimeModule())
                .registerModule(new Jdk8Module())
                .registerModule(new GuavaModule())
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    }
}
