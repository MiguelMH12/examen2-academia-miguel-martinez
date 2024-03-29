package com.everis.examen2academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.examen2academia.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
