package com.sk.GatePass.service;

import com.sk.GatePass.model.GatePass;
import com.sk.GatePass.repository.GatePassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public GatePass updateGatePass(Long id, GatePass gatePass){
        return gatePassRepository.save(gatePass);
    }
}
