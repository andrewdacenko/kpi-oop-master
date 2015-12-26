public class Score {
    String discipline;
    Integer mark;

    Score(Object[] obj) {
        discipline = (String) obj[0];
        mark = Integer.parseInt((String) obj[1]);
    }

    @Override
    public String toString() {
        return discipline + ": " + mark.toString();
    }
}
