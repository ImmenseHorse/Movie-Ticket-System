import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

// did not use this class
public class Payment extends Accounts {
    private static Movie movies;

    public Payment(String accountFilename, String movieFilename) {
        super(accountFilename);
        this.movies = new Movie(movieFilename);
    }

    public void addBalance(String username, double amount) {
        JSONObject account = getAccount(username);
        if (account == null) {
            System.out.println("Error: Account not found");
            return;
        }

        double balance = (double) account.get("balance");
        balance += amount;
        account.put("balance", balance);

        saveAccounts();
        System.out.println("Successfully added " + amount + " to " + username + "'s account");
    }

    public int getBalance(String username) {
        JSONObject account = getAccount(username);
        if (account == null) {
            System.out.println("Error: Account not found");
            return (Integer) null;
        }
        return (int) account.get("balance");

    }

    public int getTickets(String username, String movie) {
        JSONObject account = getAccount(username);
        if (account == null) {
            System.out.println("Error: Account not found");
            return (Integer) null;
        }
        return (int) account.get("tickets");

    }

    public void removeBalance(String username, double amount) {
        JSONObject account = getAccount(username);
        if (account == null) {
            System.out.println("Error: Account not found");
            return;
        }
        int balance = (int) account.get("balance");
        if (balance >= amount) {
            balance -= amount;
            account.put("balance", balance);

        } else {
            System.out.println("Error: Amount is More than Balance");
        }

        saveAccounts();
        System.out.println("Successfully removed " + amount + " from " + username + "'s account");
    }

    public void bookTicket(String username, String movieName, String showtimeString, int numTickets) {
        JSONObject account = getAccount(username);
        JSONObject movie = movies.getMovie(movieName);
        JSONArray showtimes = (JSONArray) movie.get("showtimes");

        // Check if user already has tickets for this movie and showtime
        boolean foundMatchingTicket = false;
        JSONArray userTickets = (JSONArray) account.get("tickets");

        for (Object userTicketObj : userTickets) {
            System.out.println("here3");
            JSONObject userTicket = (JSONObject) userTicketObj;
            if (userTicket.get("movie").equals(movieName) && userTicket.get("time").equals(showtimeString.toString())) {
                int currentNumTickets = ((Long) userTicket.get("tickets")).intValue();
                userTicket.put("tickets", currentNumTickets + numTickets);
                foundMatchingTicket = true;
                break;
            }
        }

        // If no matching ticket was found, create a new one
        if (!foundMatchingTicket) {

            JSONObject newTicket = new JSONObject();
            newTicket.put("movie", movieName);
            newTicket.put("time", showtimeString);
            newTicket.put("tickets", numTickets);
            userTickets.add(newTicket);
        }

        for (Object showtimeObj : showtimes) {

            JSONObject showtime = (JSONObject) showtimeObj;
            String time = (String) showtime.get("time");
            int ticketsRemaining = ((Long) showtime.get("tickets")).intValue();

            if (time.equals(showtimeString)) {
                if (ticketsRemaining >= numTickets) {
                    // Update movie tickets
                    showtime.put("tickets", ticketsRemaining - numTickets);
                    movies.saveMovies();
                    saveAccounts();
                    System.out.println("Successfully booked " + numTickets + " tickets for " + movieName + " at "
                            + showtimeString);
                    return;
                } else {
                    System.out
                            .println("Error: Not enough tickets available for " + movieName + " at " + showtimeString);
                    return;
                }
            }
        }
        System.out.println("Error: Showtime not found for " + movieName);
    }

    public static void removeTickets(String username, String movieName, String showtimeString, int numTickets) {
        JSONObject account = getAccount(username);
        JSONObject movie = movies.getMovie(movieName);
        JSONArray showtimes = (JSONArray) movie.get("showtimes");

        // Check if user has tickets for this movie and showtime
        JSONArray userTickets = (JSONArray) account.get("tickets");
        for (Object userTicketObj : userTickets) {
            JSONObject userTicket = (JSONObject) userTicketObj;
            if (userTicket.get("movie").equals(movieName) && userTicket.get("time").equals(showtimeString)) {
                int currentNumTickets = ((Long) userTicket.get("tickets")).intValue();
                if (currentNumTickets >= numTickets) {
                    userTicket.put("tickets", currentNumTickets - numTickets);
                } else {
                    System.out
                            .println("Error: Not enough tickets to remove for " + movieName + " at " + showtimeString);
                    return;
                }
                // Remove ticket object if ticket amount becomes 0
                if (currentNumTickets - numTickets == 0) {
                    userTickets.remove(userTicketObj);
                }
                break;
            }
        }

        // Update movie tickets
        for (Object showtimeObj : showtimes) {
            JSONObject showtime = (JSONObject) showtimeObj;
            String time = (String) showtime.get("time");
            if (time.equals(showtimeString)) {
                int currentNumTickets = ((Long) showtime.get("tickets")).intValue();
                showtime.put("tickets", currentNumTickets + numTickets);
                break;
            }
        }
        movies.saveMovies();
        saveAccounts();
        System.out
                .println("Successfully removed " + numTickets + " tickets for " + movieName + " at " + showtimeString);
    }

    public static void main(String[] args) {
        Payment payment = new Payment("accounts.json", "movies.json");
        payment.bookTicket("grey", "The Matrix", "10:00 AM", 2);
    }
}
