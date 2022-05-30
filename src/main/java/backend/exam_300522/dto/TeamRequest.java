package backend.exam_300522.dto;

import backend.exam_300522.entity.Rider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequest {

    private String teamName;

    private List<Rider> riders;

    public TeamRequest(String teamName) {
        this.teamName = teamName;
    }
}
