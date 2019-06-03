package com.cinemapp.CinemaBE.mapper;

import com.cinemapp.CinemaBE.domain.User;
import com.cinemapp.CinemaBE.dto.UserDTO;

@org.mapstruct.Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO userToUserDTO(User entity);
	User userDTOToUser(UserDTO dto);
}
