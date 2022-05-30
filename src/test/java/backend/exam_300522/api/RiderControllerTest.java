package backend.exam_300522.api;

import backend.exam_300522.dto.TeamRequest;
import backend.exam_300522.entity.Rider;
import backend.exam_300522.entity.Team;
import backend.exam_300522.repository.RiderRepository;
import backend.exam_300522.repository.TeamRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class RiderControllerTest {

    @Autowired
    RiderRepository riderRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    MockMvc mockMvc;

    private static Rider rider1;
    private static Rider rider2;
    private static Team cofidis;

    @BeforeAll
    public static void setUp(@Autowired RiderRepository riderRepository, @Autowired
            TeamRepository teamRepository) {
        riderRepository.deleteAll();
        teamRepository.deleteAll();

        cofidis = new Team(new TeamRequest("Cofidis"));
        teamRepository.save(cofidis);

        rider1 = new Rider("Piet", "Allegart", LocalDate.of(1995, 01, 20), "Germany", cofidis);
        rider2 = new Rider("Sander", "Armee", LocalDate.of(1985, 12, 10), "Germany", cofidis);

        riderRepository.save(rider1);
        riderRepository.save(rider2);

    }

    @Test
    void getRidersTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/riders")
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
            .andExpect(MockMvcResultMatchers.content().string(containsString("Piet")))
            .andExpect(MockMvcResultMatchers.content().string(containsString("Sander")));

    }

    @Test
    void getRiderTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/riders/" + rider1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(rider1.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(rider1.getAge()));
    }

//    @Test
//    void addRider() {
//    }
//
//    @Test
//    void editRider() {
//    }
//
//    @Test
//    void updateTime() {
//    }
//
//    @Test
//    void updateMountain() {
//    }
//
//    @Test
//    void updateSprint() {
//    }
//
//    @Test
//    void deleteRider() {
//    }
}