package com.application.cursova.service;

import com.application.cursova.model.FinancialProduct;
import com.application.cursova.repository.FinancialProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FinancialProductService {

    @Autowired
    private FinancialProductRepository financialProductRepository;

    public List<FinancialProduct> getAllFinancialProducts() {
        return financialProductRepository.findAll();
    }

    public FinancialProduct getFinancialProductById(Long id) {
        Optional<FinancialProduct> product = financialProductRepository.findById(id);
        return product.orElse(null);
    }

    public FinancialProduct createFinancialProduct(FinancialProduct financialProduct) {
        return financialProductRepository.save(financialProduct);
    }

    public FinancialProduct updateFinancialProduct(Long id, FinancialProduct updatedFinancialProduct) {
        Optional<FinancialProduct> optionalFinancialProduct = financialProductRepository.findById(id);
        if (optionalFinancialProduct.isPresent()) {
            FinancialProduct existingFinancialProduct = optionalFinancialProduct.get();
            existingFinancialProduct.setUserId(updatedFinancialProduct.getUserId());
            existingFinancialProduct.setAccountId(updatedFinancialProduct.getAccountId());
            existingFinancialProduct.setProductType(updatedFinancialProduct.getProductType());
            existingFinancialProduct.setAmount(updatedFinancialProduct.getAmount());
            existingFinancialProduct.setInterestRate(updatedFinancialProduct.getInterestRate());
            existingFinancialProduct.setStartDate(updatedFinancialProduct.getStartDate());
            existingFinancialProduct.setEndDate(updatedFinancialProduct.getEndDate());
            return financialProductRepository.save(existingFinancialProduct);
        } else {
            throw new RuntimeException("Financial product not found");
        }
    }

    public void deleteFinancialProduct(Long id) {
        financialProductRepository.deleteById(id);
    }

    public List<FinancialProduct> getFinancialProductsByProductType(String productType) {
        return financialProductRepository.findByProductType(productType);
    }

    public List<FinancialProduct> getFinancialProductsByAmount(BigDecimal amount) {
        return financialProductRepository.findByAmountGreaterThan(amount);
    }

    public List<FinancialProduct> getFinancialProductsByStartDate(Timestamp startDate) {
        return financialProductRepository.findByStartDate(startDate);
    }

    public List<FinancialProduct> getFinancialProductsByYear(int year) {
        return financialProductRepository.findByStartDateYear(year);
    }
}