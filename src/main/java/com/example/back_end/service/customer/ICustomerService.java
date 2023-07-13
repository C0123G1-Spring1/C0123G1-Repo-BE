package com.example.back_end.service.customer;
import com.example.back_end.dto.CustomerListDTO;
import com.example.back_end.model.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ICustomerService {

    Page<CustomerListDTO> findByNameProduct(String name, Pageable pageable);
    void deleteById(int id);
}