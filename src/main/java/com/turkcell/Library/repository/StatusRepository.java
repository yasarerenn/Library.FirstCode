package com.turkcell.Library.repository;

import com.turkcell.Library.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    @Query("SELECT s FROM Status s WHERE s.status_name = :statusName")
    Optional<Status> findByStatusName(@Param("statusName") String statusName);
}