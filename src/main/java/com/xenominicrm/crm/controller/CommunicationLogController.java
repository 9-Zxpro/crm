package com.xenominicrm.crm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xenominicrm.crm.entity.CommunicationLog;
import com.xenominicrm.crm.repository.CommunicatonLogRepository;
import com.xenominicrm.crm.services.CommunicationLogService;

@RestController
@RequestMapping("/api/commlog")
public class CommunicationLogController {
	
	@Autowired
	private CommunicatonLogRepository communicatonLogRepository;
	
	@Autowired
	private CommunicationLogService communicationLogService;

	@PostMapping(value = "/updateStatus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String processJsonReactive(Map<String, Object> payload) {
        Long logId = ((Number) payload.get("logId")).longValue();
        String status = (String) payload.get("status");
        CommunicationLog log = communicatonLogRepository.findById(logId).orElse(null);
        if (log != null) {
			log.setStatus(status);
			communicatonLogRepository.save(log);
			return "Communication Log Status updated.";
		} else {
			return "Communication Log not found.";
		}
	}
	
	@PostMapping("/send")
	public String sendMessageToCustomers() {
		communicationLogService.sendMessageToCustomers();
		return "sent message";
	}
}
