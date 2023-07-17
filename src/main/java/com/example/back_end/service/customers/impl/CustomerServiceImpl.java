package com.example.back_end.service.customers.impl;

import com.example.back_end.dto.ICustomersDto;
import com.example.back_end.model.Customers;
import com.example.back_end.repository.ICustomerRepository;
import com.example.back_end.service.customers.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Page<ICustomersDto> findAllCustomer(Pageable pageable) {
        return customerRepository.finAllCustomer(pageable);
    }

    @Override
    public Customers findByIdCustomer(Long id) {
        return customerRepository.findCustomersById(id);
    }

    @Override
    public Page<ICustomersDto> searchCustomer(Pageable pageable, String name) {
        return customerRepository.searchCustomers(pageable,name);
    }



}