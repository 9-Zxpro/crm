package com.xenominicrm.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xenominicrm.crm.entity.CommunicationLog;

@Repository
public interface CommunicatonLogRepository extends JpaRepository<CommunicationLog, Long> {

}
