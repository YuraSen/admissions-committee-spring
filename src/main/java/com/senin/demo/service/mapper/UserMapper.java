package com.senin.demo.service.mapper;

import com.senin.demo.dto.UserDTO;
import com.senin.demo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE_USER = Mappers.getMapper(UserMapper.class);

    UserDTO userEntityToDTO(UserEntity userEntity);

    UserDTO userDTOToEntity(UserDTO userDTO);
}
