package com.senin.demo.service.mapper;

import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.entity.FacultyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FacultyMapper {
    FacultyDTO mapFacultyEntityToDTO(FacultyEntity facultyEntity);

    FacultyEntity mapFacultyDTOToEntity(FacultyDTO facultyDTO);
}
