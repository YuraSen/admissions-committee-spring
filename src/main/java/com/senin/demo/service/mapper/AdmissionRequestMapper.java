package com.senin.demo.service.mapper;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.entity.AdmissionRequestEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AdmissionRequestMapper {
    AdmissionRequestDTO mapAdmissionRequestEntityToDTO(AdmissionRequestEntity admissionRequestEntity);

    AdmissionRequestEntity mapAdmissionRequestDTOtoEntity(AdmissionRequestDTO admissionRequestDTO);
}
