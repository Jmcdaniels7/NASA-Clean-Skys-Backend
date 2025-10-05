package com.example.clear_sky_backend.controller;

import com.example.clear_sky_backend.entity.Subscriber;
import com.example.clear_sky_backend.repository.SubscriberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubscriberControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private SubscriberRepository repository;

    @Test
    void createSubscriber_endToEnd() {
        repository.deleteAll();
        String url = "http://localhost:" + port + "/api/subscribers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = "{\"phone\": \"+1-317-555-0123\", \"area\": \"Indy\"}";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Subscriber> resp = rest.postForEntity(url, entity, Subscriber.class);
    Assertions.assertEquals(201, resp.getStatusCode().value());
        Subscriber created = resp.getBody();
        Assertions.assertNotNull(created);
        Assertions.assertEquals("+1-317-555-0123", created.getPhone());
        Assertions.assertEquals("Indy", created.getArea());
        Assertions.assertTrue(repository.findByPhone("+1-317-555-0123").isPresent());
    }
}
