package com.customermanagement.repository;

import com.customermanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 根据手机号查询
    Optional<Customer> findByPhone(String phone);

    // 根据身份证号查询
    Optional<Customer> findByIdCardNumber(String idCardNumber);

    // 根据姓名模糊查询
    List<Customer> findByNameContaining(String name);

    // 根据身份证号模糊查询
    List<Customer> findByIdCardNumberContaining(String idCardNumber);

    // 检查手机号是否存在
    boolean existsByPhone(String phone);

    // 检查身份证号是否存在
    boolean existsByIdCardNumber(String idCardNumber);

    // 根据姓名和手机号模糊查询
    @Query("SELECT c FROM Customer c WHERE " +
           "LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "c.phone LIKE CONCAT('%', :keyword, '%') OR " +
           "c.idCardNumber LIKE CONCAT('%', :keyword, '%')")
    List<Customer> searchCustomers(@Param("keyword") String keyword);
}
