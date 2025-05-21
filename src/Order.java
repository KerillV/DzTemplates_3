import java.util.List;
import java.util.UUID;

// Заказ
class Order {
    private UUID orderNumber;
    private List<Product> orderedProducts;

    public Order(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
        this.orderNumber = UUID.randomUUID(); // Генерируем уникальный идентификатор заказа
    }

    public UUID getOrderNumber() {
        return orderNumber;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }
}