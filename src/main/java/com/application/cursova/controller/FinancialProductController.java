package com.application.cursova.controller;

import com.application.cursova.model.FinancialProduct;
import com.application.cursova.service.FinancialProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/financial-products")
public class FinancialProductController {

    @Autowired
    private FinancialProductService financialProductService;

    @GetMapping
    public List<FinancialProduct> getAllFinancialProducts() {
        System.out.println("Всі фінансові продукти були виведені.");
        return financialProductService.getAllFinancialProducts();
    }

    @GetMapping("/{id}")
    public FinancialProduct getFinancialProductById(@PathVariable Long id) {
        System.out.println("Фінансовий продукт з ID " + id + " був виведений.");
        return financialProductService.getFinancialProductById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FinancialProduct> createFinancialProduct(@RequestBody FinancialProduct financialProduct) {
        FinancialProduct createdFinancialProduct = financialProductService.createFinancialProduct(financialProduct);
        return ResponseEntity.ok(createdFinancialProduct);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FinancialProduct> updateFinancialProduct(@PathVariable Long id, @RequestBody FinancialProduct financialProduct) {
        FinancialProduct updatedFinancialProduct = financialProductService.updateFinancialProduct(id, financialProduct);
        return ResponseEntity.ok(updatedFinancialProduct);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteFinancialProduct(@PathVariable Long id) {
        System.out.println("Фінансовий продукт видалено: " + id);
        financialProductService.deleteFinancialProduct(id);
    }

    @GetMapping("/product-type/{productType}")
    public List<FinancialProduct> getFinancialProductsByProductType(@PathVariable String productType) {
        System.out.println("Фінансові продукти з типом продукту " + productType + " були запитані.");
        return financialProductService.getFinancialProductsByProductType(productType);
    }

    @GetMapping("/amount/{amount}")
    public List<FinancialProduct> getFinancialProductsByAmount(@PathVariable BigDecimal amount) {
        System.out.println("Фінансові продукти з сумою " + amount + " були виведені.");
        return financialProductService.getFinancialProductsByAmount(amount);
    }

    @GetMapping("/start-date/{startDate}")
    public List<FinancialProduct> getFinancialProductsByStartDate(@PathVariable Timestamp startDate) {
        System.out.println("Фінансові продукти з початковою датою " + startDate + " були виведені.");
        return financialProductService.getFinancialProductsByStartDate(startDate);
    }

    @GetMapping("/year/{year}")
    public List<FinancialProduct> getFinancialProductsByYear(@PathVariable int year) {
        System.out.println("Фінансові продукти з роком " + year + " були виведені.");
        return financialProductService.getFinancialProductsByYear(year);
    }
}