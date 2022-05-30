package backend.exam_300522.repository;

import backend.exam_300522.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    Boolean existsByTeamName(String teamName);
}
