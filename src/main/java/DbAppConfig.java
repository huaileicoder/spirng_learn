import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@PropertySource("jdbc.properties")
public class DbAppConfig {

    @Value("$(jdbc.url)")
    String jdbcUrl;

    @Value("${jdbc.username")
    String jdbcUserName;

    @Value("${jdbc.password")
    String jdbcPassword;

    @Bean
    DataSource createDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(jdbcUserName);
        hikariConfig.setPassword(jdbcPassword);
        hikariConfig.addDataSourceProperty("autoCommit", "true");
        hikariConfig.addDataSourceProperty("connectionTimeOut", "5");
        hikariConfig.addDataSourceProperty("idleTimeOut", "60");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
