package com.xenominicrm.crm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xenominicrm.crm.entity.Campaign;
import com.xenominicrm.crm.repository.CampaignRepository;
import com.xenominicrm.crm.services.CampaignService;

@Service
public class CampaignServiceImpl implements CampaignService{

	@Autowired
	private CampaignRepository campaignRepository;

	@Override
	public List<Campaign> getAllCampaigns() {
		return campaignRepository.findAll();
	}

	@Override
	public Campaign getCampaignById(Integer id) {
		Campaign campaign = campaignRepository.findById(id).orElse(null);
		return campaign;
	}

	@Override
	public void addCampaign(Campaign campaign) {
		campaignRepository.save(campaign);
	}

}
