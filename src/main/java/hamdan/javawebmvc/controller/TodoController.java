package hamdan.javawebmvc.controller;

import hamdan.javawebmvc.dto.TodoRequest;
import hamdan.javawebmvc.service.TodoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping(value = "/todos", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String post(@Valid @ModelAttribute("todos") TodoRequest todoRequest, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "/dashboard";
        }

        todoService.addTodo(todoRequest);
        return "redirect:/dashboard";
    }

    @PostMapping(value = "/todos/{todoId}/delete")
    public String delete(@PathVariable("todoId") Integer todoId, HttpSession session) {
        todoService.deleteTodo(todoId);
        return "redirect:/dashboard";
    }

    @DeleteMapping("/todos/{todoId}")
    public String delete(@PathVariable Integer todoId) {
        todoService.deleteTodo(todoId);
        return "redirect:/dashboard";
    }
}
