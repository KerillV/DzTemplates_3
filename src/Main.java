import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ProductRepository productRepo = new ProductRepository();
    static ShoppingCart cart = new ShoppingCart();

    public static void main(String[] args) {
        populateSampleData(); // тестовые данные

        while (true) {
            // пользовательское меню
            System.out.print("\n" +
                    "Меню: \n" +
                    "1. Просмотреть каталог \n" +
                    "2. Найти товар по названию \n" +
                    "3. Добавить товар в корзину \n" +
                    "4. Посмотреть корзину \n" +
                    "5. Оформить заказ \n" +
                    "6. Выход \n");

            int choice = userInput("Выберите пункт меню: ");

            switch (choice) {
                case 1:
                    showCatalog();
                    break;
                case 2:
                    findProductByName();
                    break;
                case 3:
                    addToCart();
                    break;
                case 4:
                    viewCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 6:
                    System.out.println("Вы вышли из магазина.");
                    return;
                default:
                    System.out.println("Ошибка! Проверьте введенное значение.");
            }
        }
    }

    // тестовые данные
    private static void populateSampleData() {
        productRepo.save(new Product(1, "Телефон Samsung Galaxy A52", 25_000, "Samsung"));
        productRepo.save(new Product(2, "Ноутбук Lenovo IdeaPad", 50_000, "Lenovo"));
        productRepo.save(new Product(3, "Смартфон Xiaomi Redmi Note 10 Pro", 18_000, "Xiaomi"));
    }

    private static void showCatalog() {
        for (Product product : productRepo.getAll()) {
            System.out.printf("%d | %s | %.2f руб.\n",
                    product.getId(), product.getName(), product.getPrice());
        }
    }

    private static void findProductByName() {
        System.out.print("Введите ключевое слово для поиска товара: ");
        String keyword = scanner.nextLine().trim().toLowerCase(); // Ключевое слово вводится в нижнем регистре

        List<Product> matchingProducts = productRepo.getAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword)) // Фильтруем товары, ищем по частичному соответствию
                .collect(Collectors.toList());

        if (matchingProducts.isEmpty()) {
            System.out.println("По вашему запросу ничего не найдено.");
        } else {
            System.out.println("Совпадения по запросу '" + keyword + "' найдены:");
            for (Product product : matchingProducts) {
                System.out.printf("%d | %s | %.2f руб.\n",
                        product.getId(),
                        product.getName(),
                        product.getPrice());
            }
        }
    }

    private static void addToCart() {
        int productId = userInput("Введите ID товара для добавления в корзину: ");
        Optional<Product> productOptional = productRepo.getAll().stream()
                .filter(p -> p.getId() == productId)
                .findFirst();
        if (productOptional.isPresent()) {
            cart.addItem(productOptional.get());
            System.out.println("Товар успешно добавлен в корзину!");
        } else {
            System.out.println("Такой товар не найден.");
        }
    }

    private static void viewCart() {
        List<Product> itemsInCart = cart.getItems();
        if (!itemsInCart.isEmpty()) {
            for (Product item : itemsInCart) {
                System.out.printf("%d | %s | %.2f руб.\n",
                        item.getId(),
                        item.getName(),
                        item.getPrice());
            }
        } else {
            System.out.println("Корзина пуста.");
        }
    }

    private static void placeOrder() {
        if (cart.getItems().isEmpty()) {
            System.out.println("В вашей корзине нечего оформлять.");
            return;
        }
        Order order = new Order(cart.getItems());
        System.out.println("Заказ успешно оформлен! Ваш номер заказа: " + order.getOrderNumber());
        cart.clear(); // Очищаем корзину после оформления заказа
    }

    // метод для общения с пользователем через консоль
    private static int userInput(String promptMessage) {
        System.out.print(promptMessage); // Один раз выводим приглашение перед началом ввода

        while (true) {
            String input = scanner.nextLine().trim(); // Получаем введённую строку

            try {
                return Integer.parseInt(input); // Пробуем преобразовать строку в число
            } catch (NumberFormatException e) {
                System.err.print("Ошибка! Введите целое число: "); // Сообщаем об ошибке и просим попробовать ещё раз
            }
        }
    }
}