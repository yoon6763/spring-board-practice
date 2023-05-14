package uknow.board.practice.service;

import uknow.board.practice.entity.dto.SignInResultDto;
import uknow.board.practice.entity.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp(String id, String password, String name, String role);

    SignInResultDto signIn(String id, String password) throws RuntimeException;

}