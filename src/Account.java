import java.util.ArrayList;

public  abstract class Account {
    private User user;
    private int rank;
    public FilmApp filmApp;
    private ArrayList<Film> films;

    public Account(User user, FilmApp filmApp, ArrayList<Film> films) {
        this.user = user;
        this.filmApp = filmApp;
        this.films = films;
    }

    public abstract boolean status();

    public void addFilm(Film film, int rating){
        films.add(film);
        rank = films.size();
        film.addAccount(this);
        film.calculateRating(rating);
        film.Date();
        filmApp.addFilmToArchive(film);

    }
    public Account toPremiumAccount() {
        PremiumAccount premium = new PremiumAccount(user, filmApp, this.watchedFilms(), "premium");
        filmApp.deleteAccount(this);
        filmApp.addAccount(premium);
        premium.setRank(this.rank);
        return premium;
        ///
    }
    public ArrayList<Film> watchedFilms(){
        return films;
    }

    public User getUser() {
        return user;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return user.getLogin() + "(" + rank + ")";
    }


}
