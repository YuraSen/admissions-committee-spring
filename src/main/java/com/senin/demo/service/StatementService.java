package com.senin.demo.service;


import com.senin.demo.dto.AdmissionRequestStatus;
import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.entity.AdmissionRequest;
import com.senin.demo.entity.Faculty;
import com.senin.demo.entity.StatementElement;
import com.senin.demo.exception.CanNotMakePDFException;
import com.senin.demo.exception.StatementCreationException;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatementService {
    private static final String FILE_NAME = "src/main/resources/JasperDesign.jrxml";
    private static final String OUT_FILE = "src/main/resources/public/Reports.pdf";
    private static final String CAN_NOT_CREATE_STATEMENT_REPORT = "Can not create statement report";
    private static final String CAN_NOT_PREPARE_PDF_STATEMENT = "Can not prepare PDF statement";

    private final FacultyService facultyService;

    public List<AdmissionRequest> getStatementForFacultyWithId(Long id) {
        Faculty faculty = facultyService.getById(id);
        return getSortedListOfRequestForFaculty(faculty);
    }

    private List<AdmissionRequest> getSortedListOfRequestForFaculty(Faculty faculty) {
        return faculty.getAdmissionRequestList()
                .stream()
                .filter(x -> x.getAdmissionRequestStatus() == AdmissionRequestStatus.APPROVED)
                .sorted(
                        Comparator.comparingInt(AdmissionRequest::getSumOfGrades).reversed()
                                .thenComparing(AdmissionRequest::getCreationDateTime))
                .limit(faculty.getTotalCapacity())
                .collect(Collectors.toList());
    }

    public void facultyStatementFinalize(FacultyDTO facultyDTO, String author) {
        List<AdmissionRequest> admissionRequests = getStatementForFacultyWithId(facultyDTO.getId());
        List<StatementElement> statementElementList = getStatementElements(admissionRequests);
        facultyService.blockUnblockRegistration(facultyDTO);
        try {
            createPdfReport(statementElementList, author);
        } catch (FileNotFoundException | JRException e) {
            System.out.println(e);
        }
    }

    private List<StatementElement> getStatementElements(List<AdmissionRequest> admissionRequests) {
        List<StatementElement> statementElementList = new ArrayList<>();
        for (int i = 0; i < admissionRequests.size(); i++) {
            statementElementList.add(StatementElement.builder()
                    .facultyName(admissionRequests.get(i).getFaculty().getNameEn())
                    .firstName(admissionRequests.get(i).getApplicant().getApplicantProfile().getFirstName())
                    .lastName(admissionRequests.get(i).getApplicant().getApplicantProfile().getLastName())
                    .email(admissionRequests.get(i).getApplicant().getApplicantProfile().getEmail())
                    .grade(admissionRequests.get(i).getSumOfGrades())
                    .contactNumber(admissionRequests.get(i).getApplicant().getApplicantProfile().getPhoneNumber())
                    .status(i >= admissionRequests.get(i).getFaculty().getBudgetCapacity() ? "Contract" : "Budget")
                    .build());
        }
        return statementElementList;
    }

    public ResponseEntity<byte[]> getPDF() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "src\\main\\resources\\public\\Reports.pdf";
        File file = new File(filename);

        byte[] fileContent = getFileContent(file);

        headers.add("content-disposition", "inline;filename=" + filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }

    private void createPdfReport(final List<StatementElement> statementElementList, String author) throws JRException, FileNotFoundException {
        File templateFile = ResourceUtils.getFile(FILE_NAME);
        JasperReport jasperReport = JasperCompileManager.compileReport(templateFile.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(statementElementList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("author", author);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        File file = new File(OUT_FILE);
        OutputStream outputSteam = new FileOutputStream(file);

        JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);
    }

    private byte[] getFileContent(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new CanNotMakePDFException(CAN_NOT_PREPARE_PDF_STATEMENT);
        }
    }

}
