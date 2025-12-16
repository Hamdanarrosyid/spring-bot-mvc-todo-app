package hamdan.javawebmvc.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Size(message = "Maximum description 500 characters", max = 500)
    private String description;
    @NotNull(message = "The due date cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "The due date cannot be in the past")
    private LocalDate dueDate;

    private Boolean completed;
}
