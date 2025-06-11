package Repository;
import Entity.Category;
import org.springframework.data.repository.CrudRepository;
public interface CategoryRepository extends CrudRepository<Category, Byte> {
}
