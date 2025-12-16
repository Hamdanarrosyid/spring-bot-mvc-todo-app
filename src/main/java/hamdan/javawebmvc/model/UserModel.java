package hamdan.javawebmvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    private String email;
    private String password;
    private String profilePicture;
    private String firstName;
    private String lastName;
}
