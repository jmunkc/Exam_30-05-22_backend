package backend.exam_300522.service;

import backend.exam_300522.dto.RiderRequest;
import backend.exam_300522.dto.RiderResponse;
import backend.exam_300522.dto.TeamRequest;
import backend.exam_300522.entity.Rider;
import backend.exam_300522.entity.Team;
import backend.exam_300522.error.Client4xxException;
import backend.exam_300522.repository.RiderRepository;
import backend.exam_300522.repository.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class RiderServiceTest {

    @Autowired
    RiderRepository riderRepository;

    @Autowired
    TeamRepository teamRepository;

    RiderService riderService;

    int pietId;

    @BeforeEach
    void setUp() {
        Team cofidis = new Team(new TeamRequest("Cofidis"));
        Team lottoSoudal = new Team(new TeamRequest("Lotto Soudal"));

        teamRepository.save(cofidis);
        teamRepository.save(lottoSoudal);

        Rider c1 = new Rider("Piet", "Allegart", LocalDate.of(1995, 01, 20), "Germany", cofidis);
        Rider c2 = new Rider("Sander", "Armee", LocalDate.of(1985, 12, 10), "Germany", cofidis);
        Rider l1 = new Rider(new RiderRequest("Pirre-Luc", "Perichon", "1993-03-10", "France", lottoSoudal));



        pietId = riderRepository.save(c1).getId();
        System.out.println(pietId);
        riderRepository.save(c2);
        riderRepository.save(l1);

        riderService = new RiderService(riderRepository);
    }

    @Test
    void getRidersTest() {
        List<RiderResponse> riders = riderService.getRiders(null);
        assertEquals(3, riders.size());
        assertInstanceOf(RiderResponse.class, riders.get(0));
    }

    @Test
    void getRiderTest() {

        RiderResponse riderRes = riderService.getRider(pietId);

        assertEquals("Piet", riderRes.getFirstName());

    }

    @Test
    void addRiderTest() {
        Team cofidis = new Team(new TeamRequest("Cofidis"));
        RiderRequest newRider = new RiderRequest("John", "Johnson", "1956-11-25", "Germany", cofidis);

        int newRiderId = riderService.addRider(newRider).getId();
        System.out.println(newRiderId);

        List<RiderResponse> riders = riderService.getRiders(null);

        assertEquals(4, riders.size());
        assertEquals("John", riderService.getRider(newRiderId).getFirstName());
    }

    @Test
    void editRiderTest() {
        Team newTeam = new Team(new TeamRequest("Moviestar Team"));
        RiderResponse rider = riderService.getRider(pietId);
        assertEquals(rider.getTeam(), "Cofidis");

        riderService.editRider(new RiderRequest(
                rider.getFirstName(),
                rider.getLastName(),
                "1995-01-20",
                rider.getCountry(),
                newTeam) ,pietId);

        assertEquals(riderService.getRider(pietId).getTeam(), newTeam.getTeamName());


    }

    @Test
    void deleteRiderTest() {
        assertEquals(riderService.getRider(pietId).getFirstName(), "Piet");
        assertEquals(3, riderService.getRiders(null).size());

        riderService.deleteRider(pietId);

        assertEquals(2, riderService.getRiders(null).size());
        assertThrows(Client4xxException.class, () -> riderService.getRider(pietId));


    }
}