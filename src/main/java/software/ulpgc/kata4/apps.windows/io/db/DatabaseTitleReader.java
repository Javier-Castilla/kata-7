package software.ulpgc.kata4.apps.windows.io.db;

import software.ulpgc.kata4.architecture.io.TitleReader;
import software.ulpgc.kata4.architecture.model.Title;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseTitleReader implements TitleReader {
    private final Connection connection;
    private final PreparedStatement preparedStatement;

    public static DatabaseTitleReader open(File file) throws SQLException {
        return new DatabaseTitleReader(DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath()));
    }

    private DatabaseTitleReader(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(false);
        this.preparedStatement = createPreparedStatement();
    }

    private PreparedStatement createPreparedStatement() throws SQLException {
        return this.connection.prepareStatement(DatabaseQuery.select());
    }

    @Override
    public List<Title> read() throws IOException {
        try {
            List<Title> titles = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) titles.add(DatabaseTitleAdapter.adapt(resultSet));
            return titles;
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void close() {
        try {
            this.connection.commit();
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
