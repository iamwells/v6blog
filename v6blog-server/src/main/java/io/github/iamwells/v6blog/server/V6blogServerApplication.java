package io.github.iamwells.v6blog.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class V6blogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(V6blogServerApplication.class, args);
    }

}
