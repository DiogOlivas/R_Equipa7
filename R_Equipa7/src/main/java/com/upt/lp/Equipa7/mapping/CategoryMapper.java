package com.upt.lp.Equipa7.mapping;

import java.util.List;
import java.util.stream.Collectors;

import com.upt.lp.Equipa7.DTO.CategoryDTO;
import com.upt.lp.Equipa7.entity.Category;
import com.upt.lp.Equipa7.entity.User;
import com.upt.lp.Equipa7.repository.UserRepository;

public class CategoryMapper {


	public static CategoryDTO toDTO(Category cat) {
		if (cat == null) return null;
		return new CategoryDTO(
				cat.getId(),
	            cat.getUser() != null ? cat.getUser().getId() : null,
				cat.getName(),
				cat.getDesc(),
				cat.getBudget(),
				cat.getTransactions() == null
	            ? List.of()
	            : cat.getTransactions().stream()
	                .map(t -> t.getId())
	                .collect(Collectors.toList())
				); 
	}

	public static Category toEntity(CategoryDTO dto, UserRepository userRepository) {
	    if (dto == null) return null;

	    Category category = new Category();

	    if (dto.getId() != null && dto.getId() != 0) {
	        category.setId(dto.getId());
	    }

	    category.setName(dto.getName());
	    category.setDesc(dto.getDesc());
	    category.setBudget(dto.getBudget());

	    if (dto.getUserId() != null) {
	        User user = userRepository.findById(dto.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found"));
	        category.setUser(user);
	    }
	    

	    return category;
	}
}
