package software.ulpgc.kata4.architecture.io.db;

public class DatabaseQuery {
    public static String createTable() {
        return """
                CREATE TABLE IF NOT EXISTS titles(
                    id TEXT PRIMARY KEY,
                    titleType TEXT NOT NULL,
                    primaryTitle TEXT NOT NULL,
                    originalTitle TEXT NOT NULL,
                    isAdult BOOLEAN NOT NULL,
                    startYear INTEGER,
                    endYear INTEGER,
                    runtime INTEGER,
                    genres TEXT NOT NULl
                )
                """;
    }

    public static String insert() {
        return """
                INSERT INTO titles (id, titleType, primaryTitle, originalTitle, isAdult, startYear, endYear, runtime, genres)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
    }

    public static String select() {
        return "SELECT * FROM titles";
    }
}
