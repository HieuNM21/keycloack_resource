package com.bkav.keycloackresource.Service;


import com.bkav.keycloackresource.Dto.CreateUserRequestDTO;
import com.bkav.keycloackresource.Dto.LoginRequestDto;
import com.bkav.keycloackresource.Dto.UserResponseDTO;
import com.bkav.keycloackresource.Dto.Identity.TokenExchangeResponse;

import java.util.List;

public interface IUserService {
    UserResponseDTO createUser(CreateUserRequestDTO dto);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, CreateUserRequestDTO dto);
    void deleteUser(Long id);
    TokenExchangeResponse login(LoginRequestDto dto);
}