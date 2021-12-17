package pbo.project.view;

import pbo.project.models.PinjamModel;

import java.sql.SQLException;
import java.util.Scanner;

public class Pinjam {
    private PinjamModel pinjam = new PinjamModel();
    private Buku buku = new Buku();
    private Scanner sc_int = new Scanner(System.in);
    private Scanner sc = new Scanner(System.in);

    public Pinjam() throws SQLException {
    }

    public void tambahPeminjaman(String nim,int id_user) throws SQLException {
        System.out.println("MELAKUKAN PEMINJAMAN");
        System.out.print("ID Buku : ");
        int id = sc_int.nextInt();

        if (buku.getStatusPinjam(id) == 0 ) {
            this.pinjam.tambahDataPinjam(nim,id,id_user);
            buku.editStatusPeminjaman(1,id);
            System.out.println("Peminjaman berhasil");
        }else {
            System.out.println("Buku ini sedang dipinjam");
        }




    }
    public void kembalikanBuku() throws SQLException {
        System.out.println("KEMBALIKAN BUKU");
        System.out.print("ID Buku : ");
        int id = sc_int.nextInt();
        buku.editStatusPeminjaman(0,id);
    }

    public void getRiwayatPinjamMahasiswa(String nim) throws SQLException {
        pinjam.getRiwayatPinjamMahasiswa(nim);
    }
    public void showPinjam() throws SQLException {
        pinjam.showPeminjaman();
    }
}
