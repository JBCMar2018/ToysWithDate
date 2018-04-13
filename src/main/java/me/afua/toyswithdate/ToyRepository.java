package me.afua.toyswithdate;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface ToyRepository extends CrudRepository<Toy,Long>{
    Iterable<Toy> findAllByManufactureDateAfter(LocalDate date);
}
