import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwimmingSystem {
	private List<Lesson> lessons;
	private List<Coach> coaches;
	private List<Learner> learners;

	public SwimmingSystem() {
		this.lessons = new ArrayList<>();
		this.coaches = new ArrayList<>();
		this.learners = new ArrayList<>();
	}

	public void addLesson(Lesson lesson) {
		lessons.add(lesson);
	}
	public void addCoach(Coach coach) {
		coaches.add(coach);
	}
	public void addLearner(Learner learner) {
		learners.add(learner);
	}

	public void registerNewLearner() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter learner's name:");
		String name = scanner.nextLine();

		System.out.println("Enter learner's gender:");
		String gender = scanner.nextLine();

		System.out.println("Enter learner's age:");
		int age = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		if(age<4 && age>11)
		{
			System.out.println("Age should be between 4 - 11 ");
			return;
		}

		System.out.println("Enter learner's emergency contact:");
		String emergencyContact = scanner.nextLine();

		System.out.println("Enter learner's current grade level between 0 to 5:");
		int currentGrade = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		if(currentGrade<0 && currentGrade>5)
		{
			System.out.println("grade level should be between 0 - 5 ");
			return;
		}
		Learner learner = new Learner(name, gender, age, emergencyContact, currentGrade);
		addLearner(learner);

		System.out.println("New learner registered successfully!");
		// scanner.close();
	}







}
