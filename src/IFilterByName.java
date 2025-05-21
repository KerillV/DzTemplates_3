import java.util.List;

// / Интерфейс фильтров
interface IFilterByName {
    List<Product> filterByName(String name);
}