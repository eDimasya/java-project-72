package hexlet.code.repository;

import hexlet.code.model.Url;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class UrlRepositoryTest {

    @Test
    void test() throws SQLException {
        Url url = new Url("test");
        BaseRepository.implementSchema();
        UrlRepository.save(url);
        Assertions.assertFalse(UrlRepository.getEntities().isEmpty());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {

    }

    @Test
    void find() {
    }

    @Test
    void getEntities() {

    }
}