import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//// Репозиторий товаров
class ProductRepository implements IRepository<Product> {
    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getAll() {
        return Collections.unmodifiableList(this.products); // Возвращаем неизменяемую коллекцию
    }

    @Override
    public void save(Product product) {
        this.products.add(product); // Добавляем новый товар
    }
}