import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

public class Test extends AppGUI {
    public static void main(String[] args) {
        AppGUI test1 = new AppGUI();

        // set some of the the buttion to static

        // check existing account test case 1;
        test1.usernameField = new JTextField("Person");
        test1.passwordField = new JPasswordField("1234");
        test1.signupButton.doClick(); // signup check
        System.out.println(Accounts.getAccount("Person") != null);

        // REMOVE ACCOUNT TEST CASE
        Accounts.removeAccount("Person");
        System.out.println(Accounts.getAccount("Person") == null);
        // ADD MOVIE TEST CASE;
        test1.username = "Person";
        test1.movieName = "spiderman";
        test1.showtimeString = "10:00 AM Mar26";
        test1.numTickets = 50;
        test1.description = "sample discription 1";
        test1.poster = "images/spiderman.jpg";
        test1.genrrr = "animation";
        test1.price = "20";
        Movie.addMovie(test1.movieName, test1.showtimeString, test1.numTickets, test1.description, test1.poster,
                test1.genrrr, test1.price);
        System.out.println(true == checkmovieexist(test1.movieName, test1.poster, test1.price));

        // write test for puchase check:
        test1.usernameField = new JTextField("Person");
        test1.passwordField = new JPasswordField("1234");
        test1.signupButton.doClick(); // singe up as a person
        test1.loginButton.doClick(); // login as a person
        test1.SwingOpenImage(null); // display movie
        test1.amount = new JTextField();
        test1.amount.setText("1");
        test1.movienameforpurchase = new JTextField();
        test1.movienameforpurchase.setText("matrix");

        test1.creditcard = new JTextField();
        test1.creditcard.setText("1234");
        test1.field1 = new JTextField();
        test1.field1.setText(""); // test case: no coupon;
        test1.time = new JTextField();
        test1.time.setText("10:00 AM Mar26");
        test1.seats = new JTextField();
        test1.seats.setText("E7");

        AppGUI.purchaseListener pp = test1.new purchaseListener();// check purchase
        test1.singleprice = 10;// this variable will be mapped when guest click in the movie
        pp.actionPerformed(null);
        AppGUI.reportListener report = test1.new reportListener(); // check report
        report.actionPerformed(null);
        AppGUI.ticketHistoryListener testticket = test1.new ticketHistoryListener(); // check ticket history
        testticket.actionPerformed(null);

        AppGUI.insidebutton2ActionListener checkseat = test1.new insidebutton2ActionListener();
        checkseat.actionPerformed(null);// open seat iamge file
        test1.bb.doClick();// open seat file txt

        ModifyTicketUI ticket1 = new ModifyTicketUI();
        ticket1.tmovie.setText("spiderman");
        ticket1.tplayDate.setText("10:00 AM Mar26");
        ticket1.tprice.setText("10");
        ticket1.tticketQuantity.setText("10");
        ticket1.submit.doClick();

        // MODIFY TICKET TEST CASE

        Movie movies = new Movie("movies.json");
        JSONObject Tickets = movies.getTickets("spiderman", "10:00 AM Mar26");
        System.out.println((Tickets.get("tickets").toString().equals("10")));
        System.out.println((Tickets.get("price").equals("10")));

        // remove that moive
        Movie.removeMovie(test1.movieName);
        System.out.println(false == checkmovieexist(test1.movieName, test1.poster, test1.price));

    }

    public static boolean checkmovieexist(String mn, String ps, String pc) {
        for (Object movieObj : Movie.movies) {
            JSONObject movie = (JSONObject) movieObj;

            if (movie.get("name").equals(mn) && movie.get("poster").equals(ps) && movie.get("price").equals(pc)) {
                return true;
            }
        }
        return false;
    }

}
