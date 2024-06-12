package com.xenominicrm.crm.services;

import java.util.List;

import com.xenominicrm.crm.entity.Campaign;

public interface CampaignService {
	List<Campaign> getAllCampaigns();
	
	Campaign getCampaignById(Integer id);

	void addCampaign(Campaign campaign);
}
