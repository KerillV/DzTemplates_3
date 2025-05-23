# Комментарии.
1. Избегание магических чисел. Принцип применяется.
2. Применение принципа DRY. Например, создан отдельный метод userInput для общения с пользователем через консоль, чтобы не дублировать код в цикле метода main() и методе addToCart().
3. S - принцип единственной ответственности. Например, класс ProductRepository занимается исключительно хранением и извлечением продуктов (save, getAll), не беря на себя дополнительные обязанности вроде обработки заказов.
4. O - принцип открытости/закрытости. class NameFilter, реализация интерфейса позволяет добавлять новые фильтры без изменения существующего кода. Например, если мы захотим добавить фильтр по цене, нам достаточно создать новый класс-фильтр и реализовать соответствующий интерфейс. 
5. I - принцип сегрегации (разделения) интерфейса. 
interface IFilterByName {
    List<Product> filterByName(String name);
}
Интерфейсы реализованы таким образом, что каждый интерфейс имеет четкую цель. Классы могут выбирать реализацию именно тех методов, которые необходимы для конкретной задачи.
1. D - принцип инверсии зависимостей. 
class NameFilter implements IFilterByName {
    private final ProductRepository repository;

    public NameFilter(ProductRepository repository) {
        this.repository = repository;
    }
}
Здесь реализуется зависимость от абстракций (интерфейса), а не от конкретных реализаций. Класс NameFilter зависит от интерфейса IRepository, а не конкретного класса ProductRepository. Таким образом, изменение реализации репозитория не повлияет на работу фильтра.
