package ru.education.pastebox;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"JDBC_URL=jdbc:postgresql://localhost:5432/pastebox_db"})
class PasteboxApplicationTests {

    @Test
    void contextLoads() {
    }

}
