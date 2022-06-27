package com.jakers.mustneed;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class MustNeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(MustNeedApplication.class, args);
    }

}
