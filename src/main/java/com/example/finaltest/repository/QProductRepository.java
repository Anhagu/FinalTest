package com.example.finaltest.repository;

import com.example.finaltest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QProductRepository
        extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {
}
