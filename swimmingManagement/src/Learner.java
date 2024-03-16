import java.util.ArrayList;
import java.util.List;

public class Learner {
	private String name;
	private String gender;
	private int age;
	private String emergencyContact;
	private int currentGrade;
	private List<Lesson> bookedLessons;
	private List<Lesson> attendedLessons;
	private List<Lesson> canceledLessons;

	public Learner(String name, String gender, int age, String emergencyContact, int currentGrade) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.emergencyContact = emergencyContact;
		this.currentGrade = currentGrade;
		this.bookedLessons = new ArrayList<>();
		this.attendedLessons = new ArrayList<>();
		this.canceledLessons = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public int getCurrentGrade() {
		return currentGrade;
	}

	public void setCurrentGrade(int currentGrade) {
		this.currentGrade = currentGrade;
	}

	public List<Lesson> getBookedLessons() {
		return bookedLessons;
	}

	public void setBookedLessons(List<Lesson> bookedLessons) {
		this.bookedLessons = bookedLessons;
	}

	public List<Lesson> getAttendedLessons() {
		return attendedLessons;
	}

	public void setAttendedLessons(List<Lesson> attendedLessons) {
		this.attendedLessons = attendedLessons;
	}

	public List<Lesson> getCanceledLessons() {
		return canceledLessons;
	}

	public void setCanceledLessons(List<Lesson> canceledLessons) {
		this.canceledLessons = canceledLessons;
	}


	public void writeReviewAndRating(Lesson lesson, String review, int rating) {
		// Write review and rating for a lesson
	}

	

}
