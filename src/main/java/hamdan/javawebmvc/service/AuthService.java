package hamdan.javawebmvc.service;

import hamdan.javawebmvc.dto.LoginRequestDto;
import hamdan.javawebmvc.dto.LoginResponseDto;
import hamdan.javawebmvc.model.UserModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final Validator validator;

    public LoginResponseDto login(LoginRequestDto loginRequest) throws AuthenticationException {
        Set<ConstraintViolation<LoginRequestDto>> violations = this.validator.validate(loginRequest);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Invalid Login Request", violations);
        }

        String dummyEmail = "admin@example.com";
        if (!dummyEmail.equals(loginRequest.getEmail())) {
            throw new AuthenticationException("Invalid Email");
        }
        String dummyPassword = "admin123";
        if (!dummyPassword.equals(loginRequest.getPassword())) {
            throw new AuthenticationException("Invalid Password");
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        UserModel userModel = UserModel.builder().firstName("admin").lastName("admin").email(loginRequest.getEmail()).build();
        loginResponseDto.setUser(userModel);
        loginResponseDto.setSessionId(UUID.randomUUID().toString());
        return loginResponseDto;
    }
}
