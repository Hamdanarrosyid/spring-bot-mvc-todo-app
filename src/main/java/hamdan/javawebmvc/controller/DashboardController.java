package hamdan.javawebmvc.controller;

import hamdan.javawebmvc.model.TodoModel;
import hamdan.javawebmvc.model.UserModel;
import hamdan.javawebmvc.service.TodoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final TodoService todoService;
    @GetMapping("/dashboard")
    public String viewDashboard(@SessionAttribute(name = "user", required = false) UserModel user, Model model){
        if (user == null){
            return "redirect:/auth/login";
        }

        List<TodoModel> todos = todoService.getTodos();

        model.addAttribute("todos", todos);
        model.addAttribute("user", user);

        return "dashboard";
    }

    @PostMapping("/auth/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/auth/login";
    }
}
