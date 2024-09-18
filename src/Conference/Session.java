package Conference;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private List<Speaker> speakers;
    private final int duration;

    public Session(int duration){
        speakers=new ArrayList<>();
        this.duration=duration;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public int getDuration() {
        return duration;
    }
}
