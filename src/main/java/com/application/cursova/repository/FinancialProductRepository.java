package com.application.cursova.repository;

import com.application.cursova.model.FinancialProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FinancialProductRepository extends JpaRepository<FinancialProduct, Long> {
    List<FinancialProduct> findByProductType(String productType);
    List<FinancialProduct> findByAmountGreaterThan(BigDecimal amount);
    List<FinancialProduct> findByStartDate(Timestamp startDate);

    @Query("SELECT f FROM FinancialProduct f WHERE YEAR(f.startDate) = :year")
    List<FinancialProduct> findByStartDateYear(int year);
}