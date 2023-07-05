import java.time.LocalDateTime;
import java.util.ArrayList;

public class Film implements Comparable<Film> {
    private String filmName;
    private double rating;
    private LocalDateTime dateAdded;
    private ArrayList<Account> accountsWhoWatchedThisFilm = new ArrayList<>();
    private double allScoresSum;

    public Film(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmName() {
        return filmName;
    }

    public double getRating() {
        return rating;
    }

    public void addAccount(Account account){
        accountsWhoWatchedThisFilm.add(account);
    }

    public ArrayList<Account> filmWatchers(){
        return accountsWhoWatchedThisFilm;
    }


    public void calculateRating(int score){
        allScoresSum += score;
        rating = Math.round((allScoresSum / accountsWhoWatchedThisFilm.size()) * 100.0) / 100.0;
    }

    public void Date(){
        dateAdded = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return filmName +
                " (" + rating +
                ")";
    }

    @Override
    public int compareTo(Film o) {
        return Double.compare(o.rating, this.rating);
    }
}
