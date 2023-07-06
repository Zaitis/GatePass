package com.sk.GatePass.service;

import com.sk.GatePass.model.Car;
import com.sk.GatePass.model.Company;
import com.sk.GatePass.model.GatePass;
import com.sk.GatePass.repository.GatePassRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    public GatePass updateGatePass(Long id, GatePass gatePass){

        return gatePassRepository.save(gatePass);
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "car_id", unique = true, referencedColumnName = "id")
    private Car cars;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "is_accepted")
    private boolean isAccepted;

    @Column(name = "accepted_date")
    private LocalDateTime acceptedDate;
}
