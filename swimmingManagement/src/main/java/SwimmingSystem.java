import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SwimmingSystem {
	private List<Lesson> lessons;
	private List<Coach> coaches;
	private List<Learner> learners;

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public List<Learner> getLearners() {
        return learners;
    }

    public void setLearners(List<Learner> learners) {
        this.learners = learners;
    }

    public SwimmingSystem() {
        this.lessons = new ArrayList<>();
        addDefaultlessons();
        this.coaches = new ArrayList<>();
        defaultCoaches();
        this.learners = new ArrayList<>();
        defaultLearners();
    }

    private void defaultCoaches() {
        this.coaches.add(new Coach("Helen"));
        this.coaches.add(new Coach("Doe"));
        this.coaches.add(new Coach("John"));

    }

	private void addDefaultlessons() {

		String filename = ".\\src\\main\\resources\\lessons.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			int grade;
			int week;
			// Read each line from the file until the end
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
				String[] l = line.split(",");
				week = Integer.parseInt(l[0]);
				grade =Integer.parseInt(l[3]);
				Lesson les = new Lesson(l[1],l[2],grade,l[4].trim());
				les.setWeek(week);
				lessons.add(les);
			}

			// Close the reader
			reader.close();
		} catch (IOException e) {
			System.err.println("Error reading the file: " + e.getMessage());
		}

	}

	public void addLesson(Lesson lesson) {
		lessons.add(lesson);
	}

	public void addNewLesson() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the day of the lesson:");
		String day = scanner.nextLine();
		System.out.println("Enter the time of the lesson:");
		String time = scanner.nextLine();
		System.out.println("Enter the grade level of the lesson:");
		int gradeLevel = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.println("Enter the coach of the lesson:");
		String coach = scanner.nextLine();
		System.out.println("Enter the capacity of the lesson:");
		int capacity = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		Lesson lesson = new Lesson(day, time, gradeLevel, coach, capacity);
		addLesson(lesson);
		System.out.println("Lesson added successfully.");
		// scanner.close();
	}

	public void addCoach(Coach coach) {
		coaches.add(coach);
	}

	public void addNewCoach() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the name of the coach:");
		String name = scanner.nextLine();
		Coach coach = new Coach(name);
		addCoach(coach);
		System.out.println("Coach added successfully.");
		//// scanner.close();
	}

	public void addLearner(Learner learner) {
		learners.add(learner);
	}

	public void displayTimetableByGradeLevel(int gradeLevel) {
		System.out.println("Available Lessons for Grade Level " + gradeLevel + ":");
		for (Lesson lesson : lessons) {
			if (lesson.getGradeLevel() == gradeLevel) {
				System.out.println("Day: " + lesson.getDay() + ", Time: " + lesson.getTime() + ", Coach: "
						+ lesson.getCoach() + ", Capacity: " + lesson.getCapacity());
			}
		}
	}

	public void bookLesson(String learnerName, int gradeLevel) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Available Lessons for Grade Level " + gradeLevel + ":");
		displayTimetableByGradeLevel(gradeLevel);
		System.out.println("Enter the time (e.g., 4-5pm) of the lesson you want to book:");
		String lessonTime = scanner.nextLine();

		Lesson selectedLesson = findLessonByGradeLevelAndTime(gradeLevel, lessonTime);
		if (selectedLesson != null) {
			Learner learner = findLearner(learnerName);
			if (learner != null) {
				if (selectedLesson.addLearner(learner)) {
					learner.bookLesson(selectedLesson);
					System.out.println(learnerName + " has successfully booked the lesson.");
				} else {
					System.out.println("The lesson is already full. Booking failed.");
				}
			} else {
				System.out.println("Learner not found.");
			}
		} else {
			System.out.println("Lesson not found.");
		}
		// scanner.close();
	}

	private Lesson findLessonByGradeLevelAndTime(int gradeLevel, String lessonTime) {
		for (Lesson lesson : lessons) {
			if (lesson.getGradeLevel() == gradeLevel && lesson.getTime().equalsIgnoreCase(lessonTime)) {
				return lesson;
			}
		}
		return null;
	}

	public Learner findLearner(String learnerName) {
		for (Learner learner : learners) {
			if (learner.getName().equalsIgnoreCase(learnerName)) {
				return learner;
			}
		}
		return null;
	}

	public void displayTimetable(String day, String filterValue) {
		System.out.println("Timetable for " + day + " | Filter: " + filterValue);
		System.out.println("------------------------------------------------------------");
		System.out.println("Week \t Day\tTime\tGrade Level\tCoach\tVacant");
		System.out.println("------------------------------------------------------------");
		for (Lesson lesson : lessons) {
			boolean match = false;
			if (day.equalsIgnoreCase("day")) {
				match = lesson.getDay().equalsIgnoreCase(filterValue);
			} else if (day.equalsIgnoreCase("grade level")) {
				match = lesson.getGradeLevel() == Integer.parseInt(filterValue);
			} else if (day.equalsIgnoreCase("coach")) {
				match = lesson.getCoach().equalsIgnoreCase(filterValue);
			}

			if (match) {
				System.out.println(lesson.getWeek()
                                        +"\t"+ lesson.getDay() 
                                        + "\t" + lesson.getTime() 
                                        + "\t" + lesson.getGradeLevel() 
                                        + "\t" + lesson.getCoach() 
                                        + "\t" + (4-lesson.getLearners().size()));
			}
		}
		System.out.println("------------------------------------------------------------");

	}
	public void askForBooking(Scanner scanner, String learnerName) {
		Learner learner = findLearner(learnerName);
		if (learner == null) {
			System.out.println("Learner Not registered");
			return;
		}
		System.out.println("Enter Week number :");
		int sel_week = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter Day :");
		String sel_day = scanner.nextLine();
		System.out.println("Enter TimeSlot :");
		String sel_slot = scanner.nextLine();
		System.out.println("Enter Grade:");
		int sel_grade = scanner.nextInt();
		scanner.nextLine();

		if(learner.getCurrentGrade() < sel_grade-1)
		{
			System.out.println("-------------------------------------------------------");
			System.out.println("You are not Eligible for this Grade ...");
			System.out.println("-------------------------------------------------------");
		}
		Optional<Lesson> first = this.lessons.stream().filter(lesson -> (lesson.getWeek() == sel_week
						&& lesson.getDay().equals(sel_day)
						&& lesson.getTime().equals(sel_slot)
						&& lesson.getGradeLevel() == sel_grade))
				.findFirst();
		if(first.isEmpty())
		{
			System.out.println("-------------------------------------------------------");
				System.out.println("Lesson Not Found !!!!!!");
			System.out.println("-------------------------------------------------------");
		}
		else {
			if(first.get().getLearners().size()<4) {
				first.get().getLearners().add(learner);
				learner.getBookedLessons().add(first.get());
				System.out.println("-------------------------------------------------------");
				System.out.println("Booking Successfull");
				System.out.println("-------------------------------------------------------");

			}
			else {
				System.out.println("-------------------------------------------------------");
				System.out.println("Max 4 allowed !!!!!!");
				System.out.println("Booking NOT  Successfull");
				System.out.println("-------------------------------------------------------");
			}

		}
		//List<Lesson> lessons_of_week = timeTable.getTimeTable().get("week" + sel_week);
//		for (Lesson lesson : lessons_of_week) {
//			if (lesson.getDay().equals(sel_day) && lesson.getTime().equals(sel_slot)) {
//				if (lesson.getLearners().size() < 4 && lesson.getLearners().contains(learner)) {
//					learner.getBookedLessons().add(lesson);
//					lesson.getLearners().add(learner);
//					Booking booking = new Booking();
//					booking.setBookingId(sel_week + "_" + sel_day + "_" + sel_slot);
//					booking.setLesson(lesson);
//					booking.setLearnerName(learnerName);
//					bookings.add(booking);
//					System.out.println("-------------------------------------------------------");
//					System.out.println("Booking Successfull");
//					System.out.println("-------------------------------------------------------");
//				} else {
//					System.out.println("-------------------------------------------------------");
//					System.out.println("Booking Not Successfull");
//					System.out.println("Maximum Capacity Reached OR Duplicate bookings");
//					System.out.println("-------------------------------------------------------");
//				}
//			}
//		}

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

		System.out.println("Enter learner's emergency contact:");
		String emergencyContact = scanner.nextLine();

		System.out.println("Enter learner's current grade level:");
		int currentGrade = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		Learner learner = new Learner(name, gender, age, emergencyContact, currentGrade);
		addLearner(learner);
                System.out.println("-------------------------------------------------------");
		System.out.println("New learner registered successfully!");
                System.out.println("-------------------------------------------------------");
		// scanner.close();
	}

	public void attendSwimmingLesson(String learnerName) {

		Learner learner = findLearner(learnerName);
		if(learner == null)
		{
                    System.out.println("-------------------------------------------------------");
                    System.out.println(learnerName + "Not Registered");
                    System.out.println("-------------------------------------------------------");
                    return;
		}

		System.out.println( "All Booked Lesson of "+learnerName);
                 if(learner.getBookedLessons().size()<1)
                {
                    System.out.println(learnerName+" has no booked Lesson to attend");
                    return;
                }
                 System.out.println("-------------------------------------------------------");
		learner.getBookedLessons().forEach(bookedLesson -> System.out.println(bookedLesson.toString()));
		System.out.println("-------------------------------------------------------");
                Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the week of the lesson:");
		int week = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter the day of the lesson:");
		String day = scanner.nextLine();

		System.out.println("Enter the time slot of the lesson:");
		String timeSlot = scanner.nextLine();

		Lesson lesson = findLessonByDayTimeGrade(day, timeSlot, week);
		if (lesson != null) {
			if (learner != null) {
				if (lesson.getLearners().contains(learner)) {
					System.out.println("-------------------------------------------------------------------");
					System.out.println(learnerName + " attended the lesson successfully.");
                                        System.out.println("-------------------------------------------------------");
                                        System.out.println("1: Very dissatisfied \n"
                                                + " 2: Dissatisfied\n"
                                                + " 3: Ok\n"
                                                + " 4: Satisfied\n"
                                                + " 5: Very Satisfied\n");
                                        System.out.println("-------------------------------------------------------");
					System.out.println("Give the rating to Coach from (1 to 5) ");
					int rating = scanner.nextInt();
					scanner.nextLine();
					Optional<Coach> coach = this.coaches.stream()
							.filter(coach1 -> coach1.getName().equals(lesson.getCoach())).findFirst();
					if(!coach.isEmpty())
					{
						try {
							lesson.setRating(rating);
							coach.get().getLessonsTaught().add((Lesson) lesson.clone());
						} catch (CloneNotSupportedException e) {
							throw new RuntimeException(e);
						}
					}
					lesson.getLearners().remove(learner);



					learner.attendLesson(lesson);



				} else {
					System.out.println(learnerName + " is not booked for this lesson.");
				}
			} else {
				System.out.println("Learner not found.");
			}
		} else {
			System.out.println("Lesson not found.");
		}
		// scanner.close();
	}

	private Lesson findLessonByDayTimeGrade(String day, String timeSlot, int week) {
		for (Lesson lesson : lessons) {
			if (lesson.getDay().equalsIgnoreCase(day) && lesson.getTime().equalsIgnoreCase(timeSlot)
					&& lesson.getWeek() == week) {
				return lesson;
			}
		}
		return null;
	}
	private Lesson findLessonByWeekDayTimeGrade(String day, String timeSlot, int week,int grade) {
		for (Lesson lesson : lessons) {
			if (lesson.getDay().equalsIgnoreCase(day) && lesson.getTime().equalsIgnoreCase(timeSlot)
					&& lesson.getWeek() == week && lesson.getGradeLevel()== grade) {
				return lesson;
			}
		}
		return null;
	}

	public void changeOrCancelBooking(String learnerName) {


		Learner learner = findLearner(learnerName);
		if(learner == null)
		{
                    System.out.println(learnerName+ "Not Registered");
                    return;
		}
		System.out.println( "All Booked Lesson of "+learnerName);
                if(learner.getBookedLessons().size()<1)
                {
                    System.out.println(learnerName+" has no booked Lesson");
                    return;
                }
		learner.getBookedLessons().forEach(bookedLesson -> System.out.println(bookedLesson.toString()));
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the week of the lesson:");
		int week = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the day of the lesson:");
		String day = scanner.nextLine();

		System.out.println("Enter the time slot of the lesson:");
		String timeSlot = scanner.nextLine();

//		System.out.println("Enter the grade level of the lesson:");
//		int gradeLevel = scanner.nextInt();
//		scanner.nextLine(); // Consume newline

		Lesson lesson = findLessonByDayTimeGrade(day, timeSlot, week);
		if (lesson != null) {
				if (lesson.getLearners().contains(learner)) {
					System.out.println("Do you want to change or cancel the booking?");
					System.out.println("1. Change booking");
					System.out.println("2. Cancel booking");
					int choice = scanner.nextInt();
					scanner.nextLine(); // Consume newline
					switch (choice) {
					case 1:
						changeBooking(learner, lesson);
						break;
					case 2:
						cancelBooking(learner, lesson);
						break;
					default:
						System.out.println("Invalid choice.");
					}
				} else {
					System.out.println(learnerName + " is not booked for this lesson.");
				}
		} else {
			System.out.println("Lesson not found.");
		}
		// scanner.close();
	}

	private void changeBooking(Learner learner, Lesson lesson) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the new week of the lesson:");
		int newWeek = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the new day of the lesson:");
		String newDay = scanner.nextLine();

		System.out.println("Enter the new time slot of the lesson:");
		String newTimeSlot = scanner.nextLine();

		System.out.println("Enter the new grade level of the lesson:");
		int newGradeLevel = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		Lesson newLesson = findLessonByWeekDayTimeGrade(newDay, newTimeSlot,newWeek, newGradeLevel);
		if (newLesson != null) {
			if (newLesson.addLearner(learner)) {
				lesson.getLearners().remove(learner);
				learner.changeBooking(lesson, newLesson);
				System.out.println("Booking changed successfully.");
			} else {
				System.out.println("The new lesson is already full. Booking change failed.");
			}
		} else {
			System.out.println("New lesson not found.");
		}
		// scanner.close();
	}

	private void cancelBooking(Learner learner, Lesson lesson) {
		lesson.getLearners().remove(learner);
		learner.cancelBooking(lesson);
		System.out.println("Booking canceled successfully.");
	}

	public void generateMonthlyReport() {
		// Loop through all learners
		for (Learner learner : learners) {
			// Print learner's name
			System.out.println("Learner: " + learner.getName());

			// Initialize counters for learner's booked, canceled, and attended lessons
			int learnerBookedCount = 0;
			int learnerCanceledCount = 0;
			int learnerAttendedCount = 0;

			// Loop through learner's booked lessons
			System.out.println("Booked lessons:");
			for (Lesson lesson : learner.getBookedLessons()) {
				System.out.println(" - " + lesson.getDay() + " " + lesson.getTime());
				learnerBookedCount++;
			}

			// Loop through learner's canceled lessons
			System.out.println("Canceled lessons:");
			for (Lesson lesson : learner.getCanceledLessons()) {
				System.out.println(" - " + lesson.getDay() + " " + lesson.getTime());
				learnerCanceledCount++;
			}

			// Loop through learner's attended lessons
			System.out.println("Attended lessons:");
			for (Lesson lesson : learner.getAttendedLessons()) {
				System.out.println(" - " + lesson.getDay() + " " + lesson.getTime());
				learnerAttendedCount++;
				learnerBookedCount++;
			}

			// Print learner's total counts
			System.out.println("Total booked: " + learnerBookedCount);
			System.out.println("Total canceled: " + learnerCanceledCount);
			System.out.println("Total attended: " + learnerAttendedCount);
			System.out.println();
		}
	}

	public void generateMonthlyReportForCoach() {
		System.out.println("Monthly Coach Report:");
		for (Coach coach : coaches) {
			System.out.println("Coach: " + coach.getName());
			int totalRatings = 0;
			int numberOfRatings = 0;
			for (Lesson lesson : coach.getLessonsTaught()) {
				System.out.println("Lesson: " + lesson.getDay() + " " + lesson.getTime());
					int rating = lesson.getRating();
					if (rating != -1) { // -1 indicates no rating provided
						totalRatings += rating;
						numberOfRatings++;
						System.out.println(" - Rating: " + rating);
					}

			}
			if (numberOfRatings > 0) {
				double averageRating = (double) totalRatings / numberOfRatings;
				System.out.println("Average Rating: " + averageRating);
			} else {
				System.out.println("No ratings available.");
			}
			System.out.println();
		}
	}

    private void defaultLearners() {
       Learner learner = new Learner("Manish", "Male", 6, "1234567891", 2);
        addLearner(learner);
        learner = new Learner("Abdul", "Male", 7, "1234567891", 1);
        addLearner(learner);
        learner = new Learner("Suresh", "Male", 6, "1234567891", 3);
        addLearner(learner);
        learner = new Learner("Anku", "Female", 5, "1234567891", 4);
        addLearner(learner);
        learner = new Learner("Akshay", "Male", 8, "1234567891", 5);
        addLearner(learner);
    }

}
