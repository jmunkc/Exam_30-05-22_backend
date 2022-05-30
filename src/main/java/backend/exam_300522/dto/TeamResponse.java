package backend.exam_300522.dto;

import backend.exam_300522.entity.Rider;
import backend.exam_300522.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {

    private String teamName;
    private Set<Rider> riders;

    public TeamResponse(Team team){
        this.teamName = team.getTeamName();
        this.riders = team.getRiders();
    }

    public static List<TeamResponse> getTeamsFromEntities(List<Team> teams){
        return teams.stream().map(TeamResponse::new).collect(Collectors.toList());
    }

}
