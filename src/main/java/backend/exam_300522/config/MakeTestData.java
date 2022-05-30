package backend.exam_300522.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {


    public void makeTestData() {

    }

    @Override
    public void run(ApplicationArguments args) {
//        makeTestData();
    }
}
