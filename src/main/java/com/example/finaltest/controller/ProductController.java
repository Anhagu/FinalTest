package com.example.finaltest.controller;

import com.example.finaltest.dto.ChangeProductDto;
import com.example.finaltest.dto.ProductDto;
import com.example.finaltest.dto.ProductResponseDto;
import com.example.finaltest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDto> changeProductName(@RequestBody ChangeProductDto changeProductDto) throws Exception{
        ProductResponseDto productResponseDto = productService.changeProductName(changeProductDto.getNumber(), changeProductDto.getName());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProduct(Long number) throws Exception{
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> allProduct() {
        List<ProductResponseDto> productResponseDto = productService.allProduct();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @GetMapping("/listOrderByPriceDesc")
    public ResponseEntity<List<ProductResponseDto>> listProductByPriceDesc() {
        List<ProductResponseDto> productResponseDto = productService.listProductByPriceDesc();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @GetMapping("/byName")
    public ResponseEntity<List<ProductResponseDto>> getProductByName(String name) {
        List<ProductResponseDto> productResponseDto = productService.getProductByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<ProductResponseDto> getProduct(Long number) {
        ProductResponseDto productResponseDto = productService.getProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }


//    @GetMapping("/countByPrice")
//    public ResponseEntity<Long> getCountByPrice(int price) {
//        Long count = productService.getCountByPrice(price);
//        return ResponseEntity.status(HttpStatus.OK).body(count);
//    }
//
//    @GetMapping("/existsByNumber")
//    public ResponseEntity<Boolean> existsByNumber(Long number) {
//        Boolean exists = productService.existsByNumber(number);
//        return ResponseEntity.status(HttpStatus.OK).body(exists);
//    }
//
//    @GetMapping("/byNameAndPrice")
//    public ResponseEntity<ProductResponseDto> getProductByNameAndPrice(String name, int price) {
//        ProductResponseDto productResponseDto = productService.getProductByNameAndPrice(name,price);
//        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
//    }
//
//    @GetMapping("/listByName")
//    public ResponseEntity<List<ProductResponseDto>> listProductByName(String name) {
//        List<ProductResponseDto> productResponseDto = productService.listProductByName(name);
//        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
//    }
//
//    @GetMapping("/listByStock")
//    public ResponseEntity<List<ProductResponseDto>> listByStock(int stock) {
//        List<ProductResponseDto> productResponseDto = productService.listByStock(stock);
//        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
//    }
}
