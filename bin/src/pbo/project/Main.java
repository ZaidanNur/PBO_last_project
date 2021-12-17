package pbo.project;

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
        Pinjam pinjam = new Pinjam();
        Login login = new Login();
        Mahasiswa mhs = new Mahasiswa();
        Scanner sc = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        boolean login_status = false;
        boolean program_run = true;

        while(login_status != true) try {
            login_status = login.UserLogin();
            if (login_status) {
                while (program_run){
                    if(login.getRole()==0){
                        System.out.println("===================");
                        System.out.println("|   E-LIBRARY     |");
                        System.out.println("===================");
                        System.out.println("1. Lihat Buku \n2. Pinjam Buku \n3. Kembalikan Buku\n4. Riwayat Pinjam\n5. Keluar");
                        System.out.print("Pilih : ");
                        int pilihan = sc_int.nextInt();
                        if(pilihan==1){
                            buku.showBuku();
                            System.out.print("Tekan enter untuk lanjut ...");
                            sc.nextLine();
                        }else if(pilihan==2){
                            buku.showBuku();
                            pinjam.tambahPeminjaman(login.getUsername(), login.getUserId());
                            System.out.print("Tekan enter untuk lanjut ...");
                            sc.nextLine();
                        }else if(pilihan==3){
                            buku.showBuku();
                            pinjam.kembalikanBuku();

                        }else if(pilihan==4){
                            pinjam.getRiwayatPinjamMahasiswa(login.getUsername());
                            System.out.print("Tekan enter untuk lanjut ...");
                            sc.nextLine();
                        }
                        else if(pilihan==5){
                            program_run=false;
                        }

                    }else if (login.getRole()==1){
                        System.out.println("===================");
                        System.out.println("|   E-LIBRARY     |");
                        System.out.println("===================");
                        System.out.println("1. Kelola Mahasiswa\n2. Kelola Buku\n3. Lihat Riwayat Peminjaman\n4. Keluar");
                        System.out.print("Pilih : ");
                        int pilihan = sc_int.nextInt();
                        if (pilihan==1){
                            System.out.println("KELOLA DATA MAHASISWA");
                            System.out.println("1. Lihat Data Mahasiswa\n2. Tambah Data Mahasiswa\n3. Update Data Mahasiswa\n4. Hapus Data Mahasiswa");
                            System.out.print("Pilih : ");
                            int kelola_mhs = sc_int.nextInt();
                            if (kelola_mhs==1){
                                mhs.lihatMahasiswa();
                                System.out.print("Tekan enter untuk lanjut ...");
                                sc.nextLine();

                            }else if(kelola_mhs==2){
                                mhs.tambahMahasiswa();

                            }else if(kelola_mhs==3){
                                mhs.updateMahasiswa();

                            }else if(kelola_mhs==4){
                                mhs.deleteMahasiswa();
                            }


                        }else if(pilihan==2){
                            System.out.println("KELOLA DATA BUKU");
                            System.out.println("1. Lihat Data Buku\n2. Tambah Data Buku\n3. Update Data Buku\n4. Hapus Data Buku\n5. Lihat Genre\n6. Tambah Genre");
                            System.out.print("Pilih : ");
                            int kelola_buku = sc_int.nextInt();
                            if (kelola_buku==1){
                                buku.showBuku();
                                System.out.print("Tekan enter untuk lanjut ...");
                                sc.nextLine();
                            }else if(kelola_buku==2){
                                buku.addBuku();
                            }else if(kelola_buku==3){
                                buku.editBuku();

                            }else if(kelola_buku==4){
                                buku.deleteBuku();
                            }else if(kelola_buku==5){
                                buku.lihatGenre();
                                System.out.print("Tekan enter untuk lanjut ...");
                                sc.nextLine();
                            }else if(kelola_buku==6){
                                buku.tambahGenre();
                            }

                        }else if(pilihan==3){
                            pinjam.showPinjam();
                            System.out.print("Tekan enter untuk lanjut ...");
                            sc.nextLine();


                        }else if(pilihan==4){
                            program_run=false;
                        }
                    }
                }





            }else {
                System.out.println("Username atau password kurang sesuai");

            }



        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
