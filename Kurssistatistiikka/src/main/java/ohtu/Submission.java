package ohtu;

import java.util.Arrays;

public class Submission {
    private int week;
    private String course;
    private int hours;
    private int[] exercises;

    public void setWeek(int week, int hours, String course, int[] exercises) {
        this.week = week;
        this.hours = hours;
        this.course = course;
        this.exercises = exercises;
    }

    public int getWeek() {
        return week;
    }
    
    public int getHours() {
        return hours;
    }
    
    public String getCourseName() {
        return course;
    }
    
    public int getExerciseCount() {
        return exercises.length;
    }
    
    public int[] getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        return "Kurssi " + course + ", viikko " + week + ", aikaa kului " + hours + " tuntia " + getExerciseCount() + " tehtävään. Tehdyt tehtävät: " + Arrays.toString(exercises) ;
    }
    
}