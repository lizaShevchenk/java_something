package javaTasks.tasks.library.storage;

import javaTasks.tasks.library.config.DatabaseConnectionManager;
import javaTasks.tasks.library.exceptions.JournalRepositoryException;
import javaTasks.tasks.library.models.Journal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.Year;

public class JournalRepository implements Repository<Journal> {

    private final DatabaseConnectionManager connectionManager;

    private static final String INSERT = "INSERT INTO libSchema.journal(name, count_pages, number, publication_year) VALUES (?, ?, ?, ?)";

    private static final String SELECT_ALL = "SELECT * FROM libSchema.journal";

    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id = ?";

    private static final String SELECT_ALL_BY_VALUES = SELECT_ALL + " WHERE name=? and count_pages=? and number=? and publication_year=?";

    private static final String DELETE_BY_ID = "DELETE FROM libSchema.journal WHERE id = ?";

    public JournalRepository(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void add(Journal entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getCountPages());
            preparedStatement.setInt(3, entity.getNumber());
            preparedStatement.setInt(4, entity.getPublicationYear());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new JournalRepositoryException("Error when adding a journal!", e);
        }
    }

    @Override
    public void delete(Journal object) {
        Journal journal = getByValues(object);
        deleteByIndex(journal.getId());
    }

    @Override
    public void deleteByIndex(int index) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, index);
            statement.execute();
        } catch (SQLException ex) {
            throw new JournalRepositoryException("Error when deleting a journal!", ex);
        }
    }

    @Override
    public Journal getByIndex(int index) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, index);
            ResultSet resultSet = statement.executeQuery();
            return mapJournals(resultSet).stream().findFirst().orElse(null);
        } catch (SQLException ex) {
            throw new JournalRepositoryException("Error when getting a journal by index!", ex);
        }
    }

    @Override
    public List<Journal> get() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            return mapJournals(resultSet);
        } catch (SQLException ex) {
            throw new JournalRepositoryException("Error when getting a journals!", ex);
        }
    }

    @Override
    public String toString() {
        return get().stream().map(Journal::print).collect(Collectors.joining("\n"));
    }

    public Journal getByValues(Journal journal) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_VALUES)) {
            preparedStatement.setString(1, journal.getName());
            preparedStatement.setInt(2, journal.getCountPages());
            preparedStatement.setInt(3, journal.getNumber());
            preparedStatement.setInt(4, journal.getPublicationYear());
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapJournals(resultSet).stream().findFirst().orElse(null);
        } catch (SQLException e) {
            throw new JournalRepositoryException("Error when getting a journal by index!", e);
        }
    }

    public boolean checkPublicationYearByPattern(int publicationYearStr) throws IllegalArgumentException {
        String currentYear = Year.now().toString();
        String yearPattern = String.format("(19\\d{2}|20[01]\\d|202[0-%s])", currentYear.substring(currentYear.length() - 1));

        return Integer.toString(publicationYearStr).matches(yearPattern);
    }

    private List<Journal> mapJournals(ResultSet resultSet) throws SQLException {
        List<Journal> journals = new ArrayList<>();
        int id, number, publicationYear, countPages;
        String name;
        while(resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            countPages = resultSet.getInt("count_pages");
            number = resultSet.getInt("number");
            publicationYear = resultSet.getInt("publication_year");
            journals.add(new Journal(id, name, countPages, number, publicationYear));
        }
        return journals;
    }
}
