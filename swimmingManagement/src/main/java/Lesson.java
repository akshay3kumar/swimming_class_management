import java.util.ArrayList;
import java.util.List;

public class Lesson implements Cloneable {
    private String day;
    private int week;
    private String time;
    private int gradeLevel;
    private String coach;
    private int capacity;
    private List<Learner> learners;
    private int rating;

    public Lesson(String day, String time, int gradeLevel, String coach, int capacity) {
        this.day = day;
        this.time = time;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.capacity = capacity;
        this.learners = new ArrayList<>();
    }

    public String getDay() {
        return day;
    }

    public Lesson(String day, String time, int gradeLevel, String coach) {
        this.day = day;
        this.time = time;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.learners = new ArrayList<>();
    }


    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Learner> getLearners() {

        return learners;
    }

    public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setLearners(List<Learner> learners) {
        this.learners = learners;
    }

    public boolean addLearner(Learner learner) {
        if (learners.size() < 4) {
            learners.add(learner);
            return true;
        }
        return false;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public boolean removeLearner(Learner learner) {
        return learners.remove(learner);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "day='" + day + '\'' +
                ", week=" + week +
                ", time='" + time + '\'' +
                ", gradeLevel=" + gradeLevel +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
