package backend.exam_300522.repository;

import backend.exam_300522.entity.Rider;
import backend.exam_300522.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Integer> {
    Optional<Rider> findByLastName(String lastName);


    List<Rider> findAllByTeam_TeamName(String teamName);

}
