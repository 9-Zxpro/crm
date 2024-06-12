package com.xenominicrm.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xenominicrm.crm.entity.Campaign;
import com.xenominicrm.crm.entity.Customer;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
	@Query(value = "SELECT * FROM Customer WHERE :field :operator :val", nativeQuery = true)
    List<Customer> executeCustomQuery(@Param("field") String field, 
                                      @Param("operator") String operator, 
                                      @Param("val") Object value);
	
	@Query(value = "SELECT * FROM Campaign", nativeQuery = true)
	public List<Campaign> getllCampaigns();
}
