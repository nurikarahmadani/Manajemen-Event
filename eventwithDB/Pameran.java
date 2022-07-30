
package eventwithDB;

import com.mysql.jdbc.Connection;
import java.util.Date;

public class Pameran extends Event{
    final String jenis = "Pameran";
    String seniman;
    
    Pameran(int id, String nama, int harga, String tanggal, int peserta, String x, String waktu){
        super.id = id;
        super.harga = harga;
        super.peserta = peserta;
        this.seniman = x;
        setNamaEvent(nama);
        setTanggal(tanggal);
        setWaktu(waktu);
    }

    @Override
    void tambah() {
        
        try {
            String sql = "INSERT INTO event VALUES ('" + super.id + "','" + this.jenis + "','" + getNamaEvent() + "','" + this.seniman + "','" + getTanggal() + "','" + getWaktu() + "','" + super.harga + "','" + super.peserta + "')";
            java.sql.Connection conn = (Connection) config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            System.out.println("Penyimpanan data berhasil");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
