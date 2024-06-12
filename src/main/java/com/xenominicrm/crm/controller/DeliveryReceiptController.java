package com.xenominicrm.crm.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xenominicrm.crm.services.CommunicationLogService;

@RestController
@RequestMapping("/api/commlog")
public class DeliveryReceiptController {
	
	@Autowired
    private CommunicationLogService communicationLogService;

    @PostMapping("/{id}")
    public ResponseEntity<String> sendStatus(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        String status = (String) payload.get("status");
        communicationLogService.updateStatus(id, status);
        return ResponseEntity.ok("Status updated successfully for log with ID: " + id);
    }

}
