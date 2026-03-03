package com.traceability.traceability.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traceability.traceability.entiti.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {}