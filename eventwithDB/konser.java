package eventwithDB;

import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
//import java.util.Date;

public class konser extends Event{

    final String jenis = "Konser";
    private String penyanyi;

    konser(int id, String nama, int harga, String tanggal, int peserta, String x, String waktu) {
        super.id = id;
        super.harga = harga;
        super.peserta = peserta;
        this.penyanyi = x;
        setNamaEvent(nama);
        setTanggal(tanggal);
        setWaktu(waktu);
        
    }

    @Override
    void tambah() {

        try {
//            java.sql.Date sqlDate = new java.sql.Date(getTanggal().getTime());
            String sql = "INSERT INTO event VALUES ('" + super.id + "','" + this.jenis + "','" + getNamaEvent() + "','" + this.penyanyi + "','" + getTanggal() + "','" + getWaktu() + "','" + super.harga + "','" + super.peserta + "')";
            java.sql.Connection conn = (Connection) config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            System.out.println("Penyimpanan data berhasil");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
