package tasks.library.storage;

public interface Repository<T> {

    void add(T object);

    void delete(T object);

    void deleteByIndex(int index);

    T getByIndex(int index);

    <T> T getAll();
}
