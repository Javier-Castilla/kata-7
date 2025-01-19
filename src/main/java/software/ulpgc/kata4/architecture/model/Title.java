package software.ulpgc.kata4.architecture.model;

import java.time.Year;

public record Title(String id, TitleType titleType, String primaryTitle, String originalTitle, boolean isAdult, Year startYear, Year endYear, int runtimeMinutes, Genre[] genres) {
    public enum TitleType {
        VideoGame,
        TvPilot,
        Movie,
        TvSeries,
        TvMiniSeries,
        Short,
        TvSpecial,
        TvShort,
        Video,
        TvMovie,
        TvEpisode
    }

    public enum Genre {
        Action,
        Adult,
        Adventure,
        Animation,
        Biography,
        Comedy,
        Crime,
        Documentary,
        Drama,
        Family,
        Fantasy,
        FilmNoir,
        GameShow,
        History,
        Horror,
        Music,
        Musical,
        Mystery,
        News,
        RealityTV,
        Romance,
        SciFi,
        Short,
        Sport,
        TalkShow,
        Thriller,
        War,
        Western
    }
}
