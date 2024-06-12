package com.xenominicrm.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xenominicrm.crm.dto.RuleDTO;
import com.xenominicrm.crm.services.AudienceService;


@RestController
@RequestMapping("/api/audiences")
public class AudienceController {
	
	@Autowired
	private AudienceService audienceService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/evaluate")
	public long evaluateAudienceSize(@RequestBody RuleDTO rules) {
	    return audienceService.evaluateAudienceSizeByQuery(rules);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/addrule")
    public String addAudienceRule(@RequestBody RuleDTO ruleDTO) {
        audienceService.addAudienceRule(ruleDTO);
        return "AudienceRule created Successfully";
    }

	

}
