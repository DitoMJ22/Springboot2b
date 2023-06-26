package astratech.prg7_m5_p2_019.service;

import astratech.prg7_m5_p2_019.model.Helm;
import astratech.prg7_m5_p2_019.repository.HelmRepository;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import net.sf.jasperreports.engine.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HelmService {
    @Autowired
    HelmRepository helmRepository;

    public boolean save(Helm helm){
        Helm result = helmRepository.save(helm);
        boolean isSuccess = true;
        if (result == null){
            isSuccess = false;
        }
        return isSuccess;
    }

    public Helm getHelm(Integer idHelm){
        Helm helm = helmRepository.getHelmByIdHelm(idHelm);
        return helm;
    }

    public List<Helm> getHelms(){
        return helmRepository.getHelms();
    }

    //latihan no 2, akan mengupdate helm berdasarkan id
    public boolean updateHelm(Integer idHelm, Helm updatedHelm){
        Helm helm = helmRepository.getHelmByIdHelm(idHelm);
        if(helm == null){
            return false;
        }

        helm.setMerk(updatedHelm.getMerk());
        helm.setWarna(updatedHelm.getWarna());
        helm.setUkuran(updatedHelm.getUkuran());
        helm.setStok(updatedHelm.getStok());
        helm.setJenis(updatedHelm.getJenis());
        helm.setHarga(updatedHelm.getHarga());

        Helm result = helmRepository.save(helm);
        return result != null;
    }

    //latihan no 3, akan menghapus helm berdasarkan id
    public boolean deleteHelm(Integer idHelm){
        try {
            helmRepository.deleteById(idHelm);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "D:";
        List<Helm> helms = (List<Helm>) helmRepository.findAll();
        File file = ResourceUtils.getFile("classpath:templates/Detail/helmreport.jrxml");

        JasperReport jaspertReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(helms);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Dito Modovan Junian");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jaspertReport, parameters, dataSource);
        if(reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\helm.html");
        } else if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\helm.pdf");
        }
        return "Export Report to " + reportFormat;
    }
}

