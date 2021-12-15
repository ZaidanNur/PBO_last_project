package pbo.project.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibraryModel extends BaseModel{

    public LibraryModel() throws SQLException {
        super();
    }



    public void addGenre(String genre) throws SQLException {


        final String INSERT_DATA_GENRE = "INSERT INTO genre" + " (genre) VALUES " + " (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATA_GENRE);



        preparedStatement.setString(1,genre);
        preparedStatement.executeUpdate();


    }

    public void showMahasiswa(){
        final String query = "select nim,nama from mahasiswa where id=?";




    }





}
