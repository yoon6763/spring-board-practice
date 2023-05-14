package uknow.board.practice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uknow.board.practice.entity.dto.SignInResultDto;
import uknow.board.practice.entity.dto.SignUpResultDto;
import uknow.board.practice.service.SignService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sign-api")
@Slf4j
public class SignController {
    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping(value = "/sign-in")
    public SignInResultDto signIn(
            @RequestParam String id,
            @RequestParam String password)
            throws RuntimeException {
        log.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", id);
        SignInResultDto signInResultDto = signService.signIn(id, password);

        if (signInResultDto.getCode() == 0) {
            log.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", id,
                    signInResultDto.getToken());
        }
        return signInResultDto;
    }

    @PostMapping(value = "/sign-up")
    public SignUpResultDto signUp(
            @RequestParam String id,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String role) {
        log.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****, name : {}, role : {}", id,
                name, role);
        SignUpResultDto signUpResultDto = signService.signUp(id, password, name, role);

        log.info("[signUp] 회원가입을 완료했습니다. id : {}", id);
        return signUpResultDto;
    }

    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        log.debug("[Sign-In ExceptionHandler] Error Message = {}",e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
