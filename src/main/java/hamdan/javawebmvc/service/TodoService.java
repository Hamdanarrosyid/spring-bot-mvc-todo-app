package hamdan.javawebmvc.service;

import hamdan.javawebmvc.dto.TodoRequest;
import hamdan.javawebmvc.exception.ResourceNotFoundException;
import hamdan.javawebmvc.model.TodoModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Getter
public class TodoService {
    private final List<TodoModel> todos = new ArrayList<>();

    public TodoModel getTodoById(Integer todoId) {
        return this.todos.stream()
                .filter(todo -> Objects.equals(todo.getId(), todoId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Todo with id: " + todoId + " not found"));
    }

    public TodoModel addTodo(TodoRequest todoRequest) {
        TodoModel todoModel = TodoModel.builder()
                .id(this.todos.size() + 1)
                .title(todoRequest.getTitle())
                .description(todoRequest.getDescription())
                .dueDate(todoRequest.getDueDate())
                .completed(todoRequest.getCompleted() != null ? todoRequest.getCompleted() : false).build();
        this.todos.add(todoModel);
        return todoModel;
    }


    public TodoModel deleteTodo(Integer todoId) {
        TodoModel todoModel =  this.todos.stream()
                .filter(todo -> Objects.equals(todo.getId(), todoId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Todo with id: " + todoId + " not found"));
        this.todos.remove(todoModel);
        return todoModel;


    }


}
