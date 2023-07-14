package com.sk.GatePass.repository;

import com.sk.GatePass.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarsByEmployeeId(Long id);

    @Query("select c from Car c " +
            "where lower(c.model) like lower(concat('%', :filterText, '%')) " +
            "or lower(c.plate) like lower(concat('%', :filterText, '%'))")
    List<Car> search(String filterText);
}
