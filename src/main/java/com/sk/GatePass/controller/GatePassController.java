package com.sk.GatePass.controller;

import com.sk.GatePass.model.GatePass;
import com.sk.GatePass.service.GatePassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GatePassController {

    private final GatePassService gatePassService;
    public GatePassController(GatePassService gatePassService) {
        this.gatePassService = gatePassService;
    }

    @GetMapping("/gatepasses")
    public ResponseEntity<List<GatePass>> getAllGatePass(){
        List<GatePass> list = gatePassService.getGatePasses();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/gatepasses/{id}")
    public ResponseEntity<GatePass> getGatePass(@PathVariable("id") Long id){
        GatePass gatePass = gatePassService.getGatePass(id);
        return new ResponseEntity<>(gatePass, HttpStatus.OK);
    }

    @GetMapping("/gatepasses/companies/{id}")
    public ResponseEntity<List<GatePass>> getGatePassByCompaniesId(@PathVariable("id") Long id){
       List<GatePass> gatePass= gatePassService.getGatePassByCompanyId(id);
       return new ResponseEntity<>(gatePass, HttpStatus.OK);
    }
    @PostMapping("/gatepasses")
    public ResponseEntity<GatePass> addGatePass(@RequestBody GatePass gatePass){
        GatePass newGatePass = gatePassService.addGatePass(gatePass);
        return new ResponseEntity<>(newGatePass, HttpStatus.CREATED);
    }
//    @PutMapping("/gatepasses/{id}")
//    public ResponseEntity<GatePass> updateGatePass(@PathVariable("id") Long id, @RequestBody GatePass newGatePass){
//        GatePass gatePass = gatePassService.updateGatePass(id, newGatePass);
//        return new ResponseEntity<>(gatePass, HttpStatus.OK);
//    }
    @DeleteMapping("/gatepasses/{id}")
    public ResponseEntity<?> deleteGatePass(@PathVariable("id")Long id){
        gatePassService.deleteGatePass(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
