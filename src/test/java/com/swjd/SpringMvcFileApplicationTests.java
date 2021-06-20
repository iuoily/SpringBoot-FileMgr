package com.swjd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class SpringMvcFileApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(File.separator);
    }

}
