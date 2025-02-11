package javaTasks.tasks.library.storage;

import javaTasks.tasks.library.config.DatabaseConnectionManager;
import javaTasks.tasks.library.exceptions.BookRepositoryException;
import javaTasks.tasks.library.models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepository implements Repository<Book> {

    private final DatabaseConnectionManager connectionManager;

    private static final String INSERT = "INSERT INTO libSchema.book(name, count_pages, author_id) VALUES (?, ?, ?)";

    private static final String SELECT_ALL = "SELECT * FROM libSchema.book";

    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id = ?";

    private static final String SELECT_ALL_BY_VALUES = SELECT_ALL + " WHERE name=? and count_pages=? and author_id=?";

    private static final String DELETE_BY_ID = "DELETE FROM libSchema.book WHERE id = ?";

    public BookRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void add(Book object) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2, object.getCountPages());
            preparedStatement.setLong(3, object.getAuthorId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new BookRepositoryException("Error when adding a book!", e);
        }
    }

    @Override
    public void delete(Book object) {
        Book book = getByValues(object);
        deleteByIndex(book.getId());
    }

    @Override
    public void deleteByIndex(int index) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, index);
            statement.execute();
        } catch (SQLException ex) {
            throw new BookRepositoryException("Error when deleting a book!", ex);
        }
    }

    @Override
    public Book getByIndex(int index) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, index);
            ResultSet resultSet = statement.executeQuery();
            return mapBooks(resultSet).stream().findFirst().orElse(null);
        } catch (SQLException ex) {
            throw new BookRepositoryException("Error when getting a book by index!", ex);
        }
    }

    @Override
    public List<Book> get() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            return mapBooks(resultSet);
        } catch (SQLException ex) {
            throw new BookRepositoryException("Error when getting books!", ex);
        }
    }

    @Override
    public String toString() {
        return get().stream().map(Book::print).collect(Collectors.joining("\n"));
    }

    private List<Book> mapBooks(ResultSet resultSet) throws SQLException {
        List<Book> books = new ArrayList<>();
        int id, countPages;
        String name;
        long authorId;
        while(resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            countPages = resultSet.getInt("count_pages");
            authorId = resultSet.getLong("author_id");
            books.add(new Book(name, countPages, authorId, id));
        }
        return books;
    }

    public Book getByValues(Book book) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_VALUES)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getCountPages());
            preparedStatement.setLong(3, book.getAuthorId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapBooks(resultSet).stream().findFirst().orElse(null);
        } catch (SQLException e) {
            throw new BookRepositoryException("Error when getting a book by index!", e);
        }
    }
}
