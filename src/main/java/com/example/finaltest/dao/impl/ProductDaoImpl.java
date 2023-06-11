package com.example.finaltest.dao.impl;

import com.example.finaltest.dao.ProductDao;
import com.example.finaltest.entity.Product;
import com.example.finaltest.repository.ProductRepository;
import com.example.finaltest.repository.QProductRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.finaltest.entity.QProduct.product;

@Component
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final QProductRepository qProductRepository;

    @Autowired
    public ProductDaoImpl(ProductRepository productRepository, JPAQueryFactory jpaQueryFactory, QProductRepository qProductRepository) {
        this.productRepository = productRepository;
        this.jpaQueryFactory = jpaQueryFactory;
        this.qProductRepository = qProductRepository;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);
        Product updateProduct;
        if(selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());
            updateProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }

        return updateProduct;
    }

    @Override
    public Product insertProduct(Product product) {
        Product saveProduct = productRepository.save(product);
        return saveProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        // delete
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            productRepository.delete(product);
        } else throw new Exception();
    }

    @Override
    public List<Product> allProduct() {
        return jpaQueryFactory.selectFrom(product).where(product.name.isNotNull()).fetch();
    }

    @Override
    public List<Product> listProductByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public List<Product> selectProductByName(String name) {
        List<Product> selectProduct =
                productRepository.findAllByName(name);
        return selectProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        /*Product selectProduct = productRepository.getReferenceById(number);
        return selectProduct;*/
        Predicate predicate = product.number.eq(number);
        Optional<Product> selectProduct = qProductRepository.findOne(predicate);
        return selectProduct.isPresent() ? selectProduct.get() : null;
    }

    @Override
    public Product updateUserNamePRiceStock(Long number, String name, int price, int stock) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);
        Product updateProduct;
        if(selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setUpdatedAt(LocalDateTime.now());
            updateProduct = productRepository.save(product);
        } else throw new Exception();
        return updateProduct;
    }

    @Override
    public Product updateProductStock(Long number, int stock) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);
        Product updateProduct;
        if(selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            product.setStock(stock-1);
            product.setUpdatedAt(LocalDateTime.now());
            updateProduct = productRepository.save(product);
        } else throw new Exception();
        return updateProduct;
    }


}
