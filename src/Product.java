import java.util.ArrayList;
import java.util.List;

class Product {
    private int id;
    private String name;
    private double price;
    private String manufacturer;
    private List<Rating> ratings = new ArrayList<>(); // Рейтинги товара

    public Product(int id, String name, double price, String manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    // средний рейтинг продукта на основании оценок
    public double averageRating() {
        if (ratings.isEmpty()) return 0; // если у продукта нет ни одного рейтинга, возвращается 0
        return ratings.stream().mapToDouble(Rating::getValue).average().orElse(0);
    }
}