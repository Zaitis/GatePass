package com.sk.GatePass.service;

import com.sk.GatePass.model.Company;
import com.sk.GatePass.model.GatePass;
import com.sk.GatePass.repository.GatePassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GatePassService {


    private final GatePassRepository gatePassRepository;


    public List<GatePass> getGatePasses(){
        return gatePassRepository.findAll();
    }

    public GatePass getGatePass(Long id){
        return gatePassRepository.findById(id).orElse(null);
    }

    public GatePass addGatePass(GatePass gatePass){
        return gatePassRepository.save(gatePass);
    }
    public void deleteGatePass(Long id){
        gatePassRepository.deleteById(id);
    }

    public List<GatePass> filterGatePass(String filterText){
        if(filterText == null || filterText.isEmpty()) {
            return gatePassRepository.findAll();
        } else {
            return gatePassRepository.findAll();
        }
    }

//    public GatePass updateGatePass(Long id, GatePass gatePass){
//        GatePass oldGatePass= gatePassRepository.findById(id).orElse(null);
//        oldGatePass.setCars(gatePass.getCars());
//        oldGatePass.setIdCard(gatePass.getIdCard());
//        oldGatePass.setCompany(gatePass.getCompany());
//        oldGatePass.setName(gatePass.getName());
//        oldGatePass.setSurname(gatePass.getSurname());
//        oldGatePass.setPhone(gatePass.getPhone());
//        oldGatePass.setAcceptedDate(gatePass.getAcceptedDate());
//        oldGatePass.setUser(gatePass.getUser());
//        return gatePassRepository.save(oldGatePass);
//    }
}
