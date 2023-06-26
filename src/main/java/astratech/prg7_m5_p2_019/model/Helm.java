package astratech.prg7_m5_p2_019.model;

import javax.persistence.*;

@Entity
@Table(name = "Helm")
public class Helm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name ="id_helm")
    private Integer idHelm;

    @Column(name ="merk")
    private String merk;

    @Column(name ="warna")
    private String warna;

    @Column(name ="ukuran")
    private String ukuran;

    @Column(name ="stok")
    private Integer stok;

    @Column(name ="jenis")
    private String jenis;

    @Column(name ="harga")
    private Integer harga;

    public Helm(Integer idHelm, String merk, String warna, String ukuran, Integer stok, String jenis, Integer harga) {
        this.idHelm = idHelm;
        this.merk = merk;
        this.warna = warna;
        this.ukuran = ukuran;
        this.stok = stok;
        this.jenis = jenis;
        this.harga = harga;
    }

    public Helm() {

    }

    public Integer getIdHelm() {
        return idHelm;
    }

    public void setIdHelm(Integer idHelm) {
        this.idHelm = idHelm;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
}