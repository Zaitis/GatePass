package com.sk.GatePass.repository;

import com.sk.GatePass.model.GatePass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GatePassRepository extends JpaRepository<GatePass, Long> {


}
