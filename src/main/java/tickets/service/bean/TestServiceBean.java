package tickets.service.bean;

import org.springframework.stereotype.Service;
import tickets.service.TestService;

@Service
public class TestServiceBean implements TestService{
    public String test() {
        return "test";
    }
}
