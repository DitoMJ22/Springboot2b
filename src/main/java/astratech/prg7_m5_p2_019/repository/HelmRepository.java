package astratech.prg7_m5_p2_019.repository;


import astratech.prg7_m5_p2_019.model.Helm;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HelmRepository extends CrudRepository<Helm, Integer> {
    @Query("SELECT h FROM Helm h WHERE h.idHelm =:idhelm")
    public Helm getHelmByIdHelm(@Param("idhelm")Integer idhelm);


    //List Helm
    @Query("SELECT h FROM Helm h")
    public List<Helm> getHelms();

    //update Helm
    @Modifying
    @Query("UPDATE Helm h SET h.merk = :merk, h.warna = :warna, h.ukuran = :ukuran, h.stok = :stok, h.jenis = :jenis, h.harga = :harga WHERE h.idHelm = :idhelm")
    public void updateHelmByIdHelm(@Param("idhelm") Integer idhelm, @Param("merk") String merk, @Param("warna") String warna, @Param("ukuran") String ukuran, @Param("stok") Integer stok, @Param("jenis") String jenis, @Param("harga") Integer harga);


    //untuk menghapus helm berdasarkan id
    @Modifying
    @Query("DELETE FROM Helm h WHERE h.idHelm = :idHelm")
    public void deleteHelmByIdHelm(@Param("idHelm") Integer idHelm);

}
