package siemens.config;

import lombok.Getter;
import lombok.Setter;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;


@Configuration
@Getter
@Setter
public class FlywayConfiguration {

    @Autowired
    private DataSource dataSource;
    @Value("${spring.flyway.locations}")
    private String path;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        FluentConfiguration configuration = Flyway.configure()
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .cleanOnValidationError(true)
                .locations(path.split(","))
                .dataSource(dataSource);
        return new Flyway(configuration);
    }


}
