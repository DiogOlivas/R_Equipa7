
package com.upt.lp.Equipa7.service;

import com.upt.lp.Equipa7.DTO.UserDTO;
import com.upt.lp.Equipa7.entity.User;
import com.upt.lp.Equipa7.entity.Transaction;
import com.upt.lp.Equipa7.entity.Category;
import com.upt.lp.Equipa7.repository.UserRepository;
import com.upt.lp.Equipa7.repository.TransactionRepository;
import com.upt.lp.Equipa7.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.upt.lp.Equipa7.entity.UserType;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public UserService(UserRepository userRepository,TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    
    public User createUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        
        if (dto.getTransactionIds() != null && !dto.getTransactionIds().isEmpty()) {
            List<Transaction> transactions = transactionRepository.findAllById(dto.getTransactionIds());
            user.setTransactions(transactions);
        }

        if (dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()) {
            List<Category> categories = categoryRepository.findAllById(dto.getCategoryIds());
            user.setCategories(categories);
        }
        return userRepository.save(user);
    }

    
    public User updateUser(Long id, UserDTO dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getUsername() != null) existing.setUsername(dto.getUsername());
        if (dto.getEmail() != null) existing.setEmail(dto.getEmail());
        if (dto.getPassword() != null) existing.setPassword(dto.getPassword());

        if (dto.getUserType() != null) {
            try {
                existing.setUserType(UserType.valueOf(dto.getUserType().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid user type: " + dto.getUserType());
            }
        }

        if (dto.getTransactionIds() != null) {
            List<Transaction> transactions = transactionRepository.findAllById(dto.getTransactionIds());
            existing.setTransactions(transactions);
        }

        if (dto.getCategoryIds() != null) {
            List<Category> categories = categoryRepository.findAllById(dto.getCategoryIds());
            existing.setCategories(categories);
        }
        
        return userRepository.save(existing);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public long getUserCount() {
        return userRepository.count();
    }
}
