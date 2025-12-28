package com.upt.lp.Equipa7.mapping;

import com.upt.lp.Equipa7.DTO.UserDTO;
import com.upt.lp.Equipa7.entity.User;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User user){
        if( user == null) return null;

        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getCategories().stream().map(c -> c.getId()).collect(Collectors.toList()),
            user.getTransactions().stream().map(t -> t.getId()).collect(Collectors.toList())
        );
    }

    public static User toEntity(UserDTO dto){
        if (dto == null) {
            return null;
        }else{ 
            User user = new User();

            if(dto.getId() != 0){
                user.setId(dto.getId());
            }

            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            return user;
        }  
    }
}
