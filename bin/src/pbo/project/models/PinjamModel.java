package pbo.project.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PinjamModel extends BaseModel{
    private final String tambah_data_pinjam = "insert into peminjaman (nim,id_buku,id_user,tanggal_pinjam,tanggal_kembali) values (?,?,?,CURRENT_DATE,CURRENT_DATE+7)";


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

}
