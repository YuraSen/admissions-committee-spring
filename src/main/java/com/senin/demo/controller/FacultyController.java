package com.senin.demo.controller;

import com.senin.demo.dto.FacultyDTO;
import com.senin.demo.service.FacultyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    @GetMapping("/")
    public List<FacultyDTO> showAll() {
        return facultyService.findAll();
    }

    @GetMapping("/{id}")
    public FacultyDTO showById(@PathVariable("id") Long id) {
        return facultyService.findById(id);
    }

    @PostMapping("/")
    public FacultyDTO create(@RequestBody FacultyDTO transactionDTO) {
        return facultyService.save(transactionDTO);
    }

    @PutMapping("/{id}")
    public FacultyDTO edit(@RequestBody FacultyDTO transactionDTO) {
        return facultyService.update(transactionDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        facultyService.deleteById(id);
    }
}
