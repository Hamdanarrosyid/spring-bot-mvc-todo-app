package hamdan.javawebmvc.dto;

import hamdan.javawebmvc.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private UserModel user;
    private String sessionId;
}
