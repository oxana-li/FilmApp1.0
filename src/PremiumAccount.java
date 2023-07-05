import java.util.ArrayList;

public class PremiumAccount extends Account {
    private String type;

    public PremiumAccount(User user, FilmApp filmApp, ArrayList<Film> films, String type) {
        super(user, filmApp, films);
        this.type = type;
    }


    @Override
    public boolean status() {
        return this.getRank() >= this.filmApp.getMinRankToPremium();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + type;
    }
}
