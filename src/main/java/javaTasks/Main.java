package javaTasks;

import javaTasks.postgresql.config.DatabaseConnectionManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
//        Dispatcher.initialise(); previous task

        String filePath = "";

        try (Connection connection = new DatabaseConnectionManager("localhost",
                "java_somethingdb",
                "libuser", "libPwd").getConnection()) {
            String script = readSQLScript(filePath);
//            executeScript(connection, script);done
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readSQLScript(String filePath) throws IOException {
        StringBuilder script = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                script.append(line).append("\n");
            }
        }
        return script.toString();
    }

    public static void executeScript(Connection connection, String script) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String[] sqlCommands = script.split(";");
            for (String sqlCommand : sqlCommands) {
                if (!sqlCommand.trim().isEmpty()) {
                    statement.addBatch(sqlCommand);
                }
            }
            statement.executeBatch();
        }
    }
}
