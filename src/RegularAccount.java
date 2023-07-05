import java.util.ArrayList;

public class RegularAccount extends Account {


    public RegularAccount(User user, FilmApp filmApp, ArrayList<Film> films) {
        super(user, filmApp, films);
    }

    @Override
    public boolean status() {
        return false;
    }
}
