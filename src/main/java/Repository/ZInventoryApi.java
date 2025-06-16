package Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"Entity"})
@EnableJpaRepositories(basePackages = {"Repository"})
@ComponentScan(basePackages = {"Controller","Service"})
public class ZInventoryApi {

    public static void main(String[] args) {
        SpringApplication.run(ZInventoryApi.class, args);
    }
}
