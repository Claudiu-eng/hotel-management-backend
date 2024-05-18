package siemens.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    @Value("${database.ip}")
    private String databaseUrlFlyway;
    @Value("${database.port}")
    private String databasePortFlyway;
    @Value("${database.name}")
    private String databaseName;
    @Value("${database.user}")
    private String username;
    @Value("${database.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + databaseUrlFlyway+":"+ databasePortFlyway +"/"+databaseName);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }

}
