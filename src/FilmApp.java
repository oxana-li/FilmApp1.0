import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilmApp {
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Film> topFilms = new ArrayList<>();
    private ArrayList<Film> allFilms = new ArrayList<>();
    private ArrayList<Film> archive = new ArrayList<>();
    private FilmApp filmApp;
    private int minRankToPremium;

    public int getMinRankToPremium() {
        return minRankToPremium;
    }

    public void setMinRankToPremium(int minRankToPremium) {
        this.minRankToPremium = minRankToPremium;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    public ArrayList<Account> allAccounts(){
        return accounts;
    }

    public void deleteAccount(Account account){
        accounts.remove(account);
    }

    public void addFilmToArchive(Film film){
        archive.add(film);
    }

    public ArrayList<Film> showArchive(){
        return archive;
    }
    
    public ArrayList<Film> allFilms(){
        return allFilms;
    }

    public void updateAllFilms(Film[] newFilmsToAdd){
        allFilms.addAll(List.of(newFilmsToAdd));
    }

    public void showAllFilmsToUser(){ //for users
        for (Film film:allFilms) {
            System.out.println("- " + film.getFilmName() + " (" + film.getRating() + ")");
        }

    }

    public ArrayList<Film> topFilms(){
        Collections.sort(allFilms);
        if (allFilms.size() > 10){
            ArrayList<Film> top = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                top.add(allFilms.get(i));
            }
            return top;
        }
        return allFilms;

    }



}
