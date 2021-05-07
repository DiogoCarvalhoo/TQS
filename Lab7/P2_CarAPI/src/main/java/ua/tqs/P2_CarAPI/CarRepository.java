package ua.tqs.P2_CarAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import javax.transaction.Transactional;

@Repository
@Transactional

public interface CarRepository  extends JpaRepository<Car, Long> {
    
    public Car findByCarid(Long id);
    public Car findByMaker(String maker);
    public List<Car> findAll();
    
}