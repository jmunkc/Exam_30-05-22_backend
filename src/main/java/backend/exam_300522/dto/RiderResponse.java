package backend.exam_300522.dto;

import backend.exam_300522.entity.Rider;
import backend.exam_300522.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiderResponse {
    private String firstName;
    private String lastName;
    private int age;
    private String country;
    private String team;
    private double time;
    private int mountainPoints;
    private int sprintPoints;


    public RiderResponse(Rider rider){
        this.firstName = rider.getFirstName();
        this.lastName = rider.getLastName();
        this.age = rider.getAge();
        this.country = rider.getCountry();
        this.team = rider.getTeam().getTeamName();
        this.time = rider.getTime();
        this.mountainPoints = rider.getMountainPoints();
        this.sprintPoints = rider.getSprintPoints();
    }

    public static List<RiderResponse> getRidersFromEntities(List<Rider> riders){
        return riders.stream().map(RiderResponse::new).collect(Collectors.toList());
    }
}
