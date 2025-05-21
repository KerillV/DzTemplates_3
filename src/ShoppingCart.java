import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Корзина пользователя
class ShoppingCart {
    private List<Product> items = new ArrayList<>();

    public void addItem(Product product) {
        items.add(product);
    }

    public List<Product> getItems() {
        return Collections.unmodifiableList(items); // Возвращаем неизменяемую версию корзины
    }

    public void clear() {
        items.clear();
    }
}