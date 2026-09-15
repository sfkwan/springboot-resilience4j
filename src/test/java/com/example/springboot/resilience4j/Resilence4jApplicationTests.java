package com.example.springboot.resilience4j;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Resilience4jApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;NON_KEYWORDS=USER",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driver-class-name=org.h2.Driver"
})
class Resilence4jApplicationTests {

    @Test
    void contextLoads() {
    }

}