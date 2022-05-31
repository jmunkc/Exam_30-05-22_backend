package backend.exam_300522.entity;

import backend.exam_300522.dto.RiderRequest;
import backend.exam_300522.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    private int age;

    private String country;

    private double time;

    private int mountainPoints;

    private int sprintPoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "teamName")
    private Team team;

    public Rider(String firstName, String lastName, LocalDate dob, String country, Team team){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = Period.between(dob, LocalDate.now()).getYears();
        this.country = country;
        this.team =team;
    }

    public Rider(RiderRequest body){
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.dob = LocalDate.parse(body.getDob());
        this.age = Period.between(LocalDate.parse(body.getDob()), LocalDate.now()).getYears();
        this.country = body.getCountry();
//        this.team = body.getTeam();

    }
}
