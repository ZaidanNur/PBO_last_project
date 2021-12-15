package pbo.project.models;

import pbo.project.util.CommandLineTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MahasiswaModel extends BaseModel {

    final String INSERT_DATA_MAHASISWA = "INSERT INTO mahasiswa" + " (nim, nama) VALUES " + " (?, ?);";
    final String SHOW_ALL_MAHASISWA = "select * from mahasiswa order by nim asc ";
    final String GET_MAHASISWA_BY_ID = "select * from mahasiswa where nim = ? ";
    final String UPDATE_MAHASISWA_NAME = "update mahasiswa set nama = ? where nim = ? ";
    final String UPDATE_MAHASISWA_NIM = "update mahasiswa set nim = ? where nim = ? ";
    final String DELETE_MAHASISWA = "delete from mahasiswa where nim = ?";

    public MahasiswaModel() throws SQLException {
        super();
    }

    public String getNimMahasiswaById(String nim) throws SQLException {
        String nim_mhs = "";
        PreparedStatement preparedStatement = connection.prepareStatement(GET_MAHASISWA_BY_ID);
        preparedStatement.setString(1,nim);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            nim_mhs = rs.getString("nim");
        }
        return nim_mhs;
    }

    public void deleteMahasiswa(String nim) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MAHASISWA);
        preparedStatement.setString(1,nim);
        preparedStatement.executeUpdate();
    }

    public void addMahasiswa(String nim, String nama) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATA_MAHASISWA);
        preparedStatement.setString(1,nim);
        preparedStatement.setString(2, nama);
        preparedStatement.executeUpdate();
    }

    public void getAllMahasiswa() throws SQLException {
        CommandLineTable table = new CommandLineTable();


        PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL_MAHASISWA);

        ResultSet rs = preparedStatement.executeQuery();

        table.setShowVerticalLines(true);
        table.setHeaders("No","NIM","Nama");
        int num = 1;

        while (rs.next()){
            String nim = rs.getString("nim");
            String name = rs.getString("nama");


            table.addRow(String.valueOf(num),nim,name);
            num++;

        }
        table.print();


    }
    public void updateMahasiswa(int Pilihan,String data,String nim) throws SQLException {
        Scanner sc = new Scanner(System.in);


        if(Pilihan == 1){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MAHASISWA_NAME);

            preparedStatement.setString(1,data);
            preparedStatement.setString(2,nim);
            preparedStatement.executeUpdate();


        }
        else if(Pilihan == 2){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MAHASISWA_NIM);

            preparedStatement.setString(1,data);
            preparedStatement.setString(2,nim);
            preparedStatement.executeUpdate();

        }


    }
}
