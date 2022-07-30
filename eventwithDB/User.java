
package eventwithDB;

import com.mysql.jdbc.Connection;

public class User{
    public int status;
//    private String nama, password;
//    private int id;
    
    public void registrasi(int id, String username, String password){};
    public void registrasi(int id, String username, String password, int saldo){};
    public void login(String username, String password){};
    public void read(){
    try {
            int no = 1;
            String sql = "select * from event";
            java.sql.Connection conn = (Connection) config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            System.out.println("id\tjenis event\tnama event\t\tperformer\ttanggal\t\twaktu\tharga\tjumlah tiket");
            while (res.next()) {
//                System.out.println(res.getString("nama_event"));
                System.out.println(res.getInt("id") + "\t" + res.getString("jenis_event") + "\t\t" + res.getString("nama_event") + "\t\t" + res.getString("performer") + "\t\t" + res.getString("tanggal") + "\t" + res.getString("waktu") + "\t" + res.getInt("harga") + "\t" + res.getInt("jumlah_tiket") + "\t");
            }
        } catch (Exception e) {
        }
    };
    
    
    
}
