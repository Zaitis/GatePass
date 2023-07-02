//package com.sk.GatePass.controller;
//
//
//import com.sk.GatePass.model.GatePass;
//import com.sk.GatePass.service.GatePassService;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class GatePassControllerTest {
//    @Mock
//    private GatePassService gatePassService;
//
//    @InjectMocks
//    private GatePassController gatePassController;
//
//    private GatePass gatePass1, gatePass2;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    void shouldGetAllGatePass() {
//        List<GatePass> gatePassList = new ArrayList<>();
//        gatePassList.add(gatePass1);
//        gatePassList.add(gatePass2);
//
//        when(gatePassService.getGatePasses()).thenReturn(gatePassList);
//
//        ResponseEntity<List<GatePass>> responseEntity = gatePassController.getAllGatePass();
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals(gatePassList, responseEntity.getBody());
//    }
//
//    @Test
//    void shouldGetGatePass() {
//        when(gatePassService.getGatePass(gatePass1.getId())).thenReturn(gatePass1);
//
//        ResponseEntity<GatePass> responseEntity = gatePassController.getGatePass(gatePass1.getId());
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals(gatePass1, responseEntity.getBody());
//    }
//
//    @Test
//    void shouldGetGatePassByCompaniesId() {
//        List<GatePass> gatePassList = new ArrayList<>();
//        gatePassList.add(gatePass1);
//        gatePassList.add(gatePass2);
//
//        when(gatePassService.getGatePassByCompanyId(1L)).thenReturn(gatePassList);
//
//        ResponseEntity<List<GatePass>> responseEntity = gatePassController.getGatePassByCompaniesId(1L);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals(gatePassList, responseEntity.getBody());
//    }
//
//    @Test
//    void shouldAddGatePass() {
//        when(gatePassService.addGatePass(gatePass1)).thenReturn(gatePass1);
//
//        ResponseEntity<GatePass> responseEntity = gatePassController.addGatePass(gatePass1);
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals(gatePass1, responseEntity.getBody());
//    }
//
//    @Test
//    void shouldUpdateGatePass() {
//        when(gatePassService.updateGatePass(gatePass1.getId(), gatePass1)).thenReturn(gatePass1);
//
//        ResponseEntity<GatePass> responseEntity = gatePassController.updateGatePass(gatePass1.getId(), gatePass1);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals(gatePass1, responseEntity.getBody());
//    }
//}
