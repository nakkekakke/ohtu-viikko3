package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }
        
        String studentUrl = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String coursesUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String ohtu2018Url = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        String rails2018Url = "https://studies.cs.helsinki.fi/courses/rails2018/stats";
        
        String studentBodyText = Request.Get(studentUrl).execute().returnContent().asString();
        String coursesBodyText = Request.Get(coursesUrl).execute().returnContent().asString();
        String ohtu2018BodyText = Request.Get(ohtu2018Url).execute().returnContent().asString();
        String rails2018BodyText = Request.Get(rails2018Url).execute().returnContent().asString();
        
//        System.out.println("json-muotoinen data opiskelijasta:");
//        System.out.println( studentBodyText );
//        System.out.println("");
//        System.out.println("json-muotoinen data kursseista:");
//        System.out.println( coursesBodyText );
//        System.out.println("");
        
        JsonParser parser = new JsonParser();
        JsonObject parsedDataOhtu = parser.parse(ohtu2018BodyText).getAsJsonObject();
        JsonObject parsedDataRails = parser.parse(rails2018BodyText).getAsJsonObject();
        

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(studentBodyText, Submission[].class);
        Course[] courses = mapper.fromJson(coursesBodyText, Course[].class);
        
        ArrayList<String> courseNames = new ArrayList<>();

        for (Submission submission : subs) {
            String courseName = submission.getCourseName();
            if (!courseNames.contains(courseName)) {
                courseNames.add(courseName);
            }
        }
        
        int allStudentsCountOhtu = 0;
        int allStudentExerciseCountOhtu = 0;
        int allStudentHoursOhtu = 0;

        for (Entry<String, JsonElement> entry : parsedDataOhtu.entrySet()) {
            allStudentsCountOhtu += entry.getValue().getAsJsonObject().get("students").getAsInt();
        }
        
        for (Entry<String, JsonElement> entry : parsedDataOhtu.entrySet()) {
            allStudentExerciseCountOhtu += entry.getValue().getAsJsonObject().get("exercise_total").getAsInt();
        }
        
        for (Entry<String, JsonElement> entry : parsedDataOhtu.entrySet()) {
            allStudentHoursOhtu += entry.getValue().getAsJsonObject().get("hour_total").getAsInt();
        }
        
        
        int allStudentsCountRails = 0;
        int allStudentExerciseCountRails = 0;
        int allStudentHoursRails = 0;
        
        for (Entry<String, JsonElement> entry : parsedDataRails.entrySet()) {
            allStudentsCountRails += entry.getValue().getAsJsonObject().get("students").getAsInt();
        }
        
        for (Entry<String, JsonElement> entry : parsedDataRails.entrySet()) {
            allStudentExerciseCountRails += entry.getValue().getAsJsonObject().get("exercise_total").getAsInt();
        }
        
        for (Entry<String, JsonElement> entry : parsedDataRails.entrySet()) {
            allStudentHoursRails += entry.getValue().getAsJsonObject().get("hour_total").getAsInt();
        }
        
        
        System.out.println("Opiskelijanumero " + studentNr);
        
        for (Course course : courses) {
            
            if (courseNames.contains(course.getName())) {
                
                System.out.println("");
                System.out.println(course.getFullName() + ", " + course.getTerm() + " " + course.getYear());
                System.out.println("");
                
                ArrayList<Submission> subsOfThisCourse = new ArrayList<>();
                
                for (Submission submission : subs) {
                    if (submission.getCourseName().equals(course.getName())) {
                        subsOfThisCourse.add(submission);
                    }
                }
                
                int exercisesDoneTotal = 0;
                int exercisesMaxTotal = 0;
                int hoursTotal = 0;
                
                for (Submission submission : subsOfThisCourse) {
                    
                    int week = submission.getWeek();
                    int hours = submission.getHours();
                    int exercisesDone = submission.getExerciseCount();
                    int exercisesMax = course.getMaxExercisesOfWeek(week);
                    int[] exerciseList = submission.getExercises();
                    
                    System.out.println("Viikko " + week);
                    System.out.print("  ");
                    System.out.println("Tehtyjä tehtäviä " + exercisesDone + "/" + exercisesMax + " aikaa kului " + hours + " tuntia. Tehdyt tehtävät: " + Arrays.toString(exerciseList));
                    
                    exercisesDoneTotal += exercisesDone;
                    exercisesMaxTotal += exercisesMax;
                    hoursTotal += hours;
                }
                
                System.out.println("");
                System.out.println("Yhteensä: " + exercisesDoneTotal + "/" + exercisesMaxTotal + " tehtävää, " + hoursTotal + " tuntia");
                System.out.println("");
               
                if (course.getName().equals("rails2018")) {
                    System.out.println("Kurssilla yhteensä " + allStudentsCountRails + " palautusta, palautettuja tehtäviä " + allStudentExerciseCountRails + " kpl, aikaa käytetty yhteensä " + allStudentHoursRails + " tuntia");
                } else if (course.getName().equals("ohtu2018")) {
                    System.out.println("Kurssilla yhteensä " + allStudentsCountOhtu + " palautusta, palautettuja tehtäviä " + allStudentExerciseCountOhtu + " kpl, aikaa käytetty yhteensä " + allStudentHoursOhtu + " tuntia");
                }
                System.out.println("");
                
            }
        }
        
    }
}
