package backend.exam_300522.config;

import backend.exam_300522.dto.RiderRequest;
import backend.exam_300522.dto.TeamRequest;
import backend.exam_300522.entity.Rider;
import backend.exam_300522.entity.Team;
import backend.exam_300522.repository.RiderRepository;
import backend.exam_300522.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    RiderRepository riderRepository;

    public void makeTestData() {


        Team cofidis = new Team(new TeamRequest("Cofidis"));
        Team lottoSoudal = new Team(new TeamRequest("Lotto Soudal"));
        Team movistarTeam = new Team(new TeamRequest("Movistar Team"));

        teamRepository.save(cofidis);
        teamRepository.save(lottoSoudal);
        teamRepository.save(movistarTeam);

        Rider c1 = new Rider(new RiderRequest("Piet", "Allegart", "1995-01-20", "Germany", cofidis));
        Rider c2 = new Rider(new RiderRequest("Sander", "Armee", "1985-12-10", "Germany", cofidis));
        Rider c3 = new Rider(new RiderRequest("Francois", "Bidard", "1992-03-19", "France", cofidis));
        Rider c4 = new Rider(new RiderRequest("Tom", "Bohli", "1994-01-17", "Switzerland", cofidis));
        Rider c5 = new Rider(new RiderRequest("Andre", "Carvalho", "1997-10-31", "Portugal", cofidis));
        Rider c6 = new Rider(new RiderRequest("Jonas", "Munk", "1986-12-11", "Denmark", cofidis));

        riderRepository.save(c1);
        riderRepository.save(c2);
        riderRepository.save(c3);
        riderRepository.save(c4);
        riderRepository.save(c5);
        riderRepository.save(c6);

        Rider l1 = new Rider(new RiderRequest("Pirre-Luc", "Perichon", "1993-03-10", "France", lottoSoudal));
        Rider l2 = new Rider(new RiderRequest("Guillaume", "Martin", "1989-06-16", "France", lottoSoudal));
        Rider l3 = new Rider(new RiderRequest("Bryan", "Coquard", "1998-01-19", "France", lottoSoudal));
        Rider l4 = new Rider(new RiderRequest("Ion", "Izagirre", "1995-11-01", "Spain", lottoSoudal));
        Rider l5 = new Rider(new RiderRequest("Simon", "Geschke", "1996-08-21", "Germany", lottoSoudal));

        riderRepository.save(l1);
        riderRepository.save(l2);
        riderRepository.save(l3);
        riderRepository.save(l4);
        riderRepository.save(l5);


        Rider m1 = new Rider(new RiderRequest("Nelson", "Oliveira", "1994-11-25", "Portugal", movistarTeam));
        Rider m2 = new Rider(new RiderRequest("Carlos", "Verona", "1986-04-17", "Spain", movistarTeam));
        Rider m3 = new Rider(new RiderRequest("Imanol", "Erviti", "1994-03-13", "Spain", movistarTeam));
        Rider m4 = new Rider(new RiderRequest("Gorka", "Izagirre", "1993-07-16", "Spain", movistarTeam));
        Rider m5 = new Rider(new RiderRequest("Matteo", "Joergenson", "1997-02-03", "USA", movistarTeam));

        riderRepository.save(m1);
        riderRepository.save(m2);
        riderRepository.save(m3);
        riderRepository.save(m4);
        riderRepository.save(m5);




    }

    @Override
    public void run(ApplicationArguments args) {
//        makeTestData();
    }
}
