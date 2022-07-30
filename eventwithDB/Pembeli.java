package eventwithDB;

import com.mysql.jdbc.Connection;

public class Pembeli extends User implements doTranksaksi {

    @Override
    public void registrasi(int id, String username, String password, int saldo) {
        try {
            String sql = "INSERT INTO pembeli VALUES ('" + id + "','" + username + "','" + password + "','" + saldo + "')";
            java.sql.Connection conn = (Connection) config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            System.out.println("Registrasi Berhasil");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void login(String username, String password) {
        try {
            int no = 1;
            String sql = "SELECT * FROM pembeli WHERE Nama='" + username + "' AND password='" + password + "'";
            java.sql.Connection conn = (Connection) config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                if (username.equals(res.getString("Nama")) && password.equals(res.getString("password"))) {
                    super.status = 1;
                }
            } else {
                super.status = 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void beli(int pilihId, int jumlahTiket, String username) {
        int sisaTiketdb = 0;
        int hargadb = 0;
        int sisaSaldo = 0;
        int saldo = 0;
        int tiket = 0;
        try {

            String sqlsetTiket = "select jumlah_tiket, harga from event where id = '" + pilihId + "'";
            java.sql.Connection connSetTiket = (Connection) config.configDB();
            java.sql.Statement stmSetTiket = connSetTiket.createStatement();
            java.sql.ResultSet resSetTiket = stmSetTiket.executeQuery(sqlsetTiket);
            while (resSetTiket.next()) {
                tiket = resSetTiket.getInt("jumlah_tiket");
                sisaTiketdb = tiket - jumlahTiket;
                hargadb = (resSetTiket.getInt("harga")) * jumlahTiket;
            }
            String sqlSetSaldo = "select Saldo from pembeli where Nama = '" + username + "'";
            java.sql.Connection connSetSaldo = (Connection) config.configDB();
            java.sql.Statement stmSetSaldo = connSetSaldo.createStatement();
            java.sql.ResultSet resSetSaldo = stmSetSaldo.executeQuery(sqlSetSaldo);
            while (resSetSaldo.next()) {
                saldo = resSetSaldo.getInt("Saldo");
                if (saldo >= hargadb && tiket >= jumlahTiket) {
                    sisaSaldo = saldo - hargadb;
                    System.out.println("Total\t\t\t: Rp. " + hargadb);
                    System.out.println("Sisa Saldo\t\t: Rp. " + sisaSaldo);
                    String sqlUpdateTiket = ("update event set jumlah_tiket ='" + sisaTiketdb + "' where id='" + pilihId + "'");
                    java.sql.Connection connSet = (Connection) config.configDB();
                    java.sql.PreparedStatement pstUpdateTiket = connSet.prepareStatement(sqlUpdateTiket);
                    pstUpdateTiket.execute();

                    String sqlUpdateSaldo = ("update pembeli set Saldo ='" + sisaSaldo + "' where Nama ='" + username + "'");
                    java.sql.Connection connUpdateSaldo = (Connection) config.configDB();
                    java.sql.PreparedStatement pstUpdateSaldo = connUpdateSaldo.prepareStatement(sqlUpdateSaldo);
                    pstUpdateSaldo.execute();

                } else if (jumlahTiket > tiket) {
                    System.out.println("Tiket tidak mencukupi !!!");
                } else if (saldo < hargadb) {
                    System.out.println("Saldo tidak mencukupi !!!");
                } else {
                    System.out.println("Id yang diinputkan salah !!!");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void cekSaldo(String username) {
        try {
            String sqlSetSaldo = "select Saldo from pembeli where Nama = '" + username + "'";
            java.sql.Connection connSetSaldo = (Connection) config.configDB();
            java.sql.Statement stmSetSaldo = connSetSaldo.createStatement();
            java.sql.ResultSet resSetSaldo = stmSetSaldo.executeQuery(sqlSetSaldo);
            while (resSetSaldo.next()) {
                System.out.println("Saldo E-Money di Akun Anda : Rp. " + resSetSaldo.getInt("Saldo"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
