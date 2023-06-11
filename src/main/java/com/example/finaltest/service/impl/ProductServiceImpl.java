package com.example.finaltest.service.impl;

import com.example.finaltest.dao.ProductDao;
import com.example.finaltest.dto.ProductDto;
import com.example.finaltest.dto.ProductResponseDto;
import com.example.finaltest.entity.Product;
import com.example.finaltest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product changeProduct = productDao.updateProductName(number, name);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setName(changeProduct.getName());
        productResponseDto.setNumber(changeProduct.getNumber());
        productResponseDto.setPrice(changeProduct.getPrice());
        productResponseDto.setStock(changeProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saveProduct = productDao.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setNumber(saveProduct.getNumber());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setStock(saveProduct.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDao.deleteProduct(number);
    }

    @Override
    public List<ProductResponseDto> allProduct() {
        List<Product> products = productDao.allProduct();
        List<ProductResponseDto> productResponseDtoList =
                products.stream().map(ProductResponseDto::new).collect(Collectors.toList());
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> listProductByPriceDesc() {
        return productDao.listProductByPriceDesc().stream().map(ProductResponseDto::new).collect(Collectors
                .toList());
    }

    @Override
    public List<ProductResponseDto> getProductByName(String name) {
        List<Product> product = productDao.selectProductByName(name);
        List<ProductResponseDto> productResponseDtoList =
                product.stream().map(ProductResponseDto::new).collect(Collectors.toList());
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDao.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(product.getName());
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto getOneProduct(Long id) {
        Product product = productDao.selectProduct(id);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductNamePriceStock(Long number, String name, int price, int stock) throws Exception {
        Product changeproduct = productDao.updateUserNamePRiceStock(number, name, price, stock);
        return new ProductResponseDto(changeproduct);
    }

    @Override
    public ProductResponseDto changeProductStock(Long number, int stock) throws Exception {
        Product changeproduct = productDao.updateProductStock(number, stock);
        return new ProductResponseDto(changeproduct);
    }

}
