import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Timetable {


    HashMap<String,List<Lesson>> timeTable ;

    public Timetable()
    {
        List<Lesson> lessons = readTimeTableFromFile();
        String week = "week";
        timeTable= new HashMap<>();
        int index=0;
        for (int i=0;i<4;i++)
        {
            String key = week+(i+1);
            this.timeTable.put(key,lessons.subList(index,index+11));
            index=index+11;

        }
    }

    private List<Lesson> readTimeTableFromFile() {
        List<Lesson> lessons = new ArrayList<>();
        String filename = ".\\Sample_Swimming-main\\new_folder\\src\\lessons.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int grade;
            // Read each line from the file until the end
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                String[] l = line.split(",");
                grade =Integer.parseInt(l[2]);
                Lesson les = new Lesson(l[0],l[1],grade,"Helen");
                lessons.add(les);
            }

            // Close the reader
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return lessons;
    }

    public HashMap<String, List<Lesson>> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(HashMap<String, List<Lesson>> timeTable) {
        this.timeTable = timeTable;
    }
}