package eventwithDB;

import java.util.Random;
import com.mysql.jdbc.Connection;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;//UNTUK EXCEPTION BAGIANDATE KALO GASALAH
import java.util.logging.Logger;
import java.util.ArrayList;

public class main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> Tanggal = new ArrayList<String>();
        ArrayList<Date> Tanggal_ = new ArrayList<Date>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        
//        HAPUS OTOMATIS EVENT OUT OF DATE
        try {
            String sqlGetTanggal = "select tanggal from event";
            java.sql.Connection connGetTanggal;
            connGetTanggal = (Connection) config.configDB();
            java.sql.Statement stmGetTanggal = connGetTanggal.createStatement();
            java.sql.ResultSet resGetTanggal = stmGetTanggal.executeQuery(sqlGetTanggal);
            while (resGetTanggal.next()) {
                String tanggaldb = (resGetTanggal.getString("tanggal"));
                Tanggal.add(tanggaldb);//menyimpan ke arraylist tanggal(yg string)
            }

            Date currentDate = new Date();//ambil tanggal saat ini
            for (int i = 0; i < Tanggal.size(); i++) {
                try {
                    Date cekTanggal = formatter.parse(Tanggal.get(i));//mengubah tipe tanggal dari string ke date
                    Tanggal_.add(cekTanggal);
                    int hasilPerbandingan = Tanggal_.get(i).compareTo(currentDate);
                    if (hasilPerbandingan == -1 || hasilPerbandingan == 0) {
                        try {
                            String sqlTgl = ("delete from event where tanggal ='" + Tanggal.get(i) + "'");
                            java.sql.Connection conn = (Connection) config.configDB();
                            java.sql.PreparedStatement pst = conn.prepareStatement(sqlTgl);
                            pst.execute();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean awal = true;
        while (awal == true) {

            System.out.println("__________________________________________________________________________________________________________");
            System.out.println("Program Manajemen Event");
            System.out.println("__________________________________________________________________________________________________________");
            System.out.print("Login Sebagai\n1. Admin\n2. Pembeli\n3. Registrasi\n4. Exit\n=> ");
            int pilih = Integer.parseInt(br.readLine());
            switch (pilih) {
                case 1 -> {
                    System.out.println("__________________________________________________________________________________________________________");
                    System.out.println("Menu Admin");
                    System.out.println("Login");
                    System.out.print("Username\t: ");
                    String username = br.readLine();
                    System.out.print("Password\t: ");
                    String password = br.readLine();
                    Admin objAdmin = new Admin();
                    objAdmin.login(username, password);
                    if (objAdmin.status == 1) {
                        System.out.println("Login Berhasil !!!");
                        try {
                            boolean ulang = true;
                            while (ulang == true) {
                                System.out.println("__________________________________________________________________________________________________________");
                                System.out.print("1. Lihat Daftar Event\n2. Tambah Event\n3. Edit Event\n4. Hapus Event\n5. Log-out\n=> ");
                                int pilih2 = Integer.parseInt(br.readLine());
                                switch (pilih2) {
                                    case 1 -> {
                                        System.out.println("_________________________");
                                        System.out.println("Daftar Event");
                                        objAdmin.read();
                                    }
                                    case 2 -> {
                                        boolean ulang2 = true;
                                        while (ulang2 == true) {
                                            System.out.println("_________________________");
                                            System.out.print("Tambah Event\n1. Konser\n2. Pameran\n3. Pentas Seni\n4. Exit\n=> ");
                                            int pilih3 = Integer.parseInt(br.readLine());
                                            if (pilih3 == 4) {
                                                System.out.println("Keluar");
                                                ulang2 = false;
                                            }
                                            System.out.print("Id Event\t: ");
                                            int id = Integer.parseInt(br.readLine());
                                            System.out.print("Nama Konser\t: ");
                                            String nama = br.readLine();
                                            if (pilih3 == 1) {
                                                System.out.print("Performer\t: ");
                                            } else if (pilih3 == 2) {
                                                System.out.println("Seniman\t: ");
                                            } else if (pilih3 == 3) {
                                                System.out.println("Grub Senii\t: ");
                                            } else {
                                                System.out.println("Inputan tidak tersedia !!!");
                                            }
                                            String performer = br.readLine();
                                            System.out.print("Harga Tiket\t: ");
                                            int harga = Integer.parseInt(br.readLine());
                                            System.out.print("Tanggal (dd/mm//yy)\t: ");
                                            String tanggal = br.readLine();
                                            System.out.print("Batas Peserta\t: ");
                                            int peserta = Integer.parseInt(br.readLine());
                                            System.out.print("Waktu (wita)\t: ");
                                            String waktu = br.readLine();
                                            objAdmin.create(pilih3, id, nama, harga, tanggal, peserta, performer, waktu);
                                        }

                                    }
                                    case 3 -> {
                                        //EDIT
                                        System.out.println("_________________________");
                                        System.out.println("Edit Event");
                                        System.out.print("ID event yang akan diedit\t\n=> ");
                                        int id = Integer.parseInt(br.readLine());
                                        System.out.print("Nama Event baru\t\t: ");
                                        String nama = br.readLine();
                                        System.out.print("Performer Event\t\t: ");
                                        String x = br.readLine();
                                        System.out.print("Tanggal event baru\t: ");
                                        String tanggal = br.readLine();
                                        System.out.print("Harga Tiket Baru\t: ");
                                        int harga = Integer.parseInt(br.readLine());
                                        System.out.print("Jumlah partisipan baru\t: ");
                                        int partisipan = Integer.parseInt(br.readLine());
                                        System.out.print("Waktu baru(wita)\t: ");
                                        String waktu = br.readLine();
                                        Admin obj = new Admin();
                                        objAdmin.edit(id, nama, x, tanggal, harga, partisipan, waktu);
                                    }
                                    case 4 -> {
                                        System.out.println("_________________________");
                                        System.out.println("Hapus Event");
                                        System.out.print("Id event yang ingin dihapus\t: ");
                                        String id = br.readLine();
                                        objAdmin.delete(id);
                                    }
                                    case 5 -> {
                                        ulang = false;
                                        awal = true;
                                    }
                                    default -> {
                                        System.out.println("Tidak Tersedia");
                                    }
                                }
                            }

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (objAdmin.status == 0) {
                        System.out.println("Username atau Password Salah !!!");
                    }

                }
                //MENU PEMBELI
                case 2 -> {
                    System.out.println("__________________________________________________________________________________________________________");
                    System.out.println("Menu Pembeli");
                    System.out.println("Login");
                    System.out.print("Username\t: ");
                    String username = br.readLine();
                    System.out.print("Password\t: ");
                    String password = br.readLine();
                    Pembeli objPembeli = new Pembeli();
                    objPembeli.login(username, password);
                    if (objPembeli.status == 1) {

                        try {
                            System.out.println("Login Berhasil !!");
                            boolean ulang = true;
                            while (ulang == true) {
                                System.out.println("_________________________");
                                System.out.print("1. Beli Tiket\n2. Cek Saldo\n3. Log-out\n=> ");
                                int pilih3 = Integer.parseInt(br.readLine());
                                switch (pilih3) {
                                    case 1 -> {
                                        System.out.println("_________________________\nBeli Tiket\n");
                                        objPembeli.read();
                                        System.out.print("\nId tiket yang dipilih\t: ");
                                        int pilihId = Integer.parseInt(br.readLine());
                                        System.out.print("Jumlah Tiket\t\t: ");
                                        int jumlahTiket = Integer.parseInt(br.readLine());
                                        objPembeli.beli(pilihId, jumlahTiket, username);
                                    }
                                    case 2 -> {
                                        objPembeli.cekSaldo(username);
                                    }
                                    case 3 -> {
                                        ulang = false;
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (objPembeli.status == 0) {
                        System.out.println("Username atau password salah !!!");
                    }
                }

//                REGISTRASI
                case 3 -> {
                    System.out.println("__________________________________________________________________________________________________________");
                    System.out.println("Registrasi\n");
                    System.out.print("Registrasi sebagai\n1. Admin\n2. Pembeli\n=> ");
                    int akun = Integer.parseInt(br.readLine());
                    if (akun == 1) {
                        System.out.print("Masukkan nama\t\t: ");
                        String username = br.readLine();
                        System.out.print("Masukkan id\t\t: ");
                        int id = Integer.parseInt(br.readLine());
                        System.out.print("Masukkan Password\t: ");
                        String password = br.readLine();
                        Admin objAdmin = new Admin();
                        objAdmin.registrasi(id, username, password);

                    } else if (akun == 2) {
                        System.out.println("Harap mengisi data registrasi dengan data yang sesuai dengan data di akun E-Money");
                        System.out.println("Karena akun akan disinkronkan dengan akun E-Money !!!");
                        System.out.print("Masukkan nama\t\t: ");
                        String username = br.readLine();
                        System.out.print("Masukkan id\t\t: ");
                        int id = Integer.parseInt(br.readLine());
                        System.out.print("Masukkan Password\t: ");
                        String password = br.readLine();
                        System.out.print("Masukkan kode otp\t: ");
                        String otp = br.readLine();
                        Random saldoRandom = new Random();
                        int saldoReg = 500000 + saldoRandom.nextInt(100000000);
                        Pembeli objPembeli = new Pembeli();
                        objPembeli.registrasi(id, username, password, saldoReg);
                    }
                }
                case 4 -> {
                    System.out.println("Terimakasih . . . ");
                    awal = false;
                }
            }
        }
    }
}
