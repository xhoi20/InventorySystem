package Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"Entity"})
@EnableJpaRepositories(basePackages = {"Repository"})
public class ZInventoryApi {

    public static void main(String[] args) {
        SpringApplication.run(ZInventoryApi.class, args);
    }
}
