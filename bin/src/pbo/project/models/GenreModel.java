package pbo.project.models;



import pbo.project.util.CommandLineTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreModel extends BaseModel{

    public GenreModel() throws SQLException {
        super();
    }

    public void addGenre(String genre) throws SQLException {


        final String INSERT_DATA_GENRE = "INSERT INTO genre" + " (genre) VALUES " + " (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATA_GENRE);


        preparedStatement.setString(1,genre);
        preparedStatement.executeUpdate();
    }

    public void getAllGenre() throws SQLException {
        final CommandLineTable table = new CommandLineTable();

        final String SHOW_ALL_GENRE= "select * from genre";
        PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL_GENRE);
        System.out.println(preparedStatement);

        ResultSet rs = preparedStatement.executeQuery();

        table.setShowVerticalLines(true);
        table.setHeaders("id_genre","nama");

        while (rs.next()){
            String id_genre = rs.getString("id_genre");
            String genre = rs.getString("genre");

            table.addRow(id_genre,genre);

        }
        table.print();
    }

    public String getGenreById(int id) throws SQLException {
        String genre ="";
        final String SHOW_GENRE_BY_ID= "select id_genre,genre from genre where id_genre=?";
        PreparedStatement preparedStatement = connection.prepareStatement(SHOW_GENRE_BY_ID);

        preparedStatement.setInt(1,id);

        ResultSet rs = preparedStatement.executeQuery();


        while (rs.next()){
            genre = rs.getString("genre");
        }
        return genre;
    }
}
