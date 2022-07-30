package eventwithDB;

import com.mysql.jdbc.Connection;

public class Admin extends User implements doManajemen {

    @Override
    public void registrasi(int id, String username, String password) {
        try {
            String sql = "INSERT INTO admin VALUES ('" + id + "','" + username + "','" + password + "')";
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
            String sql = "SELECT * FROM admin WHERE nama='" + username + "' AND password='" + password + "'";
            java.sql.Connection conn = (Connection) config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                if (username.equals(res.getString("nama")) && password.equals(res.getString("password"))) {
                    super.status = 1;
                }
            } else {
                super.status = 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    ;

    @Override
    public void create(int pilih, int id, String nama, int harga, String tanggal, int peserta, String x, String waktu) {
        if (pilih == 1) {
            konser obj = new konser(id, nama, harga, tanggal, peserta, x, waktu);
            obj.tambah();
        } else if (pilih == 2) {
            PentasSeni obj = new PentasSeni(id, nama, harga, tanggal, peserta, x, waktu);
            obj.tambah();
        } else if (pilih == 3) {
            Pameran obj = new Pameran(id, nama, harga, tanggal, peserta, x, waktu);
            obj.tambah();
        }
    }

    @Override
    public void edit(int id, String nama, String x, String tanggal, int harga, int partisipan, String waktu) {
        try {
            String sql = ("update event set nama_event=?, performer=?, tanggal=?, waktu=?, harga=?, jumlah_tiket=? where id=?");
            java.sql.Connection conn = (Connection) config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setString(2, x);
            pst.setString(3, tanggal);
            pst.setString(4, waktu);
            pst.setInt(5, harga);
            pst.setInt(6, partisipan);
            pst.setInt(7, id);
            pst.execute();
            System.out.println("Berhasil Mengedit Data");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(String id) {
        try {
            String sql = ("delete from event where id ='" + id + "'");
            java.sql.Connection conn = (Connection) config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            System.out.println("Berhasil Menghapus");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
