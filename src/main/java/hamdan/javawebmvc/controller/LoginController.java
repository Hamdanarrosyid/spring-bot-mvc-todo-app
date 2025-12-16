package hamdan.javawebmvc.controller;

import hamdan.javawebmvc.dto.LoginRequestDto;
import hamdan.javawebmvc.dto.LoginResponseDto;
import hamdan.javawebmvc.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final AuthService authService;

    @GetMapping("/auth/login")
    public String view(Model model) {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail("");
        loginRequestDto.setPassword("");
        model.addAttribute("loginForm", loginRequestDto);
        return "auth/login";
    }

    @PostMapping(value = "/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String post(@Valid @ModelAttribute("login")LoginRequestDto loginRequest, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }
        try {
            LoginResponseDto response = authService.login(loginRequest);
            session.setAttribute("user", response.getUser());
            log.info("Login Success with user: {}", response.getUser());
            log.info("Login Success with session: {}", session.getId());
            return "redirect:/dashboard";
        } catch (AuthenticationException e) {
            log.error("Login Failed with error: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            model.addAttribute("loginForm", loginRequest);
            return "auth/login";
        }
    }
}
