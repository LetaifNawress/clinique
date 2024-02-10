package com.example.clinique.Repositories;


import com.example.clinique.Entity.Equipement.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}