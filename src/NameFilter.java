import java.util.List;
import java.util.stream.Collectors;

// Фильтр по имени товара
class NameFilter implements IFilterByName {
    private final ProductRepository repository;

    public NameFilter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> filterByName(String name) {
        throw new UnsupportedOperationException("Use filterByKeyword instead.");
    }

    // метод фильтрации по ключу
    public List<Product> filterByKeyword(String keyword) {
        return repository.getAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword)) // Поиск по любому фрагменту в названии
                .collect(Collectors.toList());
    }
}