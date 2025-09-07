package com.turkcell.Library.service;

import com.turkcell.Library.entity.Staff;
import com.turkcell.Library.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Optional<Staff> getStaffById(int id) {
        return staffRepository.findById(id);
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(int id, Staff staffDetails) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));

        staff.setFirstName(staffDetails.getFirstName());
        staff.setLastName(staffDetails.getLastName());

        return staffRepository.save(staff);
    }

    public void deleteStaff(int id) {
        if (!staffRepository.existsById(id)) {
            throw new RuntimeException("Staff not found with id: " + id);
        }
        staffRepository.deleteById(id);
    }

    public List<Staff> searchStaff(String searchTerm) {
        return staffRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                searchTerm, searchTerm);
    }
}
