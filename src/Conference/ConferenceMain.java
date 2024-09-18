package Conference;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConferenceMain {
    private static final List<Speaker> totalSpeakers=getAllSpeaker();
    public static void main(String[] args){
        System.out.println("SAURAV SAXENA");

        LocalTime time = LocalTime.of(9,0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        Session mprningSession = new Session(180);
        //Build Morning conference session
        time=BuildConferenceSessionForSpeaker(mprningSession,time,formatter);

        if(!time.equals(LocalTime.of(12,0))){
           time= LocalTime.of(12,0);
        }
        System.out.println(time.format(formatter)+" Lunch");
        Session afternoonSession = new Session(240);

        //Afternoon session
        //Start from 1 pm
        time = LocalTime.of(13,0);

        time = BuildConferenceSessionForSpeaker(afternoonSession,time,formatter);
        if(time.isBefore(LocalTime.of(16,0))){
            time = LocalTime.of(16,0);
        }
        System.out.println(time.format(formatter)+" Networking Event");


    }

    private static LocalTime BuildConferenceSessionForSpeaker(Session session, LocalTime time, DateTimeFormatter formatter) {

        int sumOfTime = 0;

        for(Speaker speaker:totalSpeakers){
            if((sumOfTime+speaker.minute)<=session.getDuration() && (sumOfTime+speaker.minute)%5==0){
                sumOfTime=sumOfTime+speaker.minute;
                System.out.println(time.format(formatter)+" "+speaker.title + " minutes: "+speaker.minute);
                session.getSpeakers().add(speaker);
                //when you are initalize list with new ArrayList<>() it may thorow this exception
                //it's not thread safe
//                Exception in thread "main" java.util.ConcurrentModificationException
//                at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1096)
//                at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1050)
//                at Conference.ConferenceMain.BuildConferenceSessionForSpeaker(ConferenceMain.java:38)
//                at Conference.ConferenceMain.main(ConferenceMain.java:17)

                totalSpeakers.remove(speaker);
                time=time.plusMinutes(speaker.minute);
            }
        }

        return time;
    }

    private static List<Speaker> getAllSpeaker(){
        //List<Speaker> speakers = new ArrayList<>(); not threadsafe may throw
//        Exception in thread "main" java.util.ConcurrentModificationException
//        at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1096)
//        at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1050)
//        at Conference.ConferenceMain.BuildConferenceSessionForSpeaker(ConferenceMain.java:38)
//        at Conference.ConferenceMain.main(ConferenceMain.java:17)

        List<Speaker> speakers =  new CopyOnWriteArrayList<>();
        speakers.add(new Speaker("Writing Fast Tests Against Enterprise Rails", 60));
        speakers.add(new Speaker("Overdoing it in Python", 45));
        speakers.add(new Speaker("Lua for the Masses", 30));
        speakers.add(new Speaker("Ruby Errors from Mismatched Gem Versions", 45));
        speakers.add(new Speaker("Common Ruby Errors", 45));
        speakers.add(new Speaker("Rails for Python Developers", 5)); // Lightning
        speakers.add(new Speaker("Communicating Over Distance", 60));
        speakers.add(new Speaker("Accounting-Driven Development", 45));
        speakers.add(new Speaker("Woah", 30));
        speakers.add(new Speaker("Sit Down and Write", 30));
        speakers.add(new Speaker("Pair Programming vs Noise", 45));
        speakers.add(new Speaker("Rails Magic", 60));
        speakers.add(new Speaker("Ruby on Rails: Why We Should Move On", 60));
        speakers.add(new Speaker("Clojure Ate Scala (on my project)", 45));
        speakers.add(new Speaker("Programming in the Boondocks of Seattle", 30));
        speakers.add(new Speaker("Ruby vs. Clojure for Back-End Development", 30));
        speakers.add(new Speaker("Ruby on Rails Legacy App Maintenance", 60));
        speakers.add(new Speaker("A World Without HackerNews", 30));
        speakers.add(new Speaker("User Interface CSS in Rails Apps", 30));
        return speakers;
    }
}
