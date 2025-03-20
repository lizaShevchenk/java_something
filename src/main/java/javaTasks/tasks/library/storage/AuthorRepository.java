package javaTasks.tasks.library.storage;

import javaTasks.tasks.library.author.Author;
import javaTasks.tasks.library.config.DatabaseConnectionManager;
import javaTasks.tasks.library.config.HibernateConnectionManager;
import javaTasks.tasks.library.exceptions.AuthorRepositoryException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorRepository implements Repository<Author> {

//    private final DatabaseConnectionManager connectionManager;
    private final HibernateConnectionManager connectionManager;

//    private static final String INSERT = "INSERT INTO libSchema.author(first_name, last_name, email) VALUES (?, ?, ?)";
//
//    private static final String SELECT_ALL = "SELECT * FROM libschema.author";
//
//    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id=?";
//
//    private static final String SELECT_ALL_BY_VALUES = SELECT_ALL + " WHERE first_name=? and last_name=? and email=?";
//
//    private static final String DELETE_BY_ID = "DELETE FROM libschema.author WHERE id = ?";
//
//    private static final String DELETE_BY_EMAIL = "DELETE FROM libschema.author WHERE email = ?";

//    public AuthorRepository(DatabaseConnectionManager connectionManager) {
//        this.connectionManager = connectionManager;
//    }

    public AuthorRepository() {
        this.connectionManager = new HibernateConnectionManager();
    }

    @Override
    public void add(Author author) {
        Transaction transaction = null;
        try (Session session = connectionManager.openSession()) {
            transaction = session.beginTransaction();
            session.merge(author);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new AuthorRepositoryException("Error when adding an author", e);
        }

    }

    @Override
    public void delete(Author object) {

    }

    @Override
    public void deleteByIndex(int index) {

    }

    @Override
    public Author getByIndex(int index) {
        try (Session session = connectionManager.openSession()) {
            return session.find(Author.class, index);
        } catch (RuntimeException e) {
            throw new AuthorRepositoryException("Error getting author by id", e);
        }
    }

    @Override
    public List<Author> get() {
        try (Session session = connectionManager.openSession()) {
            Query<Author> query = session.createQuery("FROM Author", Author.class);
            return query.list();
        } catch (RuntimeException e) {
            throw new AuthorRepositoryException("Error when getting authors", e);
        }
    }

//    @Override
//    public void add(Author object) {
//        try (Connection connection = connectionManager.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
//            preparedStatement.setString(1, object.getFirstName());
//            preparedStatement.setString(2, object.getLastName());
//            preparedStatement.setString(3, object.getEmail());
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            throw new AuthorRepositoryException("Error when adding an author!", e);
//        }
//    }

//    @Override
//    public void delete(Author object) {
//        int index = Long.valueOf(getByValues(object).getId()).intValue();
//        deleteByIndex(index);
//    }
//
//    @Override
//    public void deleteByIndex(int index) {
//        try (Connection connection = connectionManager.getConnection();
//             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
//            statement.setInt(1, index);
//            statement.execute();
//        } catch (SQLException ex) {
//            throw new AuthorRepositoryException("Error when deleting an author by index!", ex);
//        }
//    }
//
//    public void deleteByEmail(String email) {
//        try (Connection connection = connectionManager.getConnection();
//             PreparedStatement statement = connection.prepareStatement(DELETE_BY_EMAIL)) {
//            statement.setString(1, email);
//            statement.execute();
//        } catch (SQLException ex) {
//            throw new AuthorRepositoryException("Error when deleting an author by email!", ex);
//        }
//    }
//
//    @Override
//    public Author getByIndex(int index) {
//        try (Connection connection = connectionManager.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
//            statement.setInt(1, index);
//            ResultSet resultSet = statement.executeQuery();
//            return mapAuthors(resultSet)
//                    .stream()
//                    .findFirst()
//                    .orElseThrow(() -> new AuthorRepositoryException("There is no author by index " + index));
//        } catch (SQLException ex) {
//            throw new AuthorRepositoryException("Error when getting an author by index!", ex);
//        }
//    }
//
//    @Override
//    public List<Author> get() {
//        try (Connection connection = connectionManager.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
//            ResultSet resultSet = statement.executeQuery();
//            return mapAuthors(resultSet);
//        } catch (SQLException ex) {
//            throw new AuthorRepositoryException("Error when getting authors!", ex);
//        }
//    }
//
//    public boolean checkIfAuthorExistByEmail(String email) {
//        return get().stream().anyMatch(a -> a.getEmail().equals(email));
//    }

    public boolean checkIfAuthorExistByEmail(String email) {
        try (Session session = connectionManager.openSession()) {
            Query<Author> query = session.createQuery("FROM Author WHERE email = :email", Author.class);
            query.setParameter("email", email);
            return query.uniqueResultOptional().isPresent();
        } catch (RuntimeException e) {
            throw new AuthorRepositoryException("Error when checking if author exists by email", e);
        }
    }

    @Override
    public String toString() {
        return get().stream().map(Author::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public Author getByValues(Author object) {
        return null;
    }
//
//    public Author getByValues(Author author) {
//        try (Connection connection = connectionManager.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_VALUES)) {
//            preparedStatement.setString(1, author.getFirstName());
//            preparedStatement.setString(2, author.getLastName());
//            preparedStatement.setString(3, author.getEmail());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            return mapAuthors(resultSet)
//                    .stream()
//                    .findFirst()
//                    .orElseThrow(() -> new AuthorRepositoryException("There is no author " + author));
//        } catch (SQLException e) {
//            throw new AuthorRepositoryException("Error when getting an author by index!", e);
//        }
//    }
//
//    private List<Author> mapAuthors(ResultSet resultSet) throws SQLException {
//        List<Author> authors = new ArrayList<>();
//        while (resultSet.next()) {
//            Author author = new Author();
//            author.setId(resultSet.getInt("id"));
//            author.setFirstName(resultSet.getString("first_name"));
//            author.setLastName(resultSet.getString("last_name"));
//            author.setEmail(resultSet.getString("email"));
//            authors.add(author);
//        }
//        return authors;
//    }
}
