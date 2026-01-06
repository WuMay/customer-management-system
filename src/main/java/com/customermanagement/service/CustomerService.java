package com.customermanagement.service;

import com.customermanagement.model.Customer;
import com.customermanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // 获取所有客户
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // 根据ID获取客户
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // 创建新客户
    public Customer createCustomer(Customer customer) {
        // 检查手机号是否已存在
        if (customerRepository.existsByPhone(customer.getPhone())) {
            throw new RuntimeException("手机号已存在");
        }
        // 检查身份证号是否已存在
        if (customerRepository.existsByIdCardNumber(customer.getIdCardNumber())) {
            throw new RuntimeException("身份证号已存在");
        }
        return customerRepository.save(customer);
    }

    // 更新客户信息
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("客户不存在"));

        // 如果手机号发生变化，检查新手机号是否已存在
        if (!customer.getPhone().equals(customerDetails.getPhone())) {
            if (customerRepository.existsByPhone(customerDetails.getPhone())) {
                throw new RuntimeException("手机号已存在");
            }
        }

        // 如果身份证号发生变化，检查新身份证号是否已存在
        if (!customer.getIdCardNumber().equals(customerDetails.getIdCardNumber())) {
            if (customerRepository.existsByIdCardNumber(customerDetails.getIdCardNumber())) {
                throw new RuntimeException("身份证号已存在");
            }
        }

        // 更新字段
        customer.setName(customerDetails.getName());
        customer.setPhone(customerDetails.getPhone());
        customer.setEmail(customerDetails.getEmail());
        customer.setAddress(customerDetails.getAddress());
        customer.setBirthday(customerDetails.getBirthday());
        customer.setIdCardNumber(customerDetails.getIdCardNumber());
        customer.setIdCardIssuePlace(customerDetails.getIdCardIssuePlace());
        customer.setIdCardIssueDate(customerDetails.getIdCardIssueDate());
        customer.setIdCardExpiryDate(customerDetails.getIdCardExpiryDate());

        return customerRepository.save(customer);
    }

    // 删除客户
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("客户不存在"));
        customerRepository.delete(customer);
    }

    // 搜索客户
    public List<Customer> searchCustomers(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllCustomers();
        }
        return customerRepository.searchCustomers(keyword.trim());
    }

    // 根据手机号查询
    public Optional<Customer> findByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    // 根据身份证号查询
    public Optional<Customer> findByIdCardNumber(String idCardNumber) {
        return customerRepository.findByIdCardNumber(idCardNumber);
    }
}
