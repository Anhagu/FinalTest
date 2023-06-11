package com.example.finaltest.service;

import com.example.finaltest.dto.ProductDto;
import com.example.finaltest.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    ProductResponseDto saveProduct(ProductDto productDto);

    void deleteProduct(Long number) throws Exception;

    List<ProductResponseDto> allProduct();

    List<ProductResponseDto> listProductByPriceDesc();

    List<ProductResponseDto> getProductByName(String name);

    ProductResponseDto getProduct(Long number);

    ProductResponseDto getOneProduct(Long id);

    ProductResponseDto changeProductNamePriceStock(Long number, String name, int price, int stock) throws Exception;

    ProductResponseDto changeProductStock(Long number, int stock) throws Exception;

}
