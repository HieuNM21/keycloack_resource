package com.bkav.keycloackresource.Controller;

import com.bkav.keycloackresource.Dto.CreateUserRequestDTO;
import com.bkav.keycloackresource.Dto.Identity.TokenExchangeResponse;
import com.bkav.keycloackresource.Dto.LoginRequestDto;
import com.bkav.keycloackresource.Dto.UserResponseDTO;
import com.bkav.keycloackresource.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "redirect:/oauth2/authorization/keycloak";
    }

    @PostMapping("/public/api/login")
    public ResponseEntity<TokenExchangeResponse> loginApi(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    @PostMapping("/public/api/register")
    public ResponseEntity<String> registerApi(@RequestBody CreateUserRequestDTO req) {
        try {
            UserResponseDTO user = userService.createUser(req);
            return ResponseEntity.ok("Tạo user thành công: " + user.getUsername());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Tạo user thất bại: " + e.getMessage());
        }
    }

    @GetMapping("/public/hello")
    public String hello() {
        return "Hello Public!";
    }

    @GetMapping("/user/profile")
    public String profile() {
        return "Hello User - token hợp lệ!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/dashboard")
    public String admin() {
        return "Hello Admin!";
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(@RequestHeader(value = "X-User-Id", required = false) String userId) {
        System.out.println("Received X-User-Id: " + userId);
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody CreateUserRequestDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}