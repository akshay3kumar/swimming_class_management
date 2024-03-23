
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SwimmingTest {

    @Test
    public void registerNewUserTest() {
        System.out.println("=========Test to register a new User Started===========");
        SwimmingSystem s = new SwimmingSystem();
        s.registerNewLearner();
        Assertions.assertTrue(s.getLearners().size() > 0);
        System.out.println("=========Test to register a new User Passed===========");

    }
    @Test
    public void findLearnerTest()
    {
        System.out.println("=========Test to find a learner Started===========");
        SwimmingSystem s = new SwimmingSystem();
        s.addLearner(new Learner("Hero", "M", 6, "8902229292", 1));
        Learner findLearner = s.findLearner("Hero");
        Assertions.assertNotNull(findLearner);
        System.out.println("=========Test to find a learner Passed===========");
        
    }
    

    
}
