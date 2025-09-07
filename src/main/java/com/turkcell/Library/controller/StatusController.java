package com.turkcell.Library.controller;

import com.turkcell.Library.dto.status.request.CreateStatusRequest;
import com.turkcell.Library.dto.status.request.UpdateStatusRequest;
import com.turkcell.Library.dto.status.response.CreatedStatusResponse;
import com.turkcell.Library.entity.Status;
import com.turkcell.Library.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/statuses")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<CreatedStatusResponse>> getAllStatuses() {
        List<Status> statuses = statusService.getAllStatuses();
        List<CreatedStatusResponse> response = statuses.stream()
                .map(this::convertToStatusResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatedStatusResponse> getStatusById(@PathVariable int id) {
        return statusService.getStatusById(id)
                .map(status -> ResponseEntity.ok(convertToStatusResponse(status)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CreatedStatusResponse> createStatus(@RequestBody CreateStatusRequest request) {
        try {
            Status status = convertToStatus(request);
            Status savedStatus = statusService.createStatus(status);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToStatusResponse(savedStatus));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatedStatusResponse> updateStatus(@PathVariable int id, @RequestBody UpdateStatusRequest request) {
        try {
            Status statusDetails = convertToStatus(request);
            Status updatedStatus = statusService.updateStatus(id, statusDetails);
            return ResponseEntity.ok(convertToStatusResponse(updatedStatus));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable int id) {
        try {
            statusService.deleteStatus(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private CreatedStatusResponse convertToStatusResponse(Status status) {
        return new CreatedStatusResponse(
                status.getStatus_id(),
                status.getStatus_name()
        );
    }

    private Status convertToStatus(CreateStatusRequest request) {
        Status status = new Status();
        status.setStatus_name(request.getStatusName());
        return status;
    }

    private Status convertToStatus(UpdateStatusRequest request) {
        Status status = new Status();
        status.setStatus_name(request.getStatusName());
        return status;
    }
}