import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MainProgramm {
    public static void main(String[] args) {
        FilmApp filmiRakendus = new FilmApp();
        Film film1 = new Film("Harry Potter and the Sorcerer's Stone");
        Film film2 = new Film("Harry Potter and the Chamber of Secrets");
        Film film3 = new Film("Harry Potter and the Prisoner of Azkaban");

        ArrayList<Film> allFilms = new ArrayList<>();
        Film[] films = {film1, film2, film3};
        Film film4 = new Film("Harry Potter and the Goblet of Fire");
        Film film5 = new Film("Harry Potter and the Order of Phoenix");
        Film film6 = new Film("Harry Potter and the Half-Blood Prince");
        Film film7 = new Film("Harry Potter and the Deathly Hallows - Part 1");
        Film film8 = new Film("Harry Potter and the Deathly Hallows - Part 2");
        Film[] films2 = {film4, film5, film6, film7, film8};

        filmiRakendus.updateAllFilms(films);
        filmiRakendus.allAccounts();
        filmiRakendus.updateAllFilms(films2);

        filmiRakendus.setMinRankToPremium(3);


        ////DATABASE
        // already existed accounts in database:
        User oksana = new User("Oksana", "L", "oksana", "1234");
        User darja = new User("Darja", "R", "darja", "1234");

        ArrayList<Film> moviesOksana = new ArrayList<>();
        ArrayList<Film> moviesDarja = new ArrayList<>();

        Account oksanaa = new Account(oksana, filmiRakendus, moviesOksana) {
            @Override
            public boolean status() {
                return false;
            }
        };
        Account darjaa = new Account(darja, filmiRakendus, moviesDarja) {
            @Override
            public boolean status() {
                return false;
            }
        };


        filmiRakendus.addAccount(oksanaa);
        filmiRakendus.addAccount(darjaa);

        oksanaa.addFilm(film1, 5);
        oksanaa.addFilm(film3, 1);
        darjaa.addFilm(film2, 2);
        darjaa.addFilm(film3, 10);
        oksanaa.addFilm(film2, 3);
        //////////////


        while (true){
            System.out.println("---------Welcome to the Film App!---------");
            System.out.println("Have You used this app before?");
            System.out.println("If new user - 1");
            System.out.println("If account already exists - 2");
            System.out.println("EXIT - 3");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            if (Objects.equals(choice, "1")){
                while (true) {
                    System.out.println("---Registration---");
                    System.out.println();
                    System.out.println("Firstname:");
                    String firstName = sc.nextLine();
                    System.out.println("Lastname:");
                    String lastName = sc.nextLine();
                    System.out.println("Login:");
                    String login = sc.nextLine();
                    System.out.println("Password:");
                    String password = sc.nextLine();

                    if (!Objects.equals(firstName, "") || !Objects.equals(lastName, "") || !Objects.equals(login, "") || !Objects.equals(password, "")) {
                        for (Account account: filmiRakendus.allAccounts()) {
                            User user = account.getUser();
                            if ((Objects.equals(user.getLogin(), login))) {
                                System.out.println("This login is already being used!");
                                break;
                            }

                        User newUser = new User(firstName, lastName, login, password);
                        ArrayList<Film> moviesUser = new ArrayList<>();

                        Account newAccount = new Account(newUser, filmiRakendus, moviesUser) {
                            @Override
                            public boolean status() {
                                return false;
                            }
                        };
                        filmiRakendus.addAccount(newAccount);
                        System.out.println("Thanks for signing up!");
                        break;
                        }
                    }
                    else System.out.println("All fields must be filled!");
                    break;
                }
            }
            else if (Objects.equals(choice, "2")) {
                System.out.println("---Log in---");
                System.out.println("Login:");
                String login = sc.nextLine();
                System.out.println("Password:");
                String password = sc.nextLine();

                for (Account account: filmiRakendus.allAccounts()) {
                    User user = account.getUser();
                    if ((Objects.equals(user.getLogin(), login) && Objects.equals(user.getPin(), password))) {
                        if (account.watchedFilms().size() >= filmiRakendus.getMinRankToPremium()) account = account.toPremiumAccount();

                        System.out.println("You have successfully logged in");
                        if (account.status()) System.out.println("~~~~~~~~PREMIUM ACCOUNT~~~~~~~~");
                        else System.out.println("~~~~~~~~ACCOUNT~~~~~~~~");
                        System.out.println("Hello, " + user.getLogin() + "!");

                        System.out.println("Your account rank is " + account.getRank());
                        System.out.println("List of watched films:");
                        for (Film film : account.watchedFilms()) {
                            System.out.println("- " + film);
                        }
                        while (true) {
                            System.out.println("~~~~~~~~~MENU~~~~~~~~~");
                            System.out.println("Add new watched film - 1");
                            System.out.println("Your account - 2");
                            System.out.println("Films in app - 3");
                            System.out.println("TOP-10 - 4");
                            System.out.println("Log out - 5");
                            String choicee = sc.nextLine();
                            if (Objects.equals(choicee, "1")) {
                                while (true) {
                                    System.out.println("~~~~~~~~ADD YOUR FILM~~~~~~~~");
                                    filmiRakendus.showAllFilmsToUser();
                                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                    System.out.println("Film:");
                                    String film;
                                    Film founded;
                                    try {
                                        film = sc.nextLine();
                                        founded = filmiRakendus.allFilms().stream().filter(movie -> film.equals(movie.getFilmName())).findAny().orElse(null);
                                        System.out.println("Your score (0-10):");
                                        int score = Integer.parseInt(sc.nextLine());
                                        if (score <= 10 && score >= 0) {
                                            assert founded != null;
                                            if (!account.watchedFilms().contains(founded)) {
                                                account.addFilm(founded, score);
                                                System.out.println(
                                                );
                                                if (account.watchedFilms().indexOf(founded)+1 == filmiRakendus.getMinRankToPremium()) account = account.toPremiumAccount();

                                                break;
                                            } else System.out.println("Film is already watched");
                                        } else System.out.println("Score must be 0-10!");
                                    } catch (Exception e) {
                                        System.out.println("Film not found!");
                                    }

                                }
                            }else if (Objects.equals(choicee, "2")){
                                if (account.status()) System.out.println("~~~~~~~~PREMIUM ACCOUNT~~~~~~~~");
                                else System.out.println("~~~~~~~~ACCOUNT~~~~~~~~");
                                System.out.println("Hello, " + user.getLogin() + "!");

                                System.out.println("Your account rank is " + account.getRank());
                                System.out.println("List of watched films:");
                                for (Film film : account.watchedFilms()) {
                                    System.out.println("- " + film);
                                };
                            }
                            else if (Objects.equals(choicee, "3")){
                                filmiRakendus.showAllFilmsToUser();

                            }
                            else if (Objects.equals(choicee, "4")){

                                if (account.status()){
                                    System.out.println("~~~~~~~~TOP-10 FILMS~~~~~~~~");
                                    for (Film f:filmiRakendus.topFilms()) {
                                        System.out.println("- " + f);
                                    }
                                    System.out.println("- THE BEST FILM" +
                                            " IS '" + filmiRakendus.topFilms().get(0).getFilmName() + "' with " +
                                            "rating - " + filmiRakendus.topFilms().get(0).getRating());
                                }


                                else System.out.println("Available only for Premium Account! To become a " +
                                        "Premium you need to watch " + (filmiRakendus.getMinRankToPremium() - account.getRank()) + " more movies");

                            }
                            else break;
                        }
                        break;
                    } else System.out.println("Incorrect login or password!");
                }
            }
            else if (Objects.equals(choice, "3")) break;

        }
    }
}
