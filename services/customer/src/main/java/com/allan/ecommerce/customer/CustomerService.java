package com.allan.ecommerce.customer;

import com.allan.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest updateRequest) {
        var customer = repository.findById(updateRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        mergeCustomer(customer,updateRequest);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest updateRequest) {
        if (StringUtils.isNotBlank(updateRequest.firstName())){
            customer.setFirstName(updateRequest.firstName());
        }
        if (StringUtils.isNotBlank(updateRequest.lastName())){
            customer.setLastName(updateRequest.lastName());
        }
        if (StringUtils.isNotBlank(updateRequest.email())){
            customer.setEmail(updateRequest.email());
        }
        if (updateRequest.address() != null){
            customer.setAddress(updateRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::toCustomerResponse)
                .orElseThrow(() -> new CustomerNotFoundException("No customer found with provided Id"));
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
