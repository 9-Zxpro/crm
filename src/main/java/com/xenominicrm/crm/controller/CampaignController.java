package com.xenominicrm.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xenominicrm.crm.entity.Campaign;
import com.xenominicrm.crm.services.CampaignService;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {
	
	@Autowired
	private CampaignService campaignService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	public List<Campaign> getAllCampaigns() {
		return campaignService.getAllCampaigns();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/byId")
	public Campaign getCampaignsById(@RequestParam Integer id) {
		return campaignService.getCampaignById(id);
	}
	
	@PostMapping
    public String addCampaign(@RequestBody Campaign campaign) {
		campaignService.addCampaign(campaign);
        return "Audience created Successfully";
    }

}

