package com.example.finaltest.controller;

import com.example.finaltest.config.security.JwtTokenProvider;
import com.example.finaltest.dto.OrderDto;
import com.example.finaltest.dto.OrderResponseDto;
import com.example.finaltest.dto.ProductResponseDto;
import com.example.finaltest.dto.UserDto;
import com.example.finaltest.service.OrderService;
import com.example.finaltest.service.ProductService;
import com.example.finaltest.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserDetailService userDetailService;
    private final ProductService productService;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    public OrderController(OrderService orderService, JwtTokenProvider jwtTokenProvider, UserDetailService userDetailService, ProductService productService) {
        this.orderService = orderService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailService = userDetailService;
        this.productService = productService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<OrderResponseDto> createOrder(HttpServletRequest request, @RequestParam Long productId, @RequestParam String productName) throws Exception {
        String token = request.getHeader("X-AUTH-TOKEN");
        String tokenUsername = jwtTokenProvider.getUsername(token);

        UserDto userDetails = userDetailService.getIdTokken(tokenUsername);

        ProductResponseDto productResponseDto = productService.getOneProduct(productId);

        if(productResponseDto.getStock() == 0) {
            return null;
        } else {

            OrderDto newOrder = new OrderDto();
            newOrder.setProductId(productId);
            newOrder.setProductName(productName);
            newOrder.setUserId(userDetails.getUid());
            newOrder.setUserName(userDetails.getName());
            newOrder.setPrice(productResponseDto.getPrice());
            OrderResponseDto orderResponseDto = orderService.createOrder(newOrder);
            productService.changeProductStock(productResponseDto.getNumber(), productResponseDto.getStock());

            return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
        }
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> getAllOrder() {
        List<OrderResponseDto> orderResponseDto = orderService.getAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }
    @GetMapping("/listByUserId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> getAllOrderListByUserId(String userId) {
        List<OrderResponseDto> orderResponseDto = orderService.getAllOrderListByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }
    @GetMapping("/listByProductId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> getAllOrderListByProductId(Long productId) {
        List<OrderResponseDto> orderResponseDto = orderService.getAllOrderListByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderResponseDto> getOrderById(long Id) {
        OrderResponseDto orderResponseDto = orderService.getOrderById(Id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }
}
