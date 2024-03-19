

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SwimmingSystem {
	private List<Lesson> lessons;
	private List<Coach> coaches;
	private List<Learner> learners;
	private List<Booking> bookings;
	private  Timetable timeTable = new Timetable();

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
		this.learners.add(learner);

		System.out.println("New learner registered successfully!");
		// scanner.close();
	}
	public void displayTimetable(String day, String filterValue) {
		System.out.println("Timetable for " + filterValue);
		System.out.println("------------------------------------------------------------");
		System.out.println("Week\tDay\t\t\t\t\t\tTime\t\tGrade\t\tvacant ");
		System.out.println("------------------------------------------------------------");
		HashMap<String, List<Lesson>> month_timetable = timeTable.getTimeTable();
		for (int i =1;i<5;i++) {
			List<Lesson> week_lessons = month_timetable.get("week" + i);
			for (Lesson day_lesson : week_lessons) {
				boolean match = false;
				if (day.equalsIgnoreCase("day") && day_lesson.getGradeLevel() !=-1) {
					match = day_lesson.getDay().equalsIgnoreCase(filterValue);
				} else if (day.equalsIgnoreCase("grade level")&& day_lesson.getGradeLevel() !=-1) {
					match = day_lesson.getGradeLevel() == Integer.parseInt(filterValue);
				} else if (day.equalsIgnoreCase("coach")&& day_lesson.getGradeLevel() !=-1) {
					match = day_lesson.getCoach().equalsIgnoreCase(filterValue);
				}
				if (match) {
					System.out.println(i+"\t"+day_lesson.getDay()+ "\t\t\t\t\t" + day_lesson.getTime() + "\t\t\t" + day_lesson.getGradeLevel() + "\t\t" + day_lesson.getLearners().size());
				}
			}
		}

		System.out.println("------------------------------------------------------------");
		System.out.println("------------------------------------------------------------");


	}
	private Learner findLearner(String learnerName) {
		for (Learner learner : learners) {
			if (learner.getName().equalsIgnoreCase(learnerName)) {
				return learner;
			}
		}
		return null;
	}

	public void askForBooking(Scanner scanner,String learnerName )
	{
		Learner learner = findLearner(learnerName);
		if(learner == null)
		{
			System.out.println("Learner Not registered");
			return;
		}
		System.out.println("Enter Week number :");
		String sel_week = scanner.nextLine();
		System.out.println("Enter Day :");
		String sel_day = scanner.nextLine();
		System.out.println("Enter TimeSlot :");
		String sel_slot = scanner.nextLine();
		List<Lesson> lessons_of_week = timeTable.getTimeTable().get("week" + sel_week);
		for(Lesson lesson:lessons_of_week)
		{
			if(lesson.getDay().equals(sel_day) && lesson.getTime().equals(sel_slot))
			{
				if(lesson.getLearners().size()<4 && lesson.getLearners().contains(learner))
				{
					learner.getBookedLessons().add(lesson);
					lesson.getLearners().add(learner);
					Booking booking = new Booking();
					booking.setBookingId(sel_week+sel_day+sel_slot);
					booking.setLesson(lesson);
					booking.setLearnerName(learnerName);
					bookings.add(booking);
					System.out.println("-------------------------------------------------------");
					System.out.println("Booking Successfull");
					System.out.println("-------------------------------------------------------");
				}
				else {
					System.out.println("-------------------------------------------------------");
					System.out.println("Booking Not Successfull");
					System.out.println("Maximum Capacity Reached OR Duplicate bookings");
					System.out.println("-------------------------------------------------------");
				}
			}
		}




	}







}
