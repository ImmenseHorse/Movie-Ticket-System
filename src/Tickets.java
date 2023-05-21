import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.awt.event.*;

// Front End
class ModifyTicketUI extends JFrame implements ActionListener {
    public Container c;

    public JLabel title;
    public JLabel lmovie;
    // private JLabel llocation;
    // private JLabel lroom;
    public JLabel lplayDate;
    // private JLabel lplayTime;
    public JLabel lprice;
    public JLabel lticketQuantity;
    // private JLabel lseats;
    public JLabel result;

    public JTextField tmovie;
    // private JTextField tlocation;
    public JTextField troom;
    public JTextField tplayDate;
    // private JTextField tplayTime;
    public JTextField tprice;
    public JTextField tticketQuantity;
    // private JTextField tseats;
    public JButton submit;
    public JTextArea dataOut;

    public ModifyTicketUI() {
        setTitle("Admin - Modify Ticket");
        setBounds(300, 90, 1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        c = getContentPane();

        c.setLayout(null);

        title = new JLabel("Admin - Modify Ticket");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        lmovie = new JLabel("Movie Title:");
        lmovie.setFont(new Font("Arial", Font.PLAIN, 20));
        lmovie.setSize(150, 20);
        lmovie.setLocation(100, 100);
        c.add(lmovie);

        tmovie = new JTextField();
        tmovie.setFont(new Font("Arial", Font.PLAIN, 15));
        tmovie.setSize(300, 20);
        tmovie.setLocation(300, 100);
        c.add(tmovie);

        // llocation = new JLabel("Movie Location:");
        // llocation.setFont(new Font("Arial", Font.PLAIN, 20));
        // llocation.setSize(150, 20);
        // llocation.setLocation(100, 150);
        // c.add(llocation);

        // tlocation = new JTextField();
        // tlocation.setFont(new Font("Arial", Font.PLAIN, 15));
        // tlocation.setSize(300, 20);
        // tlocation.setLocation(300, 150);
        // c.add(tlocation);

        // lroom = new JLabel("Theatre Room #:");
        // lroom.setFont(new Font("Arial", Font.PLAIN, 20));
        // lroom.setSize(150, 20);
        // lroom.setLocation(100, 150);
        // c.add(lroom);

        // troom = new JTextField();
        // troom.setFont(new Font("Arial", Font.PLAIN, 15));
        // troom.setSize(300, 20);
        // troom.setLocation(300, 150);
        // c.add(troom);

        lplayDate = new JLabel("Date, time of Play:");
        lplayDate.setFont(new Font("Arial", Font.PLAIN, 20));
        lplayDate.setSize(200, 20);
        lplayDate.setLocation(100, 150);
        c.add(lplayDate);

        tplayDate = new JTextField();
        tplayDate.setFont(new Font("Arial", Font.PLAIN, 15));
        tplayDate.setSize(300, 20);
        tplayDate.setLocation(300, 150);
        c.add(tplayDate);

        // lplayTime = new JLabel("Time of Play:");
        // lplayTime.setFont(new Font("Arial", Font.PLAIN, 20));
        // lplayTime.setSize(150, 20);
        // lplayTime.setLocation(100, 300);
        // c.add(lplayTime);

        // tplayTime = new JTextField();
        // tplayTime.setFont(new Font("Arial", Font.PLAIN, 15));
        // tplayTime.setSize(300, 20);
        // tplayTime.setLocation(300, 300);
        // c.add(tplayTime);

        lprice = new JLabel("Ticket Price:");
        lprice.setFont(new Font("Arial", Font.PLAIN, 20));
        lprice.setSize(150, 20);
        lprice.setLocation(100, 200);
        c.add(lprice);

        tprice = new JTextField();
        tprice.setFont(new Font("Arial", Font.PLAIN, 15));
        tprice.setSize(300, 20);
        tprice.setLocation(300, 200);
        c.add(tprice);

        lticketQuantity = new JLabel("Ticket Quantity:");
        lticketQuantity.setFont(new Font("Arial", Font.PLAIN, 20));
        lticketQuantity.setSize(150, 20);
        lticketQuantity.setLocation(100, 250);
        c.add(lticketQuantity);

        tticketQuantity = new JTextField();
        tticketQuantity.setFont(new Font("Arial", Font.PLAIN, 15));
        tticketQuantity.setSize(300, 20);
        tticketQuantity.setLocation(300, 250);
        c.add(tticketQuantity);

        // lseats = new JLabel("Seat Quantity:");
        // lseats.setFont(new Font("Arial", Font.PLAIN, 20));
        // lseats.setSize(150, 20);
        // lseats.setLocation(100, 450);
        // c.add(lseats);

        // tseats = new JTextField();
        // tseats.setFont(new Font("Arial", Font.PLAIN, 15));
        // tseats.setSize(300, 20);
        // tseats.setLocation(300, 450);
        // c.add(tseats);

        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 15));
        submit.setSize(100, 20);
        submit.setLocation(350, 500);
        submit.addActionListener(this);
        c.add(submit);

        dataOut = new JTextArea();
        dataOut.setFont(new Font("Arial", Font.PLAIN, 15));
        dataOut.setSize(300, 400);
        dataOut.setLocation(650, 75);
        dataOut.setLineWrap(true);
        dataOut.setEditable(false);
        c.add(dataOut);

        result = new JLabel("");
        result.setFont(new Font("Arial", Font.PLAIN, 20));
        result.setSize(500, 25);
        result.setLocation(650, 500);
        c.add(result);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {

            String dataMovie = "Movie Title: " + tmovie.getText().toString() + "\n";

            // String dataLocation = "Movie Location: " + tlocation.getText() + "\n";
            // String dataRoom = "Theatre Room #: " + troom.getText() + "\n";
            String dataPlayDate = "Date and time Play: " + tplayDate.getText() + "\n";
            // String dataPlayTime = "Time of Play: " + tplayTime.getText() + "\n";
            String dataPrice = "Ticket Price: " + tprice.getText() + "\n";
            String dataTicketQuantity = "Ticket Quantity: " + tticketQuantity.getText() + "\n";
            // String dataSeats = "Seat Quantity: " + tseats.getText();

            for (Object eachmovie : Movie.movies) {
                JSONObject onemovie = (JSONObject) eachmovie;
                if (onemovie.get("name").equals(tmovie.getText().toString())) {
                    onemovie.put("price", tprice.getText());
                    JSONArray userTickets = (JSONArray) onemovie.get("showtimes");

                    for (int i = 0; i < userTickets.size(); i++) {

                        JSONObject tt = (JSONObject) userTickets.get(i);
                        if (tt.toString().indexOf(tplayDate.getText()) != -1) {
                            int modd = Integer.parseInt(tticketQuantity.getText().toString());
                            tt.put("tickets", modd);
                        }

                        Movie.saveMovies();
                        // System.out.println(tt.get("tickets"));
                    }
                }
            }

            dataOut.setText(dataMovie + dataPlayDate + dataPrice
                    + dataTicketQuantity);
            dataOut.setEditable(false);
            result.setText("Ticket is Successfully Modifed");

        }
    }
}

// Back End
class ticketInformation {
    private Movie movieDetail;
    // private String theatreLocation;
    private int theatreRoom;
    private String moviePlayDate;
    private String playTime;
    private double price;
    private int ticketQuantity;
    private int seatsAvailable;

    public Movie getMovieDetail() {
        return movieDetail;
    }

    // public String getTheatreLocation() {
    // return theatreLocation;
    // }

    public int getTheatreRoom() {
        return theatreRoom;
    }

    public String getMoviePlayDate() {
        return moviePlayDate;
    }

    public String getPlayTime() {
        return playTime;
    }

    public double getPrice() {
        return price;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setMovieDetail(Movie movieObject) {
        this.movieDetail = movieObject;
    }

    public void setTheatreLocation(String newLocation) {
        // this.theatreLocation = newLocation;
    }

    public void setTheatreRoom(int newRoom) {
        this.theatreRoom = newRoom;
    }

    public void setMoviePlayDate(String newDate) {
        this.moviePlayDate = newDate;
    }

    public void setPlayTime(String newTime) {
        this.playTime = newTime;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public void setTicketQuantity(int newQuantity) {
        this.ticketQuantity = newQuantity;
    }

    public void setSeatsAvailable(int newSeats) {
        this.seatsAvailable = newSeats;
    }
}

class ticket extends ticketInformation {
    private ticketInformation ticketInfo;
    // private int ticketID;
    // private String purchasedDate;

    public ticketInformation getTicketInformation() {
        return ticketInfo;
    }

    // public int getTicketID() {
    // return ticketID;
    // }
    // public String getPurchasedDate() {
    // return purchasedDate;
    // }

    public void setTicketInformation(ticketInformation newTicketObject) {
        this.ticketInfo = newTicketObject;
    }

    public void setTicketID(int newID) {
        // this.ticketID = newID;
    }

    // public void setPurchasedDate(String newDate) {
    // this.purchasedDate = newDate;
    // }
}

class mainClass {
    public static void main(String[] args) throws Exception {
        new ModifyTicketUI();
    }
}