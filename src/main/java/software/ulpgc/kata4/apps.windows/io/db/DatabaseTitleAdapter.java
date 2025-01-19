package software.ulpgc.kata4.apps.windows.io.db;

import com.google.gson.Gson;
import software.ulpgc.kata4.architecture.model.Title;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class DatabaseTitleAdapter {
    public static Title adapt(ResultSet resultSet) throws SQLException {
        return new Title(
                resultSet.getString(1),
                toTitleType(resultSet.getString(2)),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getBoolean(5),
                toYear(resultSet.getInt(6)),
                toYear(resultSet.getInt(6)),
                resultSet.getInt(8),
                getArrayFromJson(resultSet.getString(9))
        );
    }

    private static Title.Genre[] getArrayFromJson(String string) {
        return new Gson().fromJson(string, Title.Genre[].class);
    }

    private static Year toYear(int anInt) {
        return Year.of(anInt);
    }

    private static Title.TitleType toTitleType(String string) {
        return Title.TitleType.valueOf(string);
    }
}
