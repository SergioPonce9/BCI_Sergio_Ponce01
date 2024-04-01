package cl.bci.application.mapper;

import cl.bci.application.dto.user.PhoneDto;
import cl.bci.domain.Phones;

public class PhoneMapper {

	private PhoneMapper() {
		
	}
	
	public static PhoneDto mapperPhoneToPhoneDTO(Phones phone) {
		PhoneDto phones = new PhoneDto();
		phones.setCitycode(phone.getCityCode());
		phones.setContrycode(phone.getContryCode());
		phones.setNumber(phone.getNumber());
		return phones;
	}
	
	
}
