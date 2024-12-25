package hexlet.code.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

@SuppressWarnings("checkstyle:MagicNumber")
public class BaseRepository {
    public static HikariConfig getConfig() {
        HikariConfig config = new HikariConfig();
        String connection = System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");
        config.setJdbcUrl(connection);
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        return config;
    }

    public static HikariDataSource getDataSource() {
        return new HikariDataSource(getConfig());
    }

    public static void implementSchema() {
        var url = BaseRepository.class.getClassLoader().getResourceAsStream("schema.sql");
        assert url != null;
        String sql = new BufferedReader(new InputStreamReader(url))
                .lines().collect(Collectors.joining("\n"));
        try (Connection connection = getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getDataSource().close();
    }
}
