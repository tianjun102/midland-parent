package com.midland.web.dao;

import com.midland.web.model.RegistrationProtocol;
import java.util.List;

public interface RegistrationProtocolMapper {

	RegistrationProtocol selectRegistrationProtocolById(Integer registrationProtocol);

	int deleteRegistrationProtocolById(Integer registrationProtocol);

	int updateRegistrationProtocolById(RegistrationProtocol registrationProtocol);

	int insertRegistrationProtocol(RegistrationProtocol registrationProtocol);

	List<RegistrationProtocol> findRegistrationProtocolList(RegistrationProtocol registrationProtocol);

}
