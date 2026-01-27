package com.upt.lp.Equipa7.controller;

import com.upt.lp.Equipa7.service.TransactionService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.upt.lp.Equipa7.DTO.TransactionDTO;
import com.upt.lp.Equipa7.entity.Transaction;
import com.upt.lp.Equipa7.mapping.TransactionMapper;
import com.upt.lp.Equipa7.repository.CategoryRepository;
import com.upt.lp.Equipa7.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	 private final TransactionService transactionService;
	    private final UserRepository userRepository;
	    private final CategoryRepository categoryRepository;

	    public TransactionController(TransactionService transactionService, UserRepository userRepository, CategoryRepository categoryRepository) {
	    	this.transactionService = transactionService;
	    	this.userRepository = userRepository;
	    	this.categoryRepository = categoryRepository;
	    }

    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions().stream()
                .filter(t -> t != null)                 
                .map(TransactionMapper::toDTO)          
                .toList();
    }

    @GetMapping("/{id}")
    public TransactionDTO getTransactionById(@PathVariable Long id) {
        Transaction tx = transactionService.getTransaction(id);
        if (tx == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found");
        }
        return TransactionMapper.toDTO(tx);
    }

    @PostMapping
    public TransactionDTO create(@RequestBody TransactionDTO dto) {
        Transaction tx = transactionService.createTransaction(dto);
        return TransactionMapper.toDTO(tx);
    }

    @PutMapping("/{id}")
    public TransactionDTO update(@PathVariable Long id, @RequestBody TransactionDTO dto) {
        Transaction txToUpdate = TransactionMapper.toEntity(dto, userRepository, categoryRepository);
        Transaction updatedTx = transactionService.updateTransaction(id, txToUpdate);
        return TransactionMapper.toDTO(updatedTx);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
