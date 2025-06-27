package com.hatimcherkaoui.graphqlstudy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class GraphqlstudyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void bcrypt() {
        System.out.println(new BCryptPasswordEncoder().encode("user123"));

    }

}
