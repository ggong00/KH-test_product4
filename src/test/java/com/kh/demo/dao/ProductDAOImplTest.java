package com.kh.demo.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
class ProductDAOImplTest {

    @Autowired
    private ProductDAOImpl productDAO;
    private Product testProduct;

    @Test
    @DisplayName("상품저장")
    void save() {
        testProduct = new Product();
        testProduct.setPname("테스트1");
        testProduct.setQuantity(1L);
        testProduct.setPrice(1000L);
        Long saveId = productDAO.save(testProduct);
        testProduct.setProductId(saveId);

        Product findedProduct = productDAO.findByProductId(testProduct.getProductId()).get();

        Assertions.assertThat(findedProduct.getPname()).isEqualTo(testProduct.getPname());
        Assertions.assertThat(findedProduct.getPrice()).isEqualTo(testProduct.getPrice());
        Assertions.assertThat(findedProduct.getQuantity()).isEqualTo(testProduct.getQuantity());
    }

    @Test
    @DisplayName("상품 전체조회")
    void findAll() {
        testProduct = new Product();
        testProduct.setPname("테스트1");
        testProduct.setQuantity(1L);
        testProduct.setPrice(1000L);
        Long saveId = productDAO.save(testProduct);
        testProduct.setProductId(saveId);

        List<Product> productList = productDAO.findAll();

        Assertions.assertThat(productList).contains(testProduct);
    }

    @Test
    @DisplayName("상품 조회")
    void findByProductId() {
        testProduct = new Product();
        testProduct.setPname("테스트1");
        testProduct.setQuantity(1L);
        testProduct.setPrice(1000L);
        Long saveId = productDAO.save(testProduct);
        testProduct.setProductId(saveId);
        Product findedProduct = productDAO.findByProductId(testProduct.getProductId()).get();

        Assertions.assertThat(findedProduct).isNotNull();
        Assertions.assertThat(findedProduct.getPname()).isEqualTo(testProduct.getPname());
        Assertions.assertThat(findedProduct.getPrice()).isEqualTo(testProduct.getPrice());
        Assertions.assertThat(findedProduct.getQuantity()).isEqualTo(testProduct.getQuantity());

    }

    @Test
    @DisplayName("상품 수정")
    void update() {
        testProduct = new Product();
        testProduct.setPname("테스트1");
        testProduct.setQuantity(1L);
        testProduct.setPrice(1000L);
        Long saveId = productDAO.save(testProduct);
        testProduct.setProductId(saveId);

        Product updateProduct = new Product();
        updateProduct.setPname("변경 테스트");
        updateProduct.setPrice(2000L);
        updateProduct.setQuantity(5L);
        int cnt = productDAO.update(testProduct.getProductId(), updateProduct);
        Product findedProduct = productDAO.findByProductId(testProduct.getProductId()).get();

        Assertions.assertThat(cnt).isEqualTo(1);
        Assertions.assertThat(findedProduct.getPname()).isEqualTo(updateProduct.getPname());
        Assertions.assertThat(findedProduct.getPrice()).isEqualTo(updateProduct.getPrice());
        Assertions.assertThat(findedProduct.getQuantity()).isEqualTo(updateProduct.getQuantity());
    }

    @Test
    @DisplayName("상품 삭제")
    void deleteByProductId() {
        testProduct = new Product();
        testProduct.setPname("테스트1");
        testProduct.setQuantity(1L);
        testProduct.setPrice(1000L);
        Long saveId = productDAO.save(testProduct);
        testProduct.setProductId(saveId);

        int cnt = productDAO.deleteByProductId(testProduct.getProductId());
        Optional<Product> findedProduct = productDAO.findByProductId(testProduct.getProductId());

        Assertions.assertThat(cnt).isEqualTo(1);
        Assertions.assertThat(findedProduct.isEmpty()).isTrue();
    }
}