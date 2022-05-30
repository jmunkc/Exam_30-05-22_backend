package backend.exam_300522.api;

import backend.exam_300522.dto.TeamResponse;
import backend.exam_300522.service.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/teams")
public class TeamController {
    TeamService teamService;

    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamResponse> getTeams(){ return teamService.getTeams(); }
}
