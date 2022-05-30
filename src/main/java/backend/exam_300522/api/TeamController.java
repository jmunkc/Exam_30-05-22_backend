package backend.exam_300522.api;

import backend.exam_300522.dto.TeamRequest;
import backend.exam_300522.dto.TeamResponse;
import backend.exam_300522.service.TeamService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public TeamResponse getTeam(@PathVariable String id){ return teamService.getTeam(id); }

    @PostMapping
    public TeamResponse addTeam(@RequestBody TeamRequest body){ return teamService.addTeam(body);}

    @PutMapping("/{id}")
    public TeamResponse editTeam(@RequestBody TeamRequest body, @PathVariable String id){ return teamService.editTeam(body, id); }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable String id){ teamService.deleteTeam(id);}
}
