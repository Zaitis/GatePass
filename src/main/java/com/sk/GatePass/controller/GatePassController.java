package com.sk.GatePass.controller;


import com.sk.GatePass.dto.GatePassDto;
import com.sk.GatePass.model.GatePass;
import com.sk.GatePass.service.CarService;

import com.sk.GatePass.service.GatePassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GatePassController {

    private final GatePassService gatePassService;
    private final CarService carService;




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

    @PostMapping("/gatepasses")
    public ResponseEntity<GatePass> addGatePass(@RequestBody GatePassDto dto){
        GatePass gatePass = gatePassService.addGatePass(createGatePass(dto));
        return new ResponseEntity<>(gatePass, HttpStatus.CREATED);
    }

    private GatePass createGatePass(GatePassDto dto) {
        GatePass gatePass= GatePass.builder()
                .cars(carService.getCarById(dto.car()))
                .createdDate(LocalDateTime.now())
                .isAccepted(false)
                .build();
        return gatePass;
    }

//    @PutMapping("/gatepasses/{id}")
//    public ResponseEntity<GatePass> updateGatePass(@PathVariable("id") Long id, @RequestBody GatePassAdminDto adminDto){
//        GatePass gatePass = gatePassService.updateGatePass(id, updateGatePass1(id, adminDto));
//        return new ResponseEntity<>(gatePass, HttpStatus.OK);
//    }
//
//    private GatePass updateGatePass1(Long id, GatePassAdminDto adminDto) {
//        GatePass gatePass = GatePass.builder()
//                .acceptedDate(LocalDateTime.now())
//                .id(id)
//                .isAccepted(adminDto.isAccepted()).
//                build();
//        return gatePass;
//    }

    @DeleteMapping("/gatepasses/{id}")
    public ResponseEntity<?> deleteGatePass(@PathVariable("id")Long id){
        gatePassService.deleteGatePass(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
