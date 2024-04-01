package cl.bci.application.mapper;

import cl.bci.application.dto.user.PhoneDto;
import cl.bci.application.dto.user.UserDto;
import cl.bci.application.dto.user.UserResponseDto;
import cl.bci.domain.Phones;
import cl.bci.domain.Users;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

	private UserMapper() {
		
	}
	
	public static UserDto mapperUserToUserDTO(Users user) {
		UserDto userDto = new UserDto();
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		List<PhoneDto> phoneDtoList = new ArrayList<PhoneDto>();
		if(user.getPhoneList() != null && user.getPhoneList().size() > 0){
			for (Phones phone : user.getPhoneList()) {
				phoneDtoList.add(PhoneMapper.mapperPhoneToPhoneDTO(phone));
			}
		}
		userDto.setPhones(phoneDtoList);
		return userDto;
	}

	public static UserResponseDto mapperUserToUserResponseDTO(Users user) {
		UserResponseDto dto = new UserResponseDto();
		dto.setId(user.getUserId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setDateCreated(user.getCreated());
		dto.setDateModified(user.getModified());
		dto.setToken(user.getToken());
		dto.setIsactive(user.isIsactive());
		dto.setLastLogin(user.getLastLogin());

		List<PhoneDto> phoneDtoList = new ArrayList<PhoneDto>();
		if(user.getPhoneList() != null && user.getPhoneList().size() > 0){
			for (Phones phone : user.getPhoneList()) {
				phoneDtoList.add(PhoneMapper.mapperPhoneToPhoneDTO(phone));
			}
		}
		dto.setPhones(phoneDtoList);
		return dto;
	}
	
}
