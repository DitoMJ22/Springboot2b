package astratech.prg7_m5_p2_019.service;

import astratech.prg7_m5_p2_019.model.Detail;
import astratech.prg7_m5_p2_019.model.Pembelian;
import astratech.prg7_m5_p2_019.repository.DetailRepository;
import astratech.prg7_m5_p2_019.repository.PembelianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {
    @Autowired
    DetailRepository detailRepository;

    public boolean save(Detail detail){
        Detail result = detailRepository.save(detail);

        boolean isSuccess = true;
        if (result == null){
            isSuccess = false;
        }
        return isSuccess;
    }

    public Detail getDetail(Integer idDetail){
        Detail detail = detailRepository.getDetailByIdDetail(idDetail);
        return detail;
    }

    //latihan no 5, akan menampilkan seluruh data helm berbentuk list
    public List<Detail> detailPembelian(Pembelian idPembelian){
        List<Detail> details = detailRepository.getDetailByIdPembelian(idPembelian);
        return details;
    }
}
