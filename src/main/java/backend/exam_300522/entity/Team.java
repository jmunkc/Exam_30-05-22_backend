package backend.exam_300522.entity;

import backend.exam_300522.dto.TeamRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Team {

    @Id
    @Column(nullable = false, unique = true)
    private String teamName;


    @JsonIgnore
    @OneToMany( mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rider> riders = new ArrayList<>();


    public Team(TeamRequest body){
        this.teamName = body.getTeamName();

    }

    public void addRider(Rider rider){
        riders.add(rider);
        rider.setTeam(this);

    }
}
