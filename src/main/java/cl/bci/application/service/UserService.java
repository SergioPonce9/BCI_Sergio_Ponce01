package cl.bci.application.service;

import cl.bci.application.dto.user.UserResponseDto;
import cl.bci.application.exception.EmailAlreadyExistException;
import cl.bci.application.mapper.UserMapper;
import cl.bci.application.dto.user.PhoneDto;
import cl.bci.application.dto.user.UserDto;
import cl.bci.application.service.contracts.IUserService;
import cl.bci.domain.Phones;
import cl.bci.domain.Users;
import cl.bci.infrastructure.repository.PhonesRepository;
import cl.bci.infrastructure.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PhonesRepository phonesRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public List<UserDto> getAll() {
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        List<Users> userList = usersRepository.findAll();
        if(userList != null && userList.size()>0) {
            for (Users user : userList){
                userDtoList.add(UserMapper.mapperUserToUserDTO(user));
            }
        }
        return userDtoList;
    }

    @Override
    public UserResponseDto getByEmail(String email) {
        Users user = usersRepository.findOneByEmail(email);
        return UserMapper.mapperUserToUserResponseDTO(user);
    }

    @Override
    @Transactional(rollbackFor = {EmailAlreadyExistException.class})
    public UserResponseDto save(UserDto userDto) throws Exception {
        emailAlreadyExist(userDto.getEmail());

        Users user = new Users();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(bcryptEncoder.encode(userDto.getPassword()));

        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken("Init");
        user.setIsactive(true);
        user = usersRepository.save(user);

        String token = tokenService.generateAuthenticationToken(userDto.getName(), userDto.getPassword());
        user.setToken(token);
        user.setModified(new Date());
        usersRepository.save(user);

        if(userDto.getPhones() != null && userDto.getPhones().size() > 0){
            for (PhoneDto phoneDto : userDto.getPhones()  ) {
                    Phones phone = new Phones();
                    phone.setNumber(phoneDto.getNumber());
                    phone.setCityCode(phoneDto.getCitycode());
                    phone.setContryCode(phoneDto.getContrycode());
                    phone.setUserId(user);
                    phonesRepository.save(phone);
            }
        }

        Users userResponse = usersRepository.findOneByEmail(userDto.getEmail());
        return UserMapper.mapperUserToUserResponseDTO(userResponse);
    }

    @Override
    @Transactional(rollbackFor = {EmailAlreadyExistException.class})
    public boolean update(UserDto userDto) throws Exception {
        emailAlreadyExist(userDto.getEmail());
        Users user = usersRepository.findOneByName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(bcryptEncoder.encode(userDto.getPassword()));

        String token = tokenService.generateAuthenticationToken(userDto.getName(), userDto.getPassword());
        user.setToken(token);
        user.setModified(new Date());
        usersRepository.save(user);

        if(userDto.getPhones() != null && userDto.getPhones().size() > 0){
            for (PhoneDto phoneDto : userDto.getPhones()  ) {
                if(phonesRepository.findOneByNumberAndUserId(phoneDto.getNumber(),user) != null) {
                    Phones phone = new Phones();
                    phone.setCityCode(phoneDto.getCitycode());
                    phone.setContryCode(phoneDto.getContrycode());
                    phonesRepository.save(phone);
                }
            }
        }
        usersRepository.save(user);
        return true;
    }

    @Override
    public boolean delete(String email) {
        Users user = usersRepository.findOneByEmail(email);
        usersRepository.delete(user);
        return true;
    }

    private void emailAlreadyExist(String email) throws EmailAlreadyExistException {
        if(usersRepository.findOneByEmail(email) != null){
            throw new EmailAlreadyExistException("El correo ya registrado");
        }
    }

}
