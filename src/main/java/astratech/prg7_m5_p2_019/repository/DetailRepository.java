package astratech.prg7_m5_p2_019.repository;

import astratech.prg7_m5_p2_019.model.Detail;
import astratech.prg7_m5_p2_019.model.Pembelian;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailRepository extends CrudRepository<Detail, Integer> {
    @Query("SELECT h FROM Detail h WHERE h.idDetail =:idDetail")
    public Detail getDetailByIdDetail(@Param("idDetail")Integer idDetail);

    //menampilkan seluruh helm dalam bentuk list
    @Query("SELECT h FROM Detail h WHERE h.idPembelian =:idPembelian")
    public List<Detail> getDetailByIdPembelian(@Param("idPembelian") Pembelian idPembelian);

}
