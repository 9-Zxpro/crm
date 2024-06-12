package com.xenominicrm.crm.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xenominicrm.crm.entity.Campaign;
import com.xenominicrm.crm.entity.CommunicationLog;
import com.xenominicrm.crm.entity.Customer;
import com.xenominicrm.crm.repository.CampaignRepository;
import com.xenominicrm.crm.repository.CommunicatonLogRepository;
import com.xenominicrm.crm.services.CommunicationLogService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class CommunicationLogServiceImpl implements CommunicationLogService {

	@Autowired
	private CommunicatonLogRepository communicatonLogRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private CampaignRepository campaignRepository;
	
	private Query buildQuery(String field, String op, Object val) {
		String q = "SELECT * FROM customer WHERE "+ field + " " + op + ":val";
		Query query = entityManager.createNativeQuery(q, Customer.class);
	    return query.setParameter("val", val);
		
	}
	
	List<Customer> executeCustomFloatQuery(String field, String op, Float val) {
		return buildQuery(field, op, val).getResultList();
	}
	List<Customer> executeCustomDateQuery(String field, String op, String val) {
		return buildQuery(field, op, val).getResultList();
	}
	
	public List<Customer> evaluateCustomers(String field, String op, String val) {
		 if ("last_visit_date".equals(field)) {
	            return executeCustomDateQuery(field, op, val);
	        } else {
	            try {
	                Float floatVal = Float.parseFloat(val);
	                return executeCustomFloatQuery(field, op, floatVal);
	            } catch (NumberFormatException e) {
	                throw new IllegalArgumentException("Invalid float value: " + val, e);
	            }
	        }
	}

	@Override
	public List<CommunicationLog> sendMessageToCustomers() {
		List<CommunicationLog> logs = new ArrayList<>();
		List<Campaign> campaigns = campaignRepository.getllCampaigns();	
		for (Campaign campaign : campaigns) {
		
	        List<Customer> campaignCustomers = evaluateCustomers(campaign.getField(), campaign.getOperator(), campaign.getValue());
	        campaignCustomers.forEach((e) -> {
				System.out.println(e.getTotalSpends()+" "+e.getTotalVisits()+" "+e.getLastVisitDate());
			});
	        for (Customer c : campaignCustomers) {
	        	CommunicationLog cLog = new CommunicationLog();
				cLog.setName(c.getName());
				cLog.setEmail(c.getEmail());

				cLog.setMessage("Hi " + c.getName() + ", here is 15% off on your next order.");
				cLog.setStatus("pending");
				CommunicationLog savedLog = communicatonLogRepository.save(cLog);
				
				logs.add(savedLog);
				deliveryReceiptController(savedLog.getId());
			}
		}
		return logs;
	}

	@Override
	public void updateStatus(Long logId, String status) {
		Optional<CommunicationLog> optionalLog = communicatonLogRepository.findById(logId);
		if (optionalLog.isPresent()) {
			CommunicationLog log = optionalLog.get();
			log.setStatus(status);
			communicatonLogRepository.save(log);
		} else {
			throw new RuntimeException("Communication log with ID " + logId + " not found.");
		}
	}

	private void deliveryReceiptController(Long logId) {
		String urlString = "http://localhost:8082/api/commlog/" + logId;
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		Map<String, Object> body = new HashMap<>();
		body.put("logId", logId);
		String status = Math.random() > 0.1 ? "SENT" : "FAILED";
		body.put("status", status);
		HttpEntity<Map<String, Object>> reqEntity = new HttpEntity<>(body, httpHeader);
		restTemplate.postForEntity(urlString, reqEntity, String.class);
	}

}
