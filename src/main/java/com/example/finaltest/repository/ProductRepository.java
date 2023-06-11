package com.example.finaltest.repository;

import com.example.finaltest.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByName(String name);

//    List<Product> findAllByOrderByPriceAsc();

    List<Product> findAllByOrderByPriceDesc();

    @Query("SELECT p FROM Product AS p WHERE p.stock = ?1")
    List<Product> listByStock(@Param("stock") int stock);

    Long countByPrice(int price);

    boolean existsByNumber(Long number);

    Product findByNameAndPrice(String name, int price);






}
