package com.senin.demo.controller;


import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.service.StatementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class StatementController {
    private static final String FACULTY_STATEMENT = "faculty_statement";
    private static final String FACULTY_ID = "faculty_id";
    private final StatementService statementService;

    @GetMapping("/admin/statement/faculty/{id}")
    public String facultyStatement(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute(FACULTY_STATEMENT, statementService.getStatementForFacultyWithId(id));
        model.addAttribute(FACULTY_ID, id);
        return "/admin/statement";
    }

    @PostMapping("/admin/statement/finalize")
    public String facultyStatementFinalize(FacultyDTO facultyDTO,
                                           @AuthenticationPrincipal User currentUser) {
        statementService.facultyStatementFinalize(facultyDTO, currentUser.getUsername());

        return "redirect:/admin/pdf/download";
    }

    @GetMapping(value = "/admin/pdf/download")
    public ResponseEntity<byte[]> getPDF() {
        return statementService.getPDF();
    }
}