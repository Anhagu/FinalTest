package com.example.finaltest.dao;

import com.example.finaltest.entity.Product;

import java.util.List;

public interface ProductDao {

    Product updateProductName(Long number, String name) throws Exception;

    Product insertProduct(Product product);

    void deleteProduct(Long number) throws Exception;

    List<Product> allProduct();

    List<Product> listProductByPriceDesc();

    List<Product> selectProductByName(String name);

    Product selectProduct(Long number);

    Product updateUserNamePRiceStock(Long number, String name, int price, int stock) throws Exception;
    Product updateProductStock(Long number, int stock) throws Exception;
}
