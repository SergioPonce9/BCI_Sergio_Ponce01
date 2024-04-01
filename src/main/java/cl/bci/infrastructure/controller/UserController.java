package cl.bci.infrastructure.controller;

import cl.bci.application.dto.general.GeneralResponseDto;
import cl.bci.application.dto.user.UserDto;
import cl.bci.application.dto.user.UserResponseDto;
import cl.bci.application.exception.EmailAlreadyExistException;
import cl.bci.application.service.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<GeneralResponseDto> getAll(){
        List<UserDto> userList = userService.getAll();
        GeneralResponseDto<List<UserDto> > response = new GeneralResponseDto<List<UserDto> >();
        response.setData(userList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getbyemail/{email}")
    public ResponseEntity<GeneralResponseDto> getByEmail(@PathVariable String email ){
        GeneralResponseDto<UserResponseDto> response = new GeneralResponseDto<UserResponseDto>();
        response.setData(userService.getByEmail(email));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/save", headers = {"Authorization"})
    public ResponseEntity<GeneralResponseDto> save(@RequestHeader HttpHeaders headers, @RequestBody @Valid UserDto dto) throws Exception {
        GeneralResponseDto<UserResponseDto> response = new GeneralResponseDto<UserResponseDto>();
        response.setData(userService.save(dto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<GeneralResponseDto> update(@RequestBody @Valid UserDto dto) throws Exception {
        GeneralResponseDto<Boolean> response = new GeneralResponseDto<Boolean>();
        response.setData(userService.update(dto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<GeneralResponseDto> delete(@PathVariable String email){
        GeneralResponseDto<Boolean> response = new GeneralResponseDto<Boolean>();
        response.setData(userService.delete(email));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


