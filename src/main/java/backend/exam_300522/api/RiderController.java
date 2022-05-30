package backend.exam_300522.api;

import backend.exam_300522.dto.RiderResponse;
import backend.exam_300522.service.RiderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public RiderResponse getRider(@PathVariable int id) { return riderService.getRider(id); }

}
