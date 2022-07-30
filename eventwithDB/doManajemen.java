
package eventwithDB;
public interface doManajemen {
    
    public void create(int pilih, int id, String nama, int harga, String tanggal, int peserta, String x, String waktu);
    public void edit(int id, String nama, String x, String tanggal, int harga, int partisipan, String waktu);
    public void delete(String id);
}
