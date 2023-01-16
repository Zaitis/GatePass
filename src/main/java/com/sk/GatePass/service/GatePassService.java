package com.sk.GatePass.service;

import com.sk.GatePass.model.GatePass;
import com.sk.GatePass.repository.CarRepository;
import com.sk.GatePass.repository.GatePassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GatePassService {

    @Autowired
    private final GatePassRepository gatePassRepository;


    public List<GatePass> getGatePasses(){
        return gatePassRepository.findAll();
    }

    public List<GatePass> getGatePassByCompanyId(Long id){
        return gatePassRepository.findByCompanyId(id);
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
    public GatePass updateGatePass(Long id, GatePass gatePass){
        GatePass oldGatePass= gatePassRepository.findById(id).orElse(null);
        oldGatePass.setCars(gatePass.getCars());
        oldGatePass.setIdCard(gatePass.getIdCard());
        oldGatePass.setCompany(gatePass.getCompany());
        oldGatePass.setName(gatePass.getName());
        oldGatePass.setSurname(gatePass.getSurname());
        oldGatePass.setPhone(gatePass.getPhone());
        oldGatePass.setAcceptedDate(gatePass.getAcceptedDate());
        oldGatePass.setPerson(gatePass.getPerson());
        return gatePassRepository.save(oldGatePass);
    }
}
