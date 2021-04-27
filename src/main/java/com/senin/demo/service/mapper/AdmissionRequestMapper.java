package com.senin.demo.service.mapper;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.dto.UserDTO;
import com.senin.demo.entity.AdmissionRequestEntity;
import com.senin.demo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdmissionRequestMapper {
    AdmissionRequestMapper INSTANCE_ADMISSION_REQUEST = Mappers.getMapper(AdmissionRequestMapper.class);

    AdmissionRequestDTO AdmissionRequestEntityToDTO(AdmissionRequestEntity admissionRequestEntity);

    AdmissionRequestEntity AdmissionRequestDTOtoEntity(AdmissionRequestDTO admissionRequestDTO);
}
