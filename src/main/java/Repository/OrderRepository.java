package Repository;
import Entity.Order;
import org.springframework.data.repository.CrudRepository;
public interface OrderRepository extends CrudRepository<Order, Long> {

}
