package backend.exam_300522.dto;

import backend.exam_300522.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiderRequest {
    private String firstName;
    private String lastName;
    private String dob;
    private String country;
    private String team;
}
