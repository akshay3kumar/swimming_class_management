public class Booking {

    private String bookingId;
    private String learnerName;
    private Lesson lesson;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getLearnerName() {
        return learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String[] getDecodedBookin()
    {
        if(bookingId !=null && !bookingId.isEmpty()) {
            String[] components = bookingId.split("_");
            return components;
        }
        return null;


    }
}
