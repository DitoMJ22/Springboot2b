package astratech.prg7_m5_p2_019.repository;

import astratech.prg7_m5_p2_019.model.Helm;
import astratech.prg7_m5_p2_019.model.Pembelian;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PembelianRepository extends CrudRepository<Pembelian, Integer> {
        @Query("SELECT h FROM Pembelian h WHERE h.idPembelian =:idPembelian")
        public Pembelian getPembelianByIdPembelian(@Param("idPembelian")Integer idpembelian);


        @Query("SELECT h FROM Pembelian h")
        public List<Pembelian> getPembelians();
}
