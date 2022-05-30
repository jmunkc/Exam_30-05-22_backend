package backend.exam_300522.entity;

import backend.exam_300522.dto.TeamRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Team {

    @Id
    @Column(nullable = false, unique = true)
    private String teamName;

    @JsonIgnore
    @OneToMany( mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rider> riders = new HashSet<>();

    public Team(TeamRequest body){
        this.teamName = body.getTeamName();

    }

    public void addRider(Rider rider){this.riders.add(rider);}
}
