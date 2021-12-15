package pbo.project;

import pbo.project.models.GenreModel;
import pbo.project.models.LibraryModel;
import pbo.project.models.MahasiswaModel;
import pbo.project.view.Buku;
import pbo.project.view.Login;
import pbo.project.view.Mahasiswa;
import pbo.project.view.Pinjam;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void printSQLException(SQLException ex){
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        Buku buku = new Buku();
        LibraryModel INSERT_DATA = new LibraryModel();
        MahasiswaModel MAHASISWA = new MahasiswaModel();
        GenreModel GENRE = new GenreModel();
        Pinjam pinjam = new Pinjam();
        Login login = new Login();
        Mahasiswa mhs = new Mahasiswa();
        Scanner sc = new Scanner(System.in);
        boolean login_status = false;

        while(login_status != true) try {
            login_status = login.UserLogin();
            if (login_status) {
                buku.showBuku();
                pinjam.tambahPeminjaman(login.getUsername(),login.getUserId());


//                mhs.lihatMahasiswa();
//                mhs.deleteMahasiswa();
//                mhs.tambahMahasiswa();
//                mhs.updateMahasiswa();
//                System.out.println(login.getRole());
            }else {
                System.out.println("Username atau password kurang sesuai");

            }

//                System.out.println("==== E-LIBRARY ====");
//                System.out.println("1. Data Mahasiswa \n2. Data Buku \n3. Pinjam Buku");

        } catch (SQLException e) {
            printSQLException(e);
//            System.out.println("Ada kesalahan saat menjalankan program : "+e.getMessage());
        }
    }
}
