package pbo.project.view;

import pbo.project.models.BukuModel;
import pbo.project.models.GenreModel;

import java.sql.SQLException;
import java.util.Scanner;

public class Buku {
    private Scanner sc = new Scanner(System.in);
    private Scanner sc_int = new Scanner(System.in);
    private BukuModel buku = new BukuModel();
    private GenreModel genre = new GenreModel();

    public Buku() throws SQLException {
    }

    public void showBuku() throws SQLException {
        buku.showBuku();
    }

    public void editBuku() throws SQLException {
        System.out.println("EDIT DATA BUKU");
        System.out.println("1. Edit Nama\n2. Edit Tahun\n3. Edit Penerbit\n4. Edit Pengarang\n5. Edit Genre");
        System.out.print("Pilih : ");
        int pilih = sc_int.nextInt();
        showBuku();
        if(pilih==1){
            System.out.print("ID Buku : ");
            int id = sc.nextInt();
            System.out.print("Data nama baru : ");
            String nama = sc.nextLine();
            buku.editNamaBuku(nama,id);
        }else if(pilih==2){
            System.out.print("ID Buku : ");
            int id = sc.nextInt();
            System.out.print("Data tahun baru : ");
            int tahun = sc.nextInt();
            buku.editTahunBuku(tahun,id);
        }else if(pilih==3){
            System.out.print("ID Buku : ");
            int id = sc.nextInt();
            System.out.print("Data penerbit baru : ");
            String penerbit = sc.nextLine();
            buku.editPenerbitBuku(penerbit,id);
        }else if(pilih==4){
            System.out.print("ID Buku : ");
            int id = sc.nextInt();
            System.out.print("Data pengarang baru : ");
            String pengarang = sc.nextLine();
            buku.editPengarangBuku(pengarang,id);
        }else if(pilih==5){
            System.out.print("ID Buku : ");
            int id = sc.nextInt();
            genre.getAllGenre();
            System.out.print("ID genre baru : ");
            int genre = sc_int.nextInt();
            buku.editGenreBuku(genre,id);

        }

    }
    public void deleteBuku() throws SQLException {
        showBuku();
        System.out.println("HAPUS BUKU");
        System.out.print("ID Buku : ");
        int id = sc.nextInt();
        buku.deleteBuku(id);

    }
    public void addBuku() throws SQLException {
        System.out.print("Nama buku : ");
        String Buku = sc.nextLine();
        System.out.print("Penerbit : ");
        String Penerbit = sc.nextLine();
        System.out.print("Pengarang : ");
        String Pengarang = sc.nextLine();
        System.out.print("Tahun terbit : ");
        int Tahun = sc.nextInt();
        System.out.print("Kode genre : ");
        int genre = sc.nextInt();

        buku.addBuku(Buku,Penerbit,Pengarang,Tahun,genre);
    }
    public void tambahGenre() throws SQLException {
        System.out.println("TAMBAH GENRE");
        System.out.print("Genre : ");
        String genre_baru = sc.nextLine();
        genre.addGenre(genre_baru);
    }
    public void lihatGenre() throws SQLException {
        genre.getAllGenre();
    }
    public void editStatusPeminjaman(int status,int id_buku) throws SQLException {

        this.buku.updatePeminjaman(status,id_buku);
    }

    public int getStatusPinjam(int id) throws SQLException {
        return buku.getStatusPinjam(id);
    }
}
