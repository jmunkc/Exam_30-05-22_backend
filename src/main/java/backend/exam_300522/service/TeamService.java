package backend.exam_300522.service;

import backend.exam_300522.dto.TeamRequest;
import backend.exam_300522.dto.TeamResponse;
import backend.exam_300522.entity.Rider;
import backend.exam_300522.entity.Team;
import backend.exam_300522.error.Client4xxException;
import backend.exam_300522.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeamService {

    TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }


    public List<TeamResponse> getTeams(){
        List<Team> teams = teamRepository.findAll();
        return TeamResponse.getTeamsFromEntities(teams);
    }

    public TeamResponse getTeam(String id){
        Team team = teamRepository.findById(id).orElseThrow(
                () -> new Client4xxException("No team with name " + id + " found!"));

        return new TeamResponse(team);
    }


    public TeamResponse addTeam(TeamRequest body){
        Team team = teamRepository.save(new Team((body)));

        return new TeamResponse(team);
    }

    public TeamResponse editTeam(TeamRequest body, String id){
        Team team = teamRepository.findById(id).orElseThrow(() -> new Client4xxException("No team with name " + id + " found!"));
        team.setTeamName(body.getTeamName());
        team.setRiders(body.getRiders());

        return  new TeamResponse(team);
    }

    public void deleteTeam(String id){
        if (!teamRepository.existsById(id)){
            throw new Client4xxException("No team with name " + id + " found!");
        }
        else {
            teamRepository.deleteById(id);
        }
    }

    public double caclTeamTime(String id){
        Team team = teamRepository.findById(id).orElseThrow(
                () -> new Client4xxException("No team with name " + id + " found!"));

        return team.getRiders().stream().map(Rider::getTime).reduce(0.0, Double::sum);
    }
}
