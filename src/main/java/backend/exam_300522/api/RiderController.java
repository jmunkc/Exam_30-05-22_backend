package backend.exam_300522.api;

import backend.exam_300522.dto.RiderRequest;
import backend.exam_300522.dto.RiderResponse;
import backend.exam_300522.service.RiderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/riders")
public class RiderController {

    RiderService riderService;

    public RiderController(RiderService riderService){
        this.riderService = riderService;
    }

    @GetMapping
    public List<RiderResponse> getRiders(@RequestParam(value = "team", required = false) String team) {
        return riderService.getRiders(team);
    }

    @GetMapping("/pages")
    public Page<RiderResponse> getRidersPage(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy){
        return riderService.getPageRiders(PageRequest.of(page.orElse(0),5, Sort.Direction.ASC, sortBy.orElse("time")));
    }

    @GetMapping("/{id}")
    public RiderResponse getRider(@PathVariable int id) { return riderService.getRider(id); }

    @PostMapping
    public RiderResponse addRider(@RequestBody RiderRequest body) {
        return riderService.addRider(body);
    }

    @PutMapping("/{id}")
    public RiderResponse editRider(@RequestBody RiderRequest body, @PathVariable int id){
        return riderService.editRider(body, id);
    }

    @PatchMapping("/{id}/{newtime}")
    public void updateTime(@PathVariable int id, @PathVariable double newtime){
        riderService.editRiderTime(newtime, id);
    }

    @PatchMapping("/{id}/{newmountain}")
    public void updateMountain(@PathVariable int id, @PathVariable int newmountain){
        riderService.editRiderMountainPoints(newmountain, id);
    }

    @PatchMapping("/{id}/{newsprint}")
    public void updateSprint(@PathVariable int id, @PathVariable int newsprint){
        riderService.editRiderSprintPoints(newsprint, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRider(@PathVariable int id){
        riderService.deleteRider(id);
    }

}
