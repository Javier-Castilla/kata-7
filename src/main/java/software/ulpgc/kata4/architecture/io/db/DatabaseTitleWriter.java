package software.ulpgc.kata4.architecture.io.db;

import com.google.gson.Gson;
import software.ulpgc.kata4.architecture.io.TitleWriter;
import software.ulpgc.kata4.architecture.model.Title;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static java.sql.Types.*;


public class DatabaseTitleWriter implements TitleWriter {
    private final Connection connection;
    private final PreparedStatement preparedStatement;

    public static DatabaseTitleWriter open(File file) throws SQLException {
        return new DatabaseTitleWriter(DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath()));
    }

    private DatabaseTitleWriter(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(false);
        createTables();
        this.preparedStatement = createPreparedStatement();
    }

    private PreparedStatement createPreparedStatement() throws SQLException {
        return this.connection.prepareStatement(DatabaseQuery.insert());
    }

    private void createTables() throws SQLException {
        this.connection.createStatement().execute(DatabaseQuery.createTable());
    }

    private record Parameter(int index, Object value, int type) {}

    @Override
    public void write(Title title) throws IOException {
        try {
            statementFor(title).execute();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private PreparedStatement statementFor(Title title) throws SQLException {
        preparedStatement.clearParameters();
        for (Parameter parameter : parameterOf(title)) this.define(parameter);
        return preparedStatement;
    }

    private void define(Parameter parameter) throws SQLException {
        if (parameter.value == null) preparedStatement.setNull(parameter.index, parameter.type);
        else preparedStatement.setObject(parameter.index, parameter.value);
    }

    private List<Parameter> parameterOf(Title title) {
        return  List.of(
                new Parameter(1, title.id(), NVARCHAR),
                new Parameter(2, title.titleType(), NVARCHAR),
                new Parameter(3, title.primaryTitle(), NVARCHAR),
                new Parameter(4, title.originalTitle(), NVARCHAR),
                new Parameter(5, title.isAdult(), BOOLEAN),
                new Parameter(6, title.startYear().getValue(), INTEGER),
                new Parameter(7, title.endYear().getValue(), INTEGER),
                new Parameter(8, title.runtimeMinutes(), INTEGER),
                new Parameter(9, new Gson().toJson(title.genres()), NVARCHAR)
        );
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
