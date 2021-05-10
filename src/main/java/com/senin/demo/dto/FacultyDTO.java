package com.senin.demo.dto;

import com.senin.demo.entity.AdmissionRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

import static java.util.Objects.nonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacultyDTO {
    private Long id;

    @NotBlank(message = "fill the name on English")
    @Length(max = 50, message = "name is too long")
    private String nameEn;

    @NotBlank(message = "fill the name on Ukrainian")
    @Length(max = 50, message = "name is too long")
    private String nameUk;

    @NotBlank(message = "fill the description on English")
    @Length(max = 2048, message = "description is too long")
    private String descriptionEn;

    @NotBlank(message = "fill the description on Ukrainian")
    @Length(max = 2048, message = "description is too long")
    private String descriptionUk;

    @NotNull(message = "Capacity can not be empty")
    @Min(value = 0,message = "Capacity can not be less than 0")
    @Max(value = 999,message = "Capacity can not be more than 999")
    private Integer budgetCapacity;

    @NotNull(message = "Capacity can not be empty")
    @Min(value = 0,message = "Capacity can not be less than 0")
    @Max(value = 999,message = "Capacity can not be more than 999")
    private Integer totalCapacity;

    @NotBlank(message = "fill the subject on English")
    @Length(max = 50, message = "subject name is too long")
    private String requiredSubject1En;

    @NotBlank(message = "fill the subject on Ukrainian")
    @Length(max = 50, message = "subject name is too long")
    private String requiredSubject1Uk;

    @NotBlank(message = "fill the subject on English")
    @Length(max = 50, message = "subject name is too long")
    private String requiredSubject2En;

    @NotBlank(message = "fill the subject on Ukrainian")
    @Length(max = 50, message = "subject name is too long")
    private String requiredSubject2Uk;

    @NotBlank(message = "fill the subject on English")
    @Length(max = 50, message = "subject name is too long")
    private String requiredSubject3En;

    @NotBlank(message = "fill the subject on Ukrainian")
    @Length(max = 50, message = "subject name is too long")
    private String requiredSubject3Uk;

    private boolean admissionOpen;

    @AssertTrue(message = "budget capacity must be less or equals to total capacity")
    private boolean isValid() {
        if (this.budgetCapacity != null && this.totalCapacity != null) {
            return this.budgetCapacity <= this.totalCapacity;
        }
        return false;
    }

    private List<AdmissionRequest> admissionRequestsList;

}
