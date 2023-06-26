package astratech.prg7_m5_p2_019.model;

import javax.persistence.*;


@Entity
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_detail")
    private Integer idDetail;

    @ManyToOne
    @JoinColumn(name = "id_pembelian")
    private Pembelian idPembelian;

    @ManyToOne
    @JoinColumn(name = "id_Helm")
    private Helm idHelm;

    @Column(name = "jumlah")
    private Integer jumlah;

    @Column(name = "subtotal")
    private Integer subtotal;

    public Detail(Integer idDetail, Pembelian idPembelian, Helm idHelm, Integer jumlah, Integer subtotal) {
        this.idDetail = idDetail;
        this.idPembelian = idPembelian;
        this.idHelm = idHelm;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }

    public Detail() {

    }

    public Integer getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Integer idDetail) {
        this.idDetail = idDetail;
    }

    public Pembelian getIdPembelian() {
        return idPembelian;
    }

    public void setIdPembelian(Pembelian idPembelian) {
        this.idPembelian = idPembelian;
    }

    public Helm getIdHelm() {
        return idHelm;
    }

    public void setIdHelm(Helm idHelm) {
        this.idHelm = idHelm;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
}
