package backend.database;

import java.util.ArrayList;

public class Schedule {
    private ArrayList<ClassEntry> schedule; // using an array list so we can hold class and time and easily add/drop

    public Schedule() {
        // Initialize the schedule as an ArrayList
        schedule = new ArrayList<>();
    }

    public class ClassEntry {
        private String className;
        private String classTime;

        public ClassEntry(String className, String classTime) {
            // Constructor for ClassEntry, initializes class name and time
            this.className = className;
            this.classTime = classTime;
        }

        public String getClassName() {
            // Getter for class name
            return className;
        }

        public void setClassName(String className) {
            // Setter for class name
            this.className = className;
        }

        public String getClassTime() {
            // Getter for class time
            return classTime;
        }

        public void setClassTime(String classTime) {
            // Setter for class time
            this.classTime = classTime;
        }

        @Override
        public String toString() {
            // Override toString method to provide a custom string representation of ClassEntry
            return className + " at " + classTime;
        }
    }

    public void addClass(String className, String classTime) {
        // Add a new class to the schedule
        ClassEntry entry = new ClassEntry(className, classTime);
        schedule.add(entry);
    }

    public void removeClass(String className) {
        // Remove a class from the schedule by class name
        schedule.removeIf(entry -> entry.getClassName().equals(className));
    }

    public void displaySchedule() {
        // Display the class schedule by iterating through the entries
        for (ClassEntry entry : schedule) {
            System.out.println(entry);
        }
    }

    public static void main(String[] args) { //example code of how to add and remove
        Schedule mySchedule = new Schedule();

        mySchedule.addClass("MATH212", "9:00 AM");
        mySchedule.addClass("CS272", "10:30 AM");
        mySchedule.addClass("CS410", "1:00 PM");

        mySchedule.displaySchedule();

        mySchedule.removeClass("CS272");

        System.out.println("After removing Math class:");
        mySchedule.displaySchedule();
    }
}


