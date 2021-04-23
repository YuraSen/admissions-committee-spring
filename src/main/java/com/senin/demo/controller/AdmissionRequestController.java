package com.senin.demo.controller;

import com.senin.demo.dto.AdmissionRequestDTO;
import com.senin.demo.dto.UserDTO;
import com.senin.demo.service.AdmissionRequestService;
import com.senin.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/user")
public class AdmissionRequestController {
    private final AdmissionRequestService admissionRequestService;

    @GetMapping("/")
    public List<AdmissionRequestDTO> showAll() {
        return admissionRequestService.findAll();
    }

    @GetMapping("/{id}")
    public AdmissionRequestDTO showById(@PathVariable("id") Long id) {
        return admissionRequestService.findById(id);
    }

    @PostMapping("/")
    public AdmissionRequestDTO create(@RequestBody AdmissionRequestDTO admissionRequestDTO) {
        return admissionRequestService.save(admissionRequestDTO);
    }

    @PutMapping("/{id}")
    public AdmissionRequestDTO edit(@RequestBody AdmissionRequestDTO admissionRequestDTO) {
        return admissionRequestService.update(admissionRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        admissionRequestService.deleteById(id);
    }
}
