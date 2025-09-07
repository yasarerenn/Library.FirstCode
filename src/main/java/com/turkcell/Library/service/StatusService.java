package com.turkcell.Library.service;

import com.turkcell.Library.entity.Status;
import com.turkcell.Library.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Optional<Status> getStatusById(int id) {
        return statusRepository.findById(id);
    }

    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    public Status updateStatus(int id, Status statusDetails) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + id));

        status.setStatus_name(statusDetails.getStatus_name());

        return statusRepository.save(status);
    }

    public void deleteStatus(int id) {
        if (!statusRepository.existsById(id)) {
            throw new RuntimeException("Status not found with id: " + id);
        }
        statusRepository.deleteById(id);
    }

    public Optional<Status> findStatusByName(String statusName) {
        return statusRepository.findByStatusName(statusName);
    }
}
