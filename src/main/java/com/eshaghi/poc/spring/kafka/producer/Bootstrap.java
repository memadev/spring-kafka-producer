package com.eshaghi.poc.spring.kafka.producer;

import com.eshaghi.poc.spring.kafka.producer.domain.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public interface Bootstrap {

    static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.eshaghi.poc.spring.kafka.producer");
        EventProducer producer = context.getBean(EventProducer.class);

        Event event = Event.newBuilder()
                .withId(UUID.randomUUID().toString())
                .withTimestamp(Instant.now())
                .withParams(Collections.singletonMap("action", "start"))
                .build();

        producer.publish(event);
        TimeUnit.SECONDS.sleep(1);
    }
}
