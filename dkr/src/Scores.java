import java.util.ArrayList;

public class Scores extends ArrayList<Score> {

    public Double getAverage() {
        Double avg = 0.0d;

        for (int i = 0; i < size(); i++) {
            Score score = get(i);
            avg += score.mark;
        }

        return avg / this.size();
    }

    @Override
    public String toString() {
        String msg = "";

        for (int i = 0; i < size(); i++) {
            Score score = get(i);
            msg += score + ", ";
        }

        return msg + "avg: " + String.format("%.2f", getAverage());
    }
}
