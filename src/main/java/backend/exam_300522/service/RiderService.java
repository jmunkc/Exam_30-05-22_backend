package backend.exam_300522.service;


import backend.exam_300522.dto.RiderRequest;
import backend.exam_300522.dto.RiderResponse;
import backend.exam_300522.entity.Rider;
import backend.exam_300522.error.Client4xxException;
import backend.exam_300522.repository.RiderRepository;
import backend.exam_300522.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RiderService {
    RiderRepository riderRepository;

    TeamRepository teamRepository;

    public RiderService(RiderRepository riderRepository, TeamRepository teamRepository){
        this.riderRepository = riderRepository;
        this.teamRepository = teamRepository;
    }

    public List<RiderResponse> getRiders(String teamName){
        List<Rider> riders;

        if (teamName != null){
            riders = riderRepository.findAllByTeam_TeamName(teamName);
        } else {
            riders = riderRepository.findAll();
        }
        return RiderResponse.getRidersFromEntities(riders);
    }

    public Page<RiderResponse> getPageRiders(PageRequest pageRequest){
        Page<Rider> riders = riderRepository.findAll(pageRequest);

        return new PageImpl<>(riders.stream().map(RiderResponse::new).collect(Collectors.toList()));
    }


    public RiderResponse getRider(int id){
        Rider rider = riderRepository.findById(id).orElseThrow(
                () -> new Client4xxException("No rider with id " + id + " found!"));

        return new RiderResponse(rider);
    }


    public RiderResponse addRider(RiderRequest body){
        Rider rider = riderRepository.save(new Rider(body.getFirstName(), body.getLastName(), LocalDate.parse(body.getDob()), body.getCountry(), teamRepository.findById(body.getTeam()).get()));
        return new RiderResponse(rider);
    }

    public RiderResponse editRider(RiderRequest body, int id){
        Rider rider = riderRepository.findById(id).orElseThrow(() -> new Client4xxException("No rider with name " + id + " found!"));
        rider.setFirstName(body.getFirstName());
        rider.setLastName(body.getLastName());
        rider.setDob(LocalDate.parse(body.getDob()));
        rider.setCountry(body.getCountry());
        rider.setTeam(teamRepository.findById(body.getTeam()).get());

        return  new RiderResponse(riderRepository.save(rider));
    }

    public void editRiderTime(double newTime, int id){
        Rider rider = riderRepository.findById(id).orElseThrow(() -> new Client4xxException("No rider with name " + id + " found!"));
        double currentTime = rider.getTime();
        rider.setTime(currentTime + newTime);

        riderRepository.save(rider);
    }

    public void editRiderMountainPoints(int newMountainPoints, int id){
        Rider rider = riderRepository.findById(id).orElseThrow(() -> new Client4xxException("No rider with name " + id + " found!"));
        double currentPoints = rider.getMountainPoints();
        rider.setTime(currentPoints + newMountainPoints);

        riderRepository.save(rider);
    }

    public void editRiderSprintPoints(int newSprintPoints, int id){
        Rider rider = riderRepository.findById(id).orElseThrow(() -> new Client4xxException("No rider with name " + id + " found!"));
        int currentPoints = rider.getSprintPoints();
        rider.setTime(currentPoints + newSprintPoints);

        riderRepository.save(rider);
    }

    public void deleteRider(int id){
        if (!riderRepository.existsById(id)){
            throw new Client4xxException("No rider with name " + id + " found!");
        }
        else {
            riderRepository.deleteById(id);
        }
    }
}
