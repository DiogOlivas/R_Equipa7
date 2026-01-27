package com.upt.lp.Equipa7.service;

import org.springframework.stereotype.Service;

import com.upt.lp.Equipa7.repository.CategoryRepository;
import com.upt.lp.Equipa7.repository.TransactionRepository;
import com.upt.lp.Equipa7.repository.UserRepository;

import java.time.LocalDate;
import java.util.*;

import com.upt.lp.Equipa7.DTO.TransactionDTO;
import com.upt.lp.Equipa7.entity.Category;
import com.upt.lp.Equipa7.entity.Transaction;
import com.upt.lp.Equipa7.entity.User;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
    public List<com.upt.lp.Equipa7.entity.Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public Transaction createTransaction(TransactionDTO dto) {      
    	Transaction transaction = new Transaction();

    	User user = userRepository.findById(dto.getUserId())
    			.orElseThrow(() -> new RuntimeException("User not found"));
        
    	transaction.setUser(user);

 
    	if (dto.getCategoryId() != null) {
        
    		Category category = categoryRepository.findById(dto.getCategoryId())
    				.orElseThrow(() -> new RuntimeException("Category not found"));
            transaction.setCategory(category);
        }
    	
    	LocalDate date = LocalDate.parse(dto.getDate());
    	transaction.setDate(date);
        transaction.setDescription(dto.getDescription());
    	transaction.setValue(dto.getValue());
    	transaction.setPaymentMethod(dto.getPaymentMethod());
    	

    	return transactionRepository.save(transaction);
    }    

    public Transaction saveTransaction(Transaction t) {
        return transactionRepository.save(t);
    }
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Transaction existing = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        existing.setValue(updatedTransaction.getValue());
        existing.setDescription(updatedTransaction.getDescription());
        existing.setDate(updatedTransaction.getDate());
        existing.setPaymentMethod(updatedTransaction.getPaymentMethod());
        existing.setUser(updatedTransaction.getUser());
        existing.setCategory(updatedTransaction.getCategory());

        return transactionRepository.save(existing);
    }
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}