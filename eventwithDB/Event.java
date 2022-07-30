
package eventwithDB;

import java.util.Date;

public abstract class Event {
    private String namaEvent, waktu, tanggal;
//    private Date tanggal;
    int peserta, harga, id; 
    

    public String getNamaEvent() {
        return namaEvent;
    }

    public void setNamaEvent(String namaEvent) {
        this.namaEvent = namaEvent;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    

//    public Date getTanggal() {
//        return tanggal;
//    }
//
//    public void setTanggal(Date tanggal) {
//        this.tanggal = tanggal;
//    }

    
    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
    
    
    abstract void tambah();
}
