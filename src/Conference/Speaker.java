package Conference;

public class Speaker {
    String title;
    int minute;

    public Speaker(String title, int min) {
        this.minute=min;
        this.title=title;
    }


    @Override
    public String toString() {
        return "Speaker{" +
                "Title='" + title + '\'' +
                ", minute='" + minute + '\'' +
                '}';
    }
}
