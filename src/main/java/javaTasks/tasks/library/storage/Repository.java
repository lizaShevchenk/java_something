package javaTasks.tasks.library.storage;

import java.util.List;

public interface Repository<T> {

    void add(T object);

    void delete(T object);

    void deleteByIndex(int index);

    T getByIndex(int index);

    List<T> get();

    String toString();
}
