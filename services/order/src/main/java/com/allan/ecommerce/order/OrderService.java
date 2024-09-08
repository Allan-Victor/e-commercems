package com.allan.ecommerce.order;

import com.allan.ecommerce.customer.CustomerClient;
import com.allan.ecommerce.exception.BusinessException;
import com.allan.ecommerce.product.ProductClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    public Integer createOrder(@Valid OrderRequest request) {
        // check the customer --> Open feign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot Create order:: No customer exists with provided id"));

        // purchase the products --> product microservice (restTemplate)

        // persist order

        // persist the order lines

        // start payment process

        // send the order confirmation  --> notification microservice(kafka)

        return null;
    }
}
