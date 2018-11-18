package ohtu;


public class Course {
    private String name;
    private String fullName;
    private String term;
    private int year;
    private int[] exercises;
    
    public void setCourse(String name, String fullName, String term, int year, int[] exercises) {
        this.name = name;
        this.fullName = fullName;
        this.term = term;
        this.year = year;
        this.exercises = exercises;
    }
    
    public String getName() {
        return name;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public String getTerm() {
        return term;
    }
    
    public int getYear() {
        return year;
    }
    
    public int getMaxExercisesOfWeek(int week) {
        return exercises[week];
    }

}
