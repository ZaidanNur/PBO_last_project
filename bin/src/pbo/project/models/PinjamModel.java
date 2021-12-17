package pbo.project.models;

import pbo.project.util.CommandLineTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PinjamModel extends BaseModel{
    private final String tambah_data_pinjam = "insert into peminjaman (nim,id_buku,id_user,tanggal_pinjam,tanggal_kembali) values (?,?,?,CURRENT_DATE,CURRENT_DATE+7)";
    private final String lihat_buku_dipinjam_by_nim = "select peminjaman.nim,buku.id_buku,buku.buku,peminjaman.tanggal_pinjam,peminjaman.tanggal_kembali from peminjaman left join buku on peminjaman.id_buku = buku.id_buku where nim = ?";
    private final String lihat_pinjam = "select peminjaman.nim,buku.id_buku,buku.buku,peminjaman.tanggal_pinjam,peminjaman.tanggal_kembali from peminjaman left join buku on peminjaman.id_buku = buku.id_buku";


    public PinjamModel() throws SQLException {
    super();
    }

    public void tambahDataPinjam(String nim,int id_buku,int id_user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(this.tambah_data_pinjam);
        preparedStatement.setString(1,nim);
        preparedStatement.setInt(2,id_buku);
        preparedStatement.setInt(3,id_user);


        preparedStatement.executeUpdate();

    }

    public void getRiwayatPinjamMahasiswa(String nim) throws SQLException {
        CommandLineTable table = new CommandLineTable();
        PreparedStatement preparedStatement = connection.prepareStatement(lihat_buku_dipinjam_by_nim);
        preparedStatement.setString(1,nim);

        ResultSet rs = preparedStatement.executeQuery();

        table.setShowVerticalLines(true);
        table.setHeaders("No","ID Buku","Nama Buku","Tanggal Pinjam","Tanggal Kembali");
        int num = 1;

        while (rs.next()){
            int id = rs.getInt("id_buku");
            String nama = rs.getString("buku");
            String tgl_in = String.valueOf(rs.getDate("tanggal_pinjam"));
            String tgl_out = String.valueOf(rs.getDate("tanggal_kembali"));


            table.addRow(String.valueOf(num),String.valueOf(id),nama,tgl_in,tgl_out);
            num++;
        }
        table.print();
    }
    public void showPeminjaman() throws SQLException {
        CommandLineTable table = new CommandLineTable();
        PreparedStatement preparedStatement = connection.prepareStatement(lihat_pinjam);


        ResultSet rs = preparedStatement.executeQuery();

        table.setShowVerticalLines(true);
        table.setHeaders("No","ID Pinjam","NIM","Nama Buku","Tanggal Pinjam","Tanggal Kembali");
        int num = 1;

        while (rs.next()){
            int id = rs.getInt("id_buku");
            String nama = rs.getString("buku");
            String nim = rs.getString("nim");
            String tgl_in = String.valueOf(rs.getDate("tanggal_pinjam"));
            String tgl_out = String.valueOf(rs.getDate("tanggal_kembali"));


            table.addRow(String.valueOf(num),String.valueOf(id),nim,nama,tgl_in,tgl_out);
            num++;
        }
        table.print();
    }

}
