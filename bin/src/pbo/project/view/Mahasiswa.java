package pbo.project.view;

import pbo.project.models.MahasiswaModel;

import java.sql.SQLException;
import java.util.Scanner;

public class Mahasiswa {
    Scanner sc = new Scanner(System.in);
    Scanner sc_int = new Scanner(System.in);
    MahasiswaModel mahasiswa = new MahasiswaModel();

    public Mahasiswa() throws SQLException {
    }

    public void deleteMahasiswa() throws SQLException {
        System.out.println("HAPUS DATA MAHASISWA");
        System.out.print("NIM : ");
        String nim = sc.nextLine();
        mahasiswa.deleteMahasiswa(nim);
    }
    public void tambahMahasiswa() throws SQLException {
        System.out.println("TAMBAH MAHASISWA");
        System.out.print("NIM : ");
        String nim = sc.nextLine();

        System.out.print("NAMA : ");
        String nama = sc.nextLine();
        mahasiswa.addMahasiswa(nim,nama);

    }

    public void lihatMahasiswa() throws SQLException {
        mahasiswa.getAllMahasiswa();
    }

    public void updateMahasiswa() throws SQLException {
        System.out.println("Pilih data yang ingin diubah :");
        System.out.println("1. Nama\n2. NIM");
        System.out.print("Pilihan : ");
        int Pilihan = sc_int.nextInt();

        if(Pilihan == 1){
            System.out.println("Masukkan NIM mahasiswa yang datanya ingin diubah");
            System.out.print("NIM : ");
            String nim = sc.nextLine();
            System.out.println("Masukkan Perubahan ");
            System.out.print("Nama : ");
            String nama = sc.nextLine();
            mahasiswa.updateMahasiswa(Pilihan,nama,nim);
        }
        else if(Pilihan == 2){
            Scanner sc1 = new Scanner(System.in);

            System.out.println("Masukkan NIM mahasiswa yang datanya ingin diubah");
            System.out.print("NIM : ");
            String nim = sc1.nextLine();
            System.out.println("Masukkan Perubahan ");
            System.out.print("NIM : ");
            String nim_baru = sc1.nextLine();
            mahasiswa.updateMahasiswa(Pilihan,nim_baru,nim);

        }

    }

}
