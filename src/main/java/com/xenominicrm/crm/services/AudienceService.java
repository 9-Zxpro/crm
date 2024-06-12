package com.xenominicrm.crm.services;

import com.xenominicrm.crm.dto.RuleDTO;

public interface AudienceService {
	void addAudienceRule(RuleDTO rules);
	long evaluateAudienceSizeByQuery(RuleDTO rules);
}
