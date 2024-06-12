package com.xenominicrm.crm.services;

import java.util.List;

import com.xenominicrm.crm.entity.CommunicationLog;

public interface CommunicationLogService {
	List<CommunicationLog> sendMessageToCustomers();

	void updateStatus(Long logId, String status);
}
