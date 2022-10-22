package com.azhar.springbootintegrationtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTestClientExampleTests {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void exampleTest() {
        this.webClient.get().uri("/students/1").exchange().expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Rajesh Bhojwani");
    }
}
