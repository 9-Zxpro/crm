package com.xenominicrm.crm.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xenominicrm.crm.dto.RuleDTO;
import com.xenominicrm.crm.entity.Campaign;
import com.xenominicrm.crm.repository.CampaignRepository;
import com.xenominicrm.crm.services.AudienceService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class AudienceServiceImpl implements AudienceService {
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void addAudienceRule(RuleDTO ruleDTO) {
		Campaign campaign = new Campaign(); 
		campaign.setField(ruleDTO.getField());
		campaign.setOperator(ruleDTO.getOperator());
		campaign.setValue(ruleDTO.getValue());
		campaign.setAudienceSize(evaluateAudienceSizeByQuery(ruleDTO));
		campaign.setCreatedAt(new Date());
		campaignRepository.save(campaign);
	}
	
	
	private Query buildQuery(String col, String field, String op, Object val) {
		String q = "SELECT " + col +" FROM customer WHERE "+ field + " " + op + ":val";
		Query query = entityManager.createNativeQuery(q);
		return query.setParameter("val", val);
	}
	
	Long executeCustomFloatQuery(String field, String op, Float val) {
		return ((Number) buildQuery("COUNT(*)", field, op, val).getSingleResult()).longValue();
	}
	Long executeCustomDateQuery(String field, String op, String val) {
		return ((Number) buildQuery("COUNT(*)", field, op, val).getSingleResult()).longValue();
	}

	@Override
	public long evaluateAudienceSizeByQuery(RuleDTO rules) {
		if("last_visit_date".equals(rules.getField())) {
			return executeCustomDateQuery(rules.getField(), rules.getOperator(), rules.getValue());
		}else {
			return executeCustomFloatQuery(rules.getField(), rules.getOperator(), Float.parseFloat(rules.getValue()));
		}
	}
	
	
}
