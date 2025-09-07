package com.turkcell.Library.controller;

import com.turkcell.Library.dto.staff.request.CreateStaffRequest;
import com.turkcell.Library.dto.staff.request.UpdateStaffRequest;
import com.turkcell.Library.dto.staff.response.CreatedStaffResponse;
import com.turkcell.Library.entity.Staff;
import com.turkcell.Library.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public ResponseEntity<List<CreatedStaffResponse>> getAllStaff() {
        List<Staff> staff = staffService.getAllStaff();
        List<CreatedStaffResponse> response = staff.stream()
                .map(this::convertToStaffResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatedStaffResponse> getStaffById(@PathVariable int id) {
        return staffService.getStaffById(id)
                .map(staff -> ResponseEntity.ok(convertToStaffResponse(staff)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CreatedStaffResponse> createStaff(@RequestBody CreateStaffRequest request) {
        try {
            Staff staff = convertToStaff(request);
            Staff savedStaff = staffService.createStaff(staff);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToStaffResponse(savedStaff));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatedStaffResponse> updateStaff(@PathVariable int id, @RequestBody UpdateStaffRequest request) {
        try {
            Staff staffDetails = convertToStaff(request);
            Staff updatedStaff = staffService.updateStaff(id, staffDetails);
            return ResponseEntity.ok(convertToStaffResponse(updatedStaff));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable int id) {
        try {
            staffService.deleteStaff(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<CreatedStaffResponse>> searchStaff(@RequestParam String searchTerm) {
        List<Staff> staff = staffService.searchStaff(searchTerm);
        List<CreatedStaffResponse> response = staff.stream()
                .map(this::convertToStaffResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private CreatedStaffResponse convertToStaffResponse(Staff staff) {
        return new CreatedStaffResponse(
                staff.getStaff_Id(),
                staff.getFirstName(),
                staff.getLastName()
        );
    }

    private Staff convertToStaff(CreateStaffRequest request) {
        Staff staff = new Staff();
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        return staff;
    }

    private Staff convertToStaff(UpdateStaffRequest request) {
        Staff staff = new Staff();
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        return staff;
    }
}