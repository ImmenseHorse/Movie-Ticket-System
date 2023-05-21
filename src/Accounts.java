import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Accounts {
    private static String filename;
    private static JSONArray accounts;

    public Accounts(String filename) {
        this.filename = filename;
        this.accounts = loadAccounts();
    }

    private JSONArray loadAccounts() {
        JSONArray data = new JSONArray();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filename)) {
            Object obj = parser.parse(reader);
            data = (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void saveAccounts() {
        try (FileWriter file = new FileWriter(filename)) {
            file.write(accounts.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getAccount(String username) {
        for (Object accountObj : accounts) {
            JSONObject account = (JSONObject) accountObj;
            if (account.get("username").equals(username)) {
                return account;
            }
        }
        return null;
    }

    public String addAccount(String username, String password, String authority) {
        JSONObject newAccount = new JSONObject();
        newAccount.put("username", username);
        newAccount.put("password", password);
        newAccount.put("authority", authority);
        newAccount.put("balance", 0.0);
        newAccount.put("tickets", new JSONArray()); // initialize empty array for tickets

        for (Object accountObj : accounts) {
            JSONObject account = (JSONObject) accountObj;
            if (account.get("username").equals(username)) {
                return "Error Username Already Used";
            }
        }

        if (authority.equals("user") || authority.equals("worker") || authority.equals("admin")) {
            accounts.add(newAccount);
            saveAccounts();
        } else {
            return "Error Authority isnt 'User', 'worker' or 'Admin'";
        }

        return null;
    }

    public static boolean removeAccount(String username) {
        for (Object accountObj : accounts) {
            JSONObject account = (JSONObject) accountObj;
            if (account.get("username").equals(username)) {
                accounts.remove(account);
                saveAccounts();
                return true;
            }
        }
        return false;
    }

    public JSONArray printTicketHistory(String username) {
        JSONObject account = getAccount(username);
        if (account == null) {
            System.out.println("Error: Account not found");
            return null;
        }
        JSONArray tickets = (JSONArray) account.get("tickets");
        if (tickets.isEmpty()) {
            System.out.println("No ticket history found for " + username);
            return null;
        }
        for (Object ticketObj : tickets) {
            JSONObject ticket = (JSONObject) ticketObj;
            String movie = (String) ticket.get("movie");
            String time = (String) ticket.get("time");
            int numTickets = ((Long) ticket.get("tickets")).intValue();
            System.out.println("Movie: " + movie + ", Tickets: " + numTickets + ", Time: " + time);
        }
        return tickets;
    }

    public static void main(String[] args) {
        Accounts account = new Accounts("accounts.json");
        System.out.println(account.printTicketHistory("grey"));
    }

}
