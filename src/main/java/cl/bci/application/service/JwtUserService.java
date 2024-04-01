package cl.bci.application.service;

import cl.bci.domain.Users;
import cl.bci.infrastructure.repository.PhonesRepository;
import cl.bci.infrastructure.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PhonesRepository phonesRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users user = getUserByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("Users not found with name: " + name);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                new ArrayList<>());
    }

    public Users getUserByName(String name) {
        Users user = usersRepository.findOneByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("Users not found with name: " + name);
        }
        return user;
    }

    public Boolean updateUser(Users user) {
        if (user == null) {
            throw new UsernameNotFoundException("Users not found");
        }
        try {
            usersRepository.save(user);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

}
