import java.util.List;

// Базовый интерфейс репозитория

interface IRepository<T> {
    List<T> getAll();
    void save(T entity);
}