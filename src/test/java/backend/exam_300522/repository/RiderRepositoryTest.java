package backend.exam_300522.repository;

import backend.exam_300522.dto.RiderRequest;
import backend.exam_300522.dto.TeamRequest;
import backend.exam_300522.entity.Rider;
import backend.exam_300522.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@Transactional
class RiderRepositoryTest {

    @Autowired
    RiderRepository riderRepository;

    @Autowired
    TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
        Team cofidis = new Team(new TeamRequest("Cofidis"));
        Team lottoSoudal = new Team(new TeamRequest("Lotto Soudal"));

        teamRepository.save(cofidis);
        teamRepository.save(lottoSoudal);

        Rider c1 = new Rider("Piet", "Allegart", LocalDate.of(1995, 01, 20), "Germany", cofidis);
        Rider c2 = new Rider("Sander", "Armee", LocalDate.of(1985, 12, 10), "Germany", cofidis);
        Rider l1 = new Rider(new RiderRequest("Pirre-Luc", "Perichon", "1993-03-10", "France", lottoSoudal));

        riderRepository.save(c1);
        riderRepository.save(c2);
        riderRepository.save(l1);


    }

    @Test
    void findAllByTeam_TeamNameTest() {
        List<Rider> riders = riderRepository.findAllByTeam_TeamName("Cofidis");

        assertEquals(2, riders.size());
        assertEquals("Cofidis", riders.get(0).getTeam().getTeamName());

    }
}