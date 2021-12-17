package pbo.project.models;

import pbo.project.util.CommandLineTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BukuModel extends BaseModel{
    private GenreModel genre = new GenreModel();

    final String INSERT_DATA_BUKU = "INSERT INTO buku" + " (buku,tahun_terbit,penerbit,pengarang,genre,status_pinjam) VALUES " + " (?, ?,?,?,?,0);";
    final String SHOW_ALL_BUKU = "select * from buku order by id_buku asc";
    final String GET_BUKU = "select * from buku where id_buku = ?";
    final String EDIT_NAMA_BUKU = "update buku set buku = ? where id_buku = ?;";
    final String EDIT_TAHUN_BUKU = "update buku set tahun_terbit = ? where id_buku = ?;";
    final String EDIT_PENERBIT_BUKU = "update buku set penerbit = ? where id_buku = ?;";
    final String EDIT_PENGARANG_BUKU = "update buku set pengarang = ? where id_buku = ?;";
    final String EDIT_GENRE_BUKU = "update buku set genre = ? where id_buku = ?;";
    final String EDIT_STATUS_BUKU = "update buku set status_pinjam = ? where id_buku = ?;";
    final String DELETE_BUKU = "delete from buku where id_buku =?;";
    public BukuModel() throws SQLException {
        super();
    }

    public int getStatusPinjam(int id) throws SQLException {
        int status = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(this.GET_BUKU);
        preparedStatement.setInt(1,id);

        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            status = rs.getInt("status_pinjam");

        }
        return  status;
    }

    public void updatePeminjaman(int status,int id_buku) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(this.EDIT_STATUS_BUKU);
        preparedStatement.setInt(1,status);
        preparedStatement.setInt(2,id_buku);
        preparedStatement.executeUpdate();

    }

    public void editNamaBuku(String data,int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(EDIT_NAMA_BUKU);
        preparedStatement.setString(1,data);
        preparedStatement.setInt(2,id);
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }
    public void editPenerbitBuku(String data,int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(EDIT_PENERBIT_BUKU);
        preparedStatement.setString(1,data);
        preparedStatement.setInt(2,id);
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }
    public void editPengarangBuku(String data,int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(EDIT_PENGARANG_BUKU);
        preparedStatement.setString(1,data);
        preparedStatement.setInt(2,id);
        preparedStatement.executeUpdate();
    }
    public void editTahunBuku(int data,int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(EDIT_TAHUN_BUKU);
        preparedStatement.setInt(1,data);
        preparedStatement.setInt(2,id);
        preparedStatement.executeUpdate();
    }
    public void editGenreBuku(int data,int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(EDIT_GENRE_BUKU);
        preparedStatement.setInt(1,data);
        preparedStatement.setInt(2,id);
        preparedStatement.executeUpdate();
    }

    public void showBuku() throws SQLException {
        CommandLineTable table = new CommandLineTable();
        PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL_BUKU);

        ResultSet rs = preparedStatement.executeQuery();

        table.setShowVerticalLines(true);
        table.setHeaders("No","ID Buku","Nama Buku","Tahun","Penerbit","Pengarang","Genre","Status");
        int num = 1;

        while (rs.next()){
            String status = "";
            int id = rs.getInt("id_buku");
            String nama = rs.getString("buku");
            int tahun = rs.getInt("tahun_terbit");
            String penerbit = rs.getString("penerbit");
            String pengarang = rs.getString("pengarang");
            String genre_buku = genre.getGenreById(rs.getInt("genre"));
            int status_pinjam = rs.getInt("status_pinjam");
            if(status_pinjam==0){
                status = "Tidak dipinjam";
            }else {
                status = "Dipinjam";
            }
            table.addRow(String.valueOf(num),String.valueOf(id),nama,String.valueOf(tahun),penerbit,pengarang,genre_buku,status);
            num++;
        }
        table.print();


    }

    public void addBuku(String buku,String penerbit,String pengarang,int tahun,int genre) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATA_BUKU);

        preparedStatement.setString(1,buku);
        preparedStatement.setInt(2, tahun);
        preparedStatement.setString(3, penerbit);
        preparedStatement.setString(4, pengarang);
        preparedStatement.setInt(5, genre);
        preparedStatement.executeUpdate();
    }
    public void deleteBuku(int id_buku) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BUKU);
        preparedStatement.setInt(1,id_buku);
        preparedStatement.executeUpdate();

    }
}
