import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class AppGUI {
    public ActionListener purchaseListener;
    private JPanel moviepanel;
    private String uname;
    private JPanel moviepanellll;
    private JComboBox box;
    public JFrame frame;
    private JPanel loginPanel;
    private JPanel actionPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JButton loginButton;
    private JButton logoutButton;
    private JButton addUserButton;
    private JButton removeUserButton;
    public JButton addMovieButton; // -> changed this to public for testing
    private JButton quitButton;

    private Accounts accounts;
    private Movie movies;
    private String currentUser;
    private JButton ticketHistory;

    // I add button
    public JButton removeMovie; // -> changed this to public for testing

    // I add this button, they are same
    private JButton displayMovieButtion;
    public JButton displayMovieButtion2;

    // I add this buttion
    private JButton generationreportbutton;

    // I add this button
    private JButton modifyTicket;
    public JTextField seats;
    public JTextField movienameforpurchase;
    public JTextField time;
    JLabel Coupon;
    public JTextField field1; // couponJTextField
    JLabel creaditcardtext; // creditcard
    public JTextField creditcard; // creditcard
    public JTextField amount; // multiply
    int singleprice;
    int fnalprice;
    JTextField ggeennrra;
    JTextField searchbyname;
    JLabel searchlabel;
    JButton gobButton;

    JButton modiFFyTicket;
    JTextField uuuname;
    JTextField mmoviename;
    JTextField timmm;
    JTextField tickam;

    // the following is exstracted from the scope for testing

    // for signup
    JButton signupButton;
    String username;
    String password;

    // for add movie
    String movieName;
    String showtimeString;
    int numTickets;
    String description;
    String poster;
    String genrrr;
    String price;
    // for delete movie
    String mName;

    // for purchase
    public JButton insidebutton3;
    JButton frontpurchase;
    String choosmovenname;
    String choosetime;
    String seatlocation;
    String num;
    JButton bb;
    JFrame mo;

    public AppGUI() {

        accounts = new Accounts("accounts.json");
        movies = new Movie("movies.json");

        frame = new JFrame("Movie Ticket Booking System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new java.awt.Color(114, 137, 218));

        // maybe replace it with usename for sign up
        // usernameLabel = new JLabel("Username:");
        usernameLabel = new JLabel("Username/Email:");

        usernameField = new JTextField(10);
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(10);
        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());
        signupButton = new JButton("Signup"); // extracted this to top for testing
        signupButton.addActionListener(new SignupButtonListener());

        JLabel loginLabel = new JLabel("MovieTix");

        Font font = new Font("Roberto", Font.PLAIN, 24);
        loginLabel.setFont(font);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);

        BufferedImage icccon = null;
        try {
            icccon = ImageIO.read(new File("images/logo.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel icon1 = new JLabel();
        icon1.setIcon(new ImageIcon(icccon.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        loginPanel.add(loginLabel, c);

        c.gridx = 0;
        c.gridy = 0;

        loginPanel.add(icon1, c);

        c.gridx = 0;
        c.gridy = 1;

        loginPanel.add(usernameLabel, c);

        c.gridx = 1;
        loginPanel.add(usernameField, c);

        c.gridx = 0;
        c.gridy = 2;
        loginPanel.add(passwordLabel, c);

        c.gridx = 1;
        loginPanel.add(passwordField, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        loginPanel.add(loginButton, c);

        c.gridy = 4;
        loginPanel.add(signupButton, c);

        frame.getContentPane().add(BorderLayout.CENTER, loginPanel);
        frame.setVisible(true);
    }

    private void showActionPanel() {
        GridBagConstraints c;
        frame.getContentPane().remove(loginPanel);

        actionPanel = new JPanel(new GridBagLayout());

        JLabel loginLabel = new JLabel("MovieTix");
        Font font = new Font("Roberto", Font.PLAIN, 24);
        loginLabel.setFont(font);

        GridBagConstraints loogo;
        loogo = new GridBagConstraints();
        loogo.gridx = 1;
        loogo.gridy = 0;
        ImageIcon backgg = new ImageIcon("images/logo2.png");
        JLabel backgroundimgae = new JLabel("", backgg, JLabel.RIGHT);

        actionPanel.add(backgroundimgae, loogo);
        loogo.gridx = 2;
        actionPanel.add(loginLabel, loogo);

        actionPanel.setBackground(new java.awt.Color(114, 137, 218));
        addMovieButton = new JButton("Add Movie");
        addMovieButton.addActionListener(new AddMovieButtonListener());
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new LogoutButtonListener());
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new QuitButtonListener());

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);

        actionPanel.add(quitButton, c);

        c.gridx = 3;
        actionPanel.add(logoutButton, c);

        // I added here
        c.gridx = 1;
        c.gridy = 5;
        displayMovieButtion2 = new JButton("Show movies");
        displayMovieButtion2.addActionListener(new movieActionListener());
        actionPanel.add(displayMovieButtion2, c);

        // I add this
        c.gridx = 1;
        c.gridy = 2;
        ticketHistory = new JButton("Show ticket history");
        ticketHistory.addActionListener(new ticketHistoryListener());
        actionPanel.add(ticketHistory, c);
        // listener at line 211

        // I add this for customer
        c.gridx = 2;
        c.gridy = 2;
        modifyTicket = new JButton("Delete tickets");
        modifyTicket.addActionListener(new deleteticketListener()); // for customer
        actionPanel.add(modifyTicket, c);

        // Show or hide addUserButton and removeUserButton based on authority level
        JSONObject account = accounts.getAccount(currentUser);
        String authorityLevel = (String) account.get("authority");
        if (authorityLevel.equals("admin")) {
            addUserButton = new JButton("Add User");
            addUserButton.addActionListener(new AddUserButtonListener());
            removeUserButton = new JButton("Remove User");
            removeUserButton.addActionListener(new RemoveUserButtonListener());

            // I add this for adimn
            removeMovie = new JButton("Remove movie");
            removeMovie.addActionListener(new removeMovieActionListener());

            // this is what I add for movie
            displayMovieButtion = new JButton("Show movies");
            displayMovieButtion.addActionListener(new movieActionListener());

            // I also add this button
            generationreportbutton = new JButton("Generate report");
            generationreportbutton.addActionListener(new reportListener());
            // listener at line 192

            c.gridx = 1;
            c.gridy = 1;
            actionPanel.add(addMovieButton, c);

            c.gridx = 1;
            c.gridy = 2;
            actionPanel.add(addUserButton, c);

            c.gridx = 2;
            c.gridy = 2;
            actionPanel.add(removeUserButton, c);

            c.gridx = 2;
            c.gridy = 4;
            actionPanel.add(generationreportbutton, c);

            c.gridx = 2;
            c.gridy = 4;
            // this is the delete ticket function for cusotmer
            actionPanel.remove(modifyTicket);

            c.gridx = 2;
            c.gridy = 1;
            // I add this for admin
            actionPanel.add(removeMovie, c);

            // I remove this in admin
            actionPanel.remove(ticketHistory);

            c.gridx = 1;
            c.gridy = 4;
            modiFFyTicket = new JButton("Modify ticket");
            modiFFyTicket.addActionListener(new modiFFyTicketActionlistener());
            actionPanel.add(modiFFyTicket, c);

        }
        if (authorityLevel.equals("worker")) {

            removeMovie = new JButton("Remove movie");
            removeMovie.addActionListener(new removeMovieActionListener());
            c.gridx = 1;
            c.gridy = 1;
            actionPanel.add(removeMovie, c);
            c.gridx = 2;
            actionPanel.add(addMovieButton, c);
            actionPanel.remove(modifyTicket);
            actionPanel.remove(ticketHistory);

            generationreportbutton = new JButton("Generate report");
            generationreportbutton.addActionListener(new reportListener());
            c.gridx = 1;
            c.gridy = 2;
            actionPanel.add(generationreportbutton, c);

        }

        frame.getContentPane().add(BorderLayout.NORTH,
                new JLabel("Welcome, " + currentUser + "! Your Authority is: " + authorityLevel));
        frame.getContentPane().add(BorderLayout.CENTER, actionPanel);
        frame.setVisible(true);
    }

    private void showLoginPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(BorderLayout.CENTER, loginPanel);
        // frame.getContentPane ().add(BorderLayout.SOUTH, outputArea);
        frame.revalidate();
        frame.repaint();
    }
    ///////////////////////////////////////////////////////////

    public class removeMovieActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            mName = JOptionPane.showInputDialog(frame, "Enter movie name:");
            Movie.removeMovie(mName);
        }

    }

    // this will show all the text in the admin report
    public class reportListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame adminemployee = new JFrame();
            // JPanel noseat = new JPanel();
            adminemployee.pack();
            adminemployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            adminemployee.setVisible(true);
            adminemployee.setExtendedState(JFrame.MAXIMIZED_BOTH);

            // notavalible.add(noseat);

            File seatt = new File("workerandadminreport.txt");
            try (Scanner scan = new Scanner(seatt)) {

                JTextArea area = new JTextArea(10, 100);
                JScrollPane pane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                while (scan.hasNextLine()) {
                    String ddaattaa = scan.nextLine();
                    area.append(ddaattaa + "\n");
                }

                area.setLineWrap(true);
                area.setWrapStyleWord(true);
                adminemployee.add(pane);
                adminemployee.setSize(500, 500);
                adminemployee.setVisible(true);

            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
        // This need implement

    }

    // this will show the delete ticket panel
    public class deleteticketListener implements ActionListener {

        // delete
        @Override

        public void actionPerformed(ActionEvent e) {
            JFrame deletFrame = new JFrame();
            JPanel deletPanel = new JPanel();

            deletFrame.setBounds(300, 90, 1000, 600);
            deletFrame.setLocationRelativeTo(null);
            deletFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            deletFrame.setVisible(true);

            JLabel title = new JLabel("Delete Ticket");
            title.setFont(new Font("Arial", Font.PLAIN, 30));
            title.setSize(300, 30);
            title.setLocation(400, 30);
            deletFrame.add(title);

            JLabel lmovie = new JLabel("Your name:");
            lmovie.setFont(new Font("Arial", Font.PLAIN, 20));
            lmovie.setSize(150, 20);
            lmovie.setLocation(300, 200);
            deletFrame.add(lmovie);

            uuuname = new JTextField();
            uuuname.setFont(new Font("Arial", Font.PLAIN, 15));
            uuuname.setSize(150, 20);
            uuuname.setLocation(550, 200);
            deletFrame.add(uuuname);

            JLabel lplayDate = new JLabel("Movie name:");
            lplayDate.setFont(new Font("Arial", Font.PLAIN, 20));
            lplayDate.setSize(200, 20);
            lplayDate.setLocation(300, 250);
            deletFrame.add(lplayDate);

            mmoviename = new JTextField();
            mmoviename.setFont(new Font("Arial", Font.PLAIN, 15));
            mmoviename.setSize(150, 20);
            mmoviename.setLocation(550, 250);
            deletFrame.add(mmoviename);

            JLabel dd = new JLabel("Movie time:");
            dd.setFont(new Font("Arial", Font.PLAIN, 20));
            dd.setSize(200, 20);
            dd.setLocation(300, 300);
            deletFrame.add(dd);

            timmm = new JTextField();
            timmm.setFont(new Font("Arial", Font.PLAIN, 15));
            timmm.setSize(150, 20);
            timmm.setLocation(550, 300);
            deletFrame.add(timmm);

            JLabel lticketQuantity = new JLabel("Amount as purchase:");
            lticketQuantity.setFont(new Font("Arial", Font.PLAIN, 20));
            lticketQuantity.setSize(200, 20);
            lticketQuantity.setLocation(300, 350);
            deletFrame.add(lticketQuantity);

            tickam = new JTextField();
            tickam.setFont(new Font("Arial", Font.PLAIN, 15));
            tickam.setSize(150, 20);
            tickam.setLocation(550, 350);
            deletFrame.add(tickam);

            JButton submit = new JButton("Delete");
            submit.setFont(new Font("Arial", Font.PLAIN, 15));
            submit.setSize(100, 20);
            submit.setLocation(450, 500);
            deletFrame.add(submit);

            // uuuname = new JTextField("Enter your name here");
            // mmoviename = new JTextField("Enter movie name here");
            // timmm = new JTextField("Enter movie time here");
            // tickam = new JTextField("Enter amount as purchase");

            // deletPanel.add(uuuname);
            // deletPanel.add(mmoviename);
            // deletPanel.add(timmm);
            // deletPanel.add(tickam);

            // JButton comfirmdelete = new JButton("Confim delete");

            // deletPanel.add(comfirmdelete);
            deletFrame.add(deletPanel);
            submit.addActionListener(new deleteActionListen());

        }

    }

    // this action happend when custome delete ticket and will write "delete" on the
    // admin report when the text contins username, time, and movie name of that
    // ticket
    public class deleteActionListen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ed) {
            JFrame dddFrame = new JFrame();
            JTextArea dddpp = new JTextArea();

            dddpp.append(mmoviename.getText().toString() + "\n");
            dddpp.append(timmm.getText().toString() + "\n");
            dddpp.append(tickam.getText().toString() + "\n");
            dddpp.append("Successfully deleted");
            dddFrame.pack();
            dddFrame.setLocationRelativeTo(null);
            dddFrame.setVisible(true);
            dddFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dddFrame.setSize(400, 400);
            dddFrame.add(dddpp);

            for (Object eachmovie : Movie.movies) {
                JSONObject onemovie = (JSONObject) eachmovie;
                if (onemovie.get("name").equals(mmoviename.getText().toString())) {
                    JSONArray userTickets = (JSONArray) onemovie.get("showtimes");
                    for (int i = 0; i < userTickets.size(); i++) {
                        JSONObject tt = (JSONObject) userTickets.get(i);
                        if (tt.toString().indexOf(timmm.getText()) != -1) {
                            int remainbeforedelete = Integer.parseInt(tt.get("tickets").toString());

                            tt.put("tickets", remainbeforedelete + Integer.parseInt(tickam.getText().toString()));
                            Movie.saveMovies();
                        }
                        // System.out.println(tt.get("tickets"));
                    }
                }
            }

            try {
                FileWriter deletewriter = new FileWriter("workerandadminreport.txt", true);
                Scanner sccc = new Scanner(new File("workerandadminreport.txt"));

                while (sccc.hasNextLine()) {
                    String cline = sccc.nextLine();
                    // System.out.println(cline);

                    System.out.println(uuuname.getText().toString());
                    System.out.println(mmoviename.getText().toString());
                    System.out.println(timmm.getText().toString());
                    System.out.println(cline.indexOf(uuuname.getText().toString()));
                    System.out.println(cline.indexOf(mmoviename.getText().toString()));
                    System.out.println(cline.indexOf(timmm.getText().toString()));
                    if (cline.indexOf(uuuname.getText().toString()) != -1 &&
                            cline.indexOf(mmoviename.getText().toString()) != -1
                            && cline.indexOf(timmm.getText().toString()) != -1) {
                        // System.out.println("true");
                        deletewriter.write(cline + "(deleted)" + "\n");
                        break;
                    }
                }
                sccc.close();
                deletewriter.close();

            } catch (IOException edd) {
                // TODO Auto-generated catch block
                edd.printStackTrace();
            }
        }

    }

    // this action show ticket history
    public class ticketHistoryListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame tickethistoryFrame = new JFrame();
            File thegeneralreport = new File("workerandadminreport.txt");

            tickethistoryFrame.pack();
            tickethistoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tickethistoryFrame.setVisible(true);
            tickethistoryFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            // notavalible.add(noseat);
            String personal = "";
            JTextArea area1 = new JTextArea();
            JScrollPane pane1 = new JScrollPane(area1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            try (Scanner sc = new Scanner(thegeneralreport)) {

                while (sc.hasNextLine()) {
                    String curline = sc.nextLine();
                    // System.out.println(uname);
                    if (curline.indexOf(uname) != -1)
                        area1.append(curline + "\n");
                }
                sc.close();

                area1.setLineWrap(true);
                area1.setWrapStyleWord(true);
                tickethistoryFrame.add(pane1);
                tickethistoryFrame.setSize(500, 500);
                tickethistoryFrame.setVisible(true);

            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    private class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {

            String username = usernameField.getText().toString();
            uname = username;
            String password = new String(passwordField.getPassword());
            JSONObject account = accounts.getAccount(username);

            if (account == null || !account.get("password").equals(password)) {
                // outputArea.setText("Invalid username or password. Please try again.\n");
                return;
            }

            currentUser = username;
            // outputArea.setText("Welcome, " + currentUser + "!\n");
            showActionPanel();
        }
    }

    private class AddUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String username = JOptionPane.showInputDialog(frame, "Enter new username:");
            if (username == null)
                return;

            String password = JOptionPane.showInputDialog(frame, "Enter new password:");
            if (password == null)
                return;

            String authority = JOptionPane.showInputDialog(frame,
                    "Enter new user authority ('user', 'worker', 'admin':");
            if (authority == null)
                return;

            accounts.addAccount(username, password, authority);
            //// .setText("User " + username + " added successfully.\n");
        }
    }

    private class RemoveUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String username = JOptionPane.showInputDialog(frame, "Enter username to remove:");
            if (username == null)
                return;

            if (accounts.removeAccount(username)) {
                // .setText("User " + username + " removed successfully.\n");
            } else {
                // .setText("User " + username + " not found.\n");
            }
        }
    }

    private class AddMovieButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            movieName = JOptionPane.showInputDialog(frame, "Enter new movie name:");
            if (movieName == null)
                return;

            showtimeString = JOptionPane.showInputDialog(frame, "Enter showtime (e.g. 10:00 AM Mar26):");
            if (showtimeString == null)
                return;

            numTickets = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of tickets:"));
            if (numTickets < 0) {
                // .setText("Invalid number of tickets.\n");
                return;
            }

            description = JOptionPane.showInputDialog(frame, "Enter movie description:");
            if (description == null)
                return;

            // Iadd this one
            poster = JOptionPane.showInputDialog(frame, "Enter the path to poster:");
            if (poster == null)
                return;

            genrrr = JOptionPane.showInputDialog(frame, "Enter the genre:");
            if (poster == null)
                return;

            price = JOptionPane.showInputDialog(frame, "Enter the price:");
            if (poster == null)
                return;

            // String seats = JOptionPane.showInputDialog(frame, "enter days and all
            // seats");
            // if (seats==null) return;

            JSONArray showtimes = new JSONArray();
            JSONObject showtimeJson = new JSONObject();
            showtimeJson.put("time", showtimeString);
            showtimeJson.put("tickets", numTickets);
            showtimes.add(showtimeJson);

            movies.addMovie(movieName, showtimes.toJSONString(), numTickets, description, poster, genrrr, price);

            // .setText("Movie " + movieName + " added successfully.\n");
        }
    }

    private class LogoutButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            currentUser = null;
            showLoginPanel();
        }
    }

    private class QuitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            System.exit(0);
        }
    }

    private class SignupButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            username = usernameField.getText(); // exttracted this from here for testing
            password = new String(passwordField.getPassword()); // exttracted this from here for testing

            if (accounts.getAccount(username) != null) {
                // User already exists, show error message
                JOptionPane.showMessageDialog(frame, "Username already exists, please choose a different one.",
                        "Signup Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Add new user to accounts
                accounts.addAccount(username, password, "user");
                currentUser = username;
                // Show action panel for new user
                showActionPanel();
            }
        }
    }

    // this action display movie
    public void SwingOpenImage(String s) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JPanel thetextpanel = new JPanel();

                JLabel extraLable1 = new JLabel("Search by name");
                extraLable1.setFont(new java.awt.Font("Segoe UI", 1, 18));
                searchbyname = new JTextField();
                searchbyname.setPreferredSize(new Dimension(100, 50));
                Icon extraIcon = new ImageIcon("images/search.png");
                JButton ExtragobButton = new JButton(extraIcon);
                ExtragobButton.setPreferredSize(new Dimension(50, 50));
                ExtragobButton.addActionListener(new filterbynameactionlistener());

                mo = new JFrame("All movies");
                mo.getContentPane().setBackground(new java.awt.Color(66, 69, 73));
                BorderLayout bo = new BorderLayout(30, 30);

                mo.setLayout(bo);
                searchlabel = new JLabel("Search by genre");
                searchlabel.setFont(new java.awt.Font("Segoe UI", 1, 18));

                ggeennrra = new JTextField();
                Icon icon2 = new ImageIcon("images/search.png");
                gobButton = new JButton(icon2);
                gobButton.setPreferredSize(new Dimension(50, 50));
                gobButton.addActionListener(new filterActionlistionen());

                moviepanellll = new JPanel(new BorderLayout());

                BufferedImage icccon = null;
                try {
                    icccon = ImageIO.read(new File("images/logo.png"));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                JLabel icon1 = new JLabel();
                icon1.setIcon(new ImageIcon(icccon.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

                // thetextpanel.setLayout(new FlowLayout());

                JLabel Jl1 = new JLabel(
                        "MovieTix                                                                                                                                                 ");
                Jl1.setFont(new java.awt.Font("Segoe UI", 1, 18));
                thetextpanel.add(icon1);
                thetextpanel.add(Jl1);

                thetextpanel.add(extraLable1);
                thetextpanel.add(searchbyname);
                thetextpanel.add(ExtragobButton);

                thetextpanel.add(searchlabel);
                thetextpanel.add(ggeennrra);
                thetextpanel.add(gobButton);
                ggeennrra.setPreferredSize(new Dimension(100, 50));
                thetextpanel.setMinimumSize(new Dimension(1000, 50));

                BufferedImage icccon3 = null;
                try {
                    icccon3 = ImageIO.read(new File("images/account.png"));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                JLabel icon3 = new JLabel();
                icon3.setIcon(new ImageIcon(icccon3.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                icon3.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ticketHistoryListener showhistory = new ticketHistoryListener();
                        showhistory.actionPerformed(null);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                });

                thetextpanel.add(icon3);
                mo.add(thetextpanel, BorderLayout.NORTH);

                thetextpanel.setBackground(new java.awt.Color(114, 137, 218));

                // moviepanellll.add(thetextpanel, BorderLayout.SOUTH);
                // moviepanellll.setLayout(new GridLayout(4, 4));
                int num = 0;
                for (Object eachmovie : Movie.movies) {

                    num++;
                    JSONObject onemovie = (JSONObject) eachmovie;
                    String pppp = onemovie.get("poster").toString();
                    String dddd = onemovie.get("descriptions").toString();
                    String mmmname = onemovie.get("name").toString();
                    String ppppp = onemovie.get("price").toString();
                    singleprice = Integer.parseInt(ppppp);
                    String ss = onemovie.get("showtimes").toString();

                    ArrayList<String> slidcedlist = new ArrayList<>();

                    for (int i = 1; i < ss.length(); i++) {
                        if (ss.charAt(i) == 'M') {
                            if (ss.charAt(i - 1) == 'A' || ss.charAt(i - 1) == 'P') {
                                slidcedlist.add(ss.substring(i - 7, i + 7));

                            }
                        }
                    }
                    JComboBox box = new JComboBox<>(slidcedlist.toArray());
                    System.out.println(slidcedlist);

                    String res = ss.substring(ss.indexOf("time"), ss.length());
                    // System.out.println(res);

                    BufferedImage imgg = null;
                    try {
                        imgg = ImageIO.read(new File(pppp));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    JLabel lblcurent = new JLabel(mmmname);

                    lblcurent.setIcon(new ImageIcon(imgg.getScaledInstance(261, 380, Image.SCALE_SMOOTH)));
                    lblcurent.setHorizontalAlignment(SwingConstants.CENTER);
                    lblcurent.setVerticalAlignment(SwingConstants.TOP);
                    lblcurent.setHorizontalTextPosition(SwingConstants.CENTER);
                    lblcurent.setVerticalTextPosition(SwingConstants.BOTTOM);
                    lblcurent.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                    lblcurent.setForeground(new java.awt.Color(229, 226, 226));

                    lblcurent.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {

                        }

                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                            formMouseEntered(evt);
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                            formMouseExited(evt);
                        }

                        private void formMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseEntered
                            lblcurent.setBorder(new MatteBorder(2, 0, 0, 0, new java.awt.Color(66, 69, 73)));

                        }// GEN-LAST:event_formMouseEntered

                        private void formMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseExited
                            lblcurent.setBorder(null);
                        }// GEN-LAST:event_formMouseExited

                    });

                    lblcurent.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            // TODO Auto-generated method stub

                            JFrame moviedescriptionandpurchase = new JFrame();

                            moviedescriptionandpurchase.pack();
                            moviedescriptionandpurchase.setLocationRelativeTo(null);
                            moviedescriptionandpurchase.setBounds(300, 90, 1000, 600);
                            moviedescriptionandpurchase.setVisible(true);
                            // moviedescriptionandpurchase.setSize(500, 500);
                            JPanel thepanel = new JPanel();

                            JLabel panelname = new JLabel("Ticket purchase");
                            panelname.setFont(new Font("Arial", Font.PLAIN, 30));
                            panelname.setSize(300, 30);
                            panelname.setLocation(400, 30);
                            thepanel.add(panelname);
                            // movie.name
                            JLabel moviename = new JLabel(mmmname, SwingConstants.CENTER);
                            moviename.setFont(new Font("Arial", Font.PLAIN, 15));

                            // movie.discription
                            JTextArea moivediscription = new JTextArea(
                                    dddd,
                                    50, 50);

                            //
                            // movie.price
                            JLabel price = new JLabel(ppppp, SwingConstants.CENTER);
                            amount = new JTextField("Enter amount of tickets:");
                            // amount.setSize(20, 20);
                            String thetime = res.substring(35, 49).toString();

                            // JLabel insidebutton1 = new JLabel("Time:" + thetime, SwingConstants.CENTER);
                            // insidebutton1.setFont(new Font("Arial", Font.PLAIN, 15));
                            // insidebutton1.setLineWrap(true);
                            // insidebutton1.setWrapStyleWord(true);
                            time = new JTextField();

                            JButton insidebutton2 = new JButton("Seats layout:");
                            insidebutton2.setFont(new Font("Arial", Font.PLAIN, 15));
                            insidebutton2.addActionListener(new insidebutton2ActionListener());

                            seats = new JTextField();

                            Coupon = new JLabel("Enter your coupon:", SwingConstants.CENTER);
                            Coupon.setFont(new Font("Arial", Font.PLAIN, 15));
                            field1 = new JTextField(30);

                            creaditcardtext = new JLabel("Enter your credit card:", SwingConstants.CENTER);
                            creditcard = new JTextField(30);
                            creaditcardtext.setFont(new Font("Arial", Font.PLAIN, 15));

                            JLabel movielabel = new JLabel("Movie name:", SwingConstants.CENTER);
                            movienameforpurchase = new JTextField();
                            movienameforpurchase.setText(mmmname);
                            movielabel.setFont(new Font("Arial", Font.PLAIN, 15));

                            insidebutton3 = new JButton("Purchase");
                            insidebutton3.addActionListener(new purchaseListener());

                            moivediscription.setLineWrap(true);
                            moivediscription.setWrapStyleWord(true);
                            moivediscription.setOpaque(false);
                            moivediscription.setEditable(false);

                            thepanel.add(moviename);
                            thepanel.add(moivediscription);
                            moviename.setSize(150, 20);
                            moviename.setLocation(340, 100);
                            moivediscription.setSize(300, 20);
                            moivediscription.setLocation(540, 100);

                            thepanel.add(movielabel);
                            thepanel.add(movienameforpurchase);
                            movielabel.setSize(150, 20);
                            movielabel.setLocation(340, 150);
                            movienameforpurchase.setSize(150, 20);
                            movienameforpurchase.setLocation(540, 150);

                            thepanel.add(price);
                            thepanel.add(amount);
                            price.setSize(150, 20);
                            price.setLocation(340, 200);
                            amount.setSize(150, 20);
                            amount.setLocation(540, 200);

                            thepanel.add(Coupon);
                            thepanel.add(field1);
                            Coupon.setSize(150, 20);
                            Coupon.setLocation(340, 250);
                            field1.setSize(150, 20);
                            field1.setLocation(540, 250);

                            thepanel.add(creaditcardtext);
                            thepanel.add(creditcard);
                            creaditcardtext.setSize(150, 20);
                            creaditcardtext.setLocation(340, 300);
                            creditcard.setSize(150, 20);
                            creditcard.setLocation(540, 300);

                            thepanel.add(box);
                            thepanel.add(time);
                            box.setSize(150, 20);
                            box.setLocation(340, 350);
                            // time.setText(box.getText());
                            time.setSize(150, 20);
                            time.setLocation(540, 350);

                            thepanel.add(insidebutton2);
                            thepanel.add(seats);
                            insidebutton2.setSize(150, 20);
                            insidebutton2.setLocation(340, 400);
                            seats.setSize(150, 20);
                            seats.setLocation(540, 400);

                            thepanel.add(insidebutton3);
                            insidebutton3.setSize(100, 20);
                            insidebutton3.setLocation(450, 500);

                            thepanel.setLayout(null);
                            // thepanel.setLayout(new GridLayout(2,8));

                            moviedescriptionandpurchase.add(thepanel);

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            // TODO Auto-generated method stub

                        }

                    }

                    );

                    // mo.add(lblcurent, BorderLayout.CENTER);

                    mo.pack();
                    mo.setLocationRelativeTo(null);
                    mo.setVisible(true);

                    moviepanellll.add(lblcurent);
                    moviepanellll.setBackground(new java.awt.Color(66, 69, 73));

                }

                if (num % 4 == 0) {
                    moviepanellll.setLayout(new GridLayout(num / 4, 4, 20, 20));
                } else {
                    moviepanellll.setLayout(new GridLayout((num / 4) + 1, 4, 20, 20));
                }
                JScrollPane pane = new JScrollPane(
                        moviepanellll, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                mo.add(pane);

                pane.getVerticalScrollBar().setUnitIncrement(30);
                pane.setBorder(null);

                // mo.add(moviepanellll);
                mo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mo.setVisible(true);
                mo.setExtendedState(JFrame.MAXIMIZED_BOTH);
                // mo.setUndecorated(true);

            }

        });

    }

    // this action display movies
    public class movieActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            // showmoviepanel() will be delete;
            // the upper one is original by me as example will be delete;

            SwingOpenImage("sample string");

        }

    }

    // this action show the seat layout and the show seat not avabile button
    public class insidebutton2ActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent eb) {
            JFrame ffffffff = new JFrame();
            ffffffff.setExtendedState(JFrame.MAXIMIZED_BOTH);
            JPanel ssssppp = new JPanel();
            ssssppp.setBackground(new java.awt.Color(114, 137, 218));
            ffffffff.pack();
            ffffffff.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ffffffff.setVisible(true);
            bb = new JButton("Seats are not available");
            BufferedImage layoutout = null;
            try {
                layoutout = ImageIO.read(new File("images\\layout.jpg"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            JLabel thelayout = new JLabel();
            thelayout.setIcon(new ImageIcon(layoutout));
            ssssppp.add(thelayout);
            ssssppp.add(bb);

            bb.addActionListener(new popupfilelistener());
            ffffffff.add(ssssppp);
        }

    }

    // This action show the seat from data.txt
    public class popupfilelistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame notavalible = new JFrame();
            // JPanel noseat = new JPanel();
            notavalible.pack();
            notavalible.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            notavalible.setVisible(true);
            notavalible.setExtendedState(JFrame.MAXIMIZED_BOTH);

            // notavalible.add(noseat);

            File seatt = new File("data.txt");
            try (Scanner scan = new Scanner(seatt)) {

                JTextArea area = new JTextArea(10, 100);
                JScrollPane pane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                while (scan.hasNextLine()) {
                    String ddaattaa = scan.nextLine();
                    area.append(ddaattaa + "\n");
                }
                scan.close();
                area.setLineWrap(true);
                area.setWrapStyleWord(true);
                notavalible.add(pane);
                notavalible.setSize(500, 500);
                notavalible.setVisible(true);

            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }

    }

    // this is the puchaseListen in after customer click the puchase button in side
    // the poster
    public class purchaseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            choosmovenname = movienameforpurchase.getText();
            choosetime = time.getText();
            seatlocation = seats.getText();
            num = field1.getText();
            // payment = new Payment("accounts.json", "movies.json");
            // payment.bookTicket(uname, choosmovenname, choosetime,
            // Integer.parseInt(amount.getText().toString()));

            // Movie.removeTickets(choosmovenname, choosetime,
            // Integer.parseInt(amount.getText().toString()));
            for (Object eachmovie : Movie.movies) {
                JSONObject onemovie = (JSONObject) eachmovie;
                if (onemovie.get("name").equals(choosmovenname)) {
                    JSONArray userTickets = (JSONArray) onemovie.get("showtimes");
                    for (int i = 0; i < userTickets.size(); i++) {
                        JSONObject tt = (JSONObject) userTickets.get(i);
                        if (tt.toString().indexOf(choosetime) != -1) {
                            int remain = Integer.parseInt(tt.get("tickets").toString())
                                    - Integer.parseInt(amount.getText().toString());
                            tt.put("tickets", remain);
                            Movie.saveMovies();
                        }
                        // System.out.println(tt.get("tickets"));
                    }
                }
            }

            fnalprice = Integer.parseInt(amount.getText().toString()) * singleprice;

            JFrame succesFrame = new JFrame();
            JTextArea succesJPanel = new JTextArea();
            // succesFrame.setLayout(new GridLayout(2, 8));
            succesFrame.pack();
            succesFrame.setLocationRelativeTo(null);
            succesFrame.setVisible(true);
            succesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            succesFrame.setSize(400, 400);
            succesFrame.add(succesJPanel);
            if (num.length() > 0) {
                fnalprice = fnalprice - 3 * Integer.parseInt(amount.getText().toString());
            }
            JLabel ss1 = new JLabel("Movie name:" + " " + choosmovenname);
            JLabel ss2 = new JLabel("Single price:" + " " + String.valueOf(singleprice));
            JLabel ss3 = new JLabel("Final price:" + " " + String.valueOf(fnalprice));
            JLabel ss4 = new JLabel("Number of ticket:" + " " + amount.getText().toString());
            JLabel ss5;
            if (num.length() > 0) {
                ss5 = new JLabel("Coupon apply");
            } else {
                ss5 = new JLabel("No coupon");
            }

            String carnum = creditcard.getText();
            JLabel ss7 = new JLabel("Time:" + " " + choosetime + " ");
            JLabel ssss77 = new JLabel("Seat:" + seatlocation + " ");
            JLabel ss8 = new JLabel("Credit-card number:" + carnum + '\n' + "purchased sucessfully! ");

            String ssss1 = ss1.getText(); // "movie name"
            String ssss2 = ss2.getText(); // "singe price"
            String ssss3 = ss3.getText(); // "final price"
            String ssss4 = ss4.getText(); // "number of ticket"
            String ssss5 = ss5.getText(); // "coupon or not"
            String ssss7 = ss7.getText(); // "time"
            String sssss77 = ssss77.getText(); // "seat"
            String ssss8 = ss1.getText(); // "movie name"

            succesJPanel.append(ss1.getText() + "\n");
            succesJPanel.append(ss2.getText() + "\n");
            succesJPanel.append(ss3.getText() + "\n");
            succesJPanel.append(ss4.getText() + "\n");
            succesJPanel.append(ss5.getText() + "\n");
            succesJPanel.append(ss7.getText() + "\n");
            succesJPanel.append(ssss77.getText() + "\n");
            succesJPanel.append(ss8.getText() + "\n");

            // System.out.println(choosmovenname);
            // System.out.println("single price:");
            // System.out.println(singleprice);
            // System.out.println("number of ticket");
            // System.out.println(amount.getText().toString());
            // System.out.println("coupon");
            // System.out.println(num);
            // System.out.println("final price");
            // System.out.println(fnalprice);
            // System.out.println("username");
            // System.out.println(uname);
            // System.out.println("time");
            // System.out.println(choosetime);
            // System.out.println("seat");
            // System.out.println(sssss77);
            // System.out.println("card number");
            // System.out.println(carnum);

            // the following write the occupied seat to the data.txt
            try (FileWriter anotherwriter = new FileWriter("data.txt", true)) {
                anotherwriter.write(choosmovenname + " " + sssss77 + "occupy" + " " + ssss7 + " " + "\n");
                anotherwriter.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            try (FileWriter wirter = new FileWriter("workerandadminreport.txt", true)) {
                // the following write the about string to the admin report line 1190 to 1197
                wirter.append(uname + " " + choosmovenname + " " + ssss1 + " " + ssss7 + " ");
                wirter.write("\n");
                wirter.write(uname + " " + ssss2 + " ");
                wirter.write("\n");
                wirter.write(uname + " " + ssss3 + " ");
                wirter.write("\n");
                wirter.write(uname + " " + ssss4 + " ");
                wirter.write("\n");
                wirter.write(uname + " " + ssss5 + " ");
                wirter.write("\n");
                wirter.write(uname + " " + ssss7 + " ");
                wirter.write("\n");
                wirter.write(uname + " " + ssss8 + " ");
                wirter.write("\n");
                wirter.write(uname + " " + "Seat:" + sssss77 + " ");
                wirter.write("\n");
                wirter.write(uname + " " + "Credit-card number:" + carnum + " ");
                wirter.write("\n");
                wirter.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }

    // this is the action listener when the seach button is click
    public class filterActionlistionen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!ggeennrra.getText().toString().equals("")) {
                mo.dispose();
                SwingOpenImage2(ggeennrra.getText().toString());
            } else {
                SwingOpenImage("");
            }

        }

    }

    public void SwingOpenImage2(String inn) {
        // this is the method perform filter
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                mo = new JFrame("");
                mo.getContentPane().setBackground(new java.awt.Color(66, 69, 73));
                BorderLayout bo = new BorderLayout(15, 15);
                JPanel thetextpanel = new JPanel();

                JLabel extraLable1 = new JLabel("Search by name");
                extraLable1.setFont(new java.awt.Font("Segoe UI", 1, 18));
                searchbyname = new JTextField();
                searchbyname.setPreferredSize(new Dimension(100, 50));
                Icon extraIcon = new ImageIcon("images/search.png");
                JButton ExtragobButton = new JButton(extraIcon);
                ExtragobButton.setPreferredSize(new Dimension(50, 50));
                ExtragobButton.addActionListener(new filterbynameactionlistener());

                mo.setLayout(bo);
                searchlabel = new JLabel("Search by genre");
                searchlabel.setFont(new java.awt.Font("Segoe UI", 1, 18));

                // textfield for search
                ggeennrra = new JTextField();

                Icon icon2 = new ImageIcon("images/search.png");

                gobButton = new JButton(icon2);
                gobButton.setPreferredSize(new Dimension(50, 50));
                gobButton.addActionListener(new filterActionlistionen());

                moviepanellll = new JPanel(new BorderLayout());

                // moviepanellll.setBorder(BorderFactory.createCompoundBorder(
                // BorderFactory.createEmptyBorder(10, 10, 10, 10),
                // BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK)));
                BufferedImage icccon = null;
                try {
                    icccon = ImageIO.read(new File("images/logo.png"));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                JLabel icon1 = new JLabel();
                icon1.setIcon(new ImageIcon(icccon.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

                // thetextpanel.setLayout(new FlowLayout());

                JLabel Jl1 = new JLabel(
                        "MovieTix                                                                                                                                                      ");
                Jl1.setFont(new java.awt.Font("Segoe UI", 1, 18));
                thetextpanel.add(icon1);
                thetextpanel.add(Jl1);

                thetextpanel.add(extraLable1);
                thetextpanel.add(searchbyname);
                thetextpanel.add(ExtragobButton);

                thetextpanel.add(searchlabel);
                thetextpanel.add(ggeennrra);
                thetextpanel.add(gobButton);
                ggeennrra.setPreferredSize(new Dimension(100, 50));
                thetextpanel.setMinimumSize(new Dimension(1000, 50));

                BufferedImage icccon3 = null;
                try {
                    icccon3 = ImageIO.read(new File("images/account.png"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                // this is the account icon
                JLabel icon3 = new JLabel();
                icon3.setIcon(new ImageIcon(icccon3.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                icon3.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ticketHistoryListener showhistory = new ticketHistoryListener();
                        showhistory.actionPerformed(null);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                });

                thetextpanel.add(icon3);
                mo.add(thetextpanel, BorderLayout.NORTH);

                thetextpanel.setBackground(new java.awt.Color(114, 137, 218));

                int movienum = 0;

                for (Object eachmovie : Movie.movies) {

                    JSONObject onemovie = (JSONObject) eachmovie;
                    String pppp = onemovie.get("poster").toString();
                    String dddd = onemovie.get("descriptions").toString();
                    String mmmname = onemovie.get("name").toString();
                    String ppppp = onemovie.get("price").toString();
                    String ggggeeeera = onemovie.get("genre").toString();
                    String ss = onemovie.get("showtimes").toString();
                    String res = ss.substring(ss.indexOf("time"), ss.length());
                    // System.out.println(res);
                    // the above varible when be use if match the seach result in the purchec page
                    if (inn.equals(ggggeeeera)) {
                        movienum++;
                        BufferedImage imgg = null;
                        try {
                            imgg = ImageIO.read(new File(pppp));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        ArrayList<String> slidcedlist = new ArrayList<>();

                        for (int i = 1; i < ss.length(); i++) {
                            if (ss.charAt(i) == 'M') {
                                if (ss.charAt(i - 1) == 'A' || ss.charAt(i - 1) == 'P') {
                                    slidcedlist.add(ss.substring(i - 7, i + 7));

                                }
                            }
                        }
                        JComboBox box = new JComboBox<>(slidcedlist.toArray());

                        JLabel lblcurent = new JLabel(mmmname);
                        lblcurent.setIcon(new ImageIcon(imgg.getScaledInstance(261, 380, Image.SCALE_SMOOTH)));
                        lblcurent.setHorizontalAlignment(SwingConstants.CENTER);
                        lblcurent.setVerticalAlignment(SwingConstants.TOP);
                        lblcurent.setHorizontalTextPosition(SwingConstants.CENTER);
                        lblcurent.setVerticalTextPosition(SwingConstants.BOTTOM);

                        lblcurent.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                        lblcurent.setForeground(new java.awt.Color(229, 226, 226));
                        lblcurent.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {

                            }

                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                formMouseEntered(evt);
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
                                formMouseExited(evt);
                            }

                            private void formMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseEntered
                                // lblcurent.setSize(new Dimension(200, 300));
                                lblcurent.setBorder(new MatteBorder(2, 0, 0, 0, new java.awt.Color(66, 69, 73)));
                            }// GEN-LAST:event_formMouseEntered

                            private void formMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseExited
                                lblcurent.setBorder(null);
                            }// GEN-LAST:event_formMouseExited

                        });
                        lblcurent.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                // TODO Auto-generated method stub
                                JFrame moviedescriptionandpurchase = new JFrame();

                                moviedescriptionandpurchase.pack();
                                moviedescriptionandpurchase.setLocationRelativeTo(null);
                                moviedescriptionandpurchase.setBounds(300, 90, 1000, 600);
                                moviedescriptionandpurchase.setVisible(true);
                                JPanel thepanel = new JPanel();

                                JLabel panelname = new JLabel("Ticket purchase");
                                panelname.setFont(new Font("Arial", Font.PLAIN, 30));
                                panelname.setSize(300, 30);
                                panelname.setLocation(400, 30);
                                thepanel.add(panelname);
                                // movie.name
                                JLabel moviename = new JLabel(mmmname, SwingConstants.CENTER);
                                moviename.setFont(new Font("Arial", Font.PLAIN, 15));

                                JTextArea moivediscription = new JTextArea(
                                        dddd,
                                        50, 50);

                                //
                                // movie.price
                                JLabel price = new JLabel(ppppp, SwingConstants.CENTER);
                                amount = new JTextField("Enter amount:");

                                String thetime = res.substring(35, 49).toString();
                                // JLabel insidebutton1 = new JLabel("Time:" + thetime, SwingConstants.CENTER);
                                // insidebutton1.setFont(new Font("Arial", Font.PLAIN, 15));
                                // insidebutton1.addActionListener(new insidebutton1ActionListener());
                                time = new JTextField();

                                JButton insidebutton2 = new JButton("Seats layout:");
                                insidebutton2.setFont(new Font("Arial", Font.PLAIN, 15));
                                insidebutton2.addActionListener(new insidebutton2ActionListener());

                                seats = new JTextField();

                                Coupon = new JLabel("Enter your coupon:", SwingConstants.CENTER);
                                Coupon.setFont(new Font("Arial", Font.PLAIN, 15));
                                field1 = new JTextField(30);

                                creaditcardtext = new JLabel("Enter your Credit card:", SwingConstants.CENTER);
                                creditcard = new JTextField(30);
                                creaditcardtext.setFont(new Font("Arial", Font.PLAIN, 15));

                                JLabel movielabel = new JLabel("Movie name:", SwingConstants.CENTER);
                                movienameforpurchase = new JTextField();
                                movielabel.setFont(new Font("Arial", Font.PLAIN, 15));

                                insidebutton3 = new JButton("Purchase");
                                insidebutton3.addActionListener(new purchaseListener());

                                moivediscription.setLineWrap(true);
                                moivediscription.setWrapStyleWord(true);
                                moivediscription.setOpaque(false);
                                moivediscription.setEditable(false);

                                thepanel.add(moviename);
                                thepanel.add(moivediscription);
                                moviename.setSize(150, 20);
                                moviename.setLocation(340, 100);
                                moivediscription.setSize(300, 20);
                                moivediscription.setLocation(540, 100);

                                thepanel.add(movielabel);
                                thepanel.add(movienameforpurchase);
                                movielabel.setSize(150, 20);
                                movielabel.setLocation(340, 150);
                                movienameforpurchase.setSize(150, 20);
                                movienameforpurchase.setLocation(540, 150);

                                thepanel.add(price);
                                thepanel.add(amount);
                                price.setSize(150, 20);
                                price.setLocation(340, 200);
                                amount.setSize(150, 20);
                                amount.setLocation(540, 200);

                                thepanel.add(Coupon);
                                thepanel.add(field1);
                                Coupon.setSize(150, 20);
                                Coupon.setLocation(340, 250);
                                field1.setSize(150, 20);
                                field1.setLocation(540, 250);

                                thepanel.add(creaditcardtext);
                                thepanel.add(creditcard);
                                creaditcardtext.setSize(160, 20);
                                creaditcardtext.setLocation(340, 300);
                                creditcard.setSize(150, 20);
                                creditcard.setLocation(540, 300);

                                thepanel.add(box);
                                thepanel.add(time);
                                box.setSize(160, 20);
                                box.setLocation(340, 350);

                                time.setSize(150, 20);
                                time.setLocation(540, 350);

                                thepanel.add(insidebutton2);
                                thepanel.add(seats);
                                insidebutton2.setSize(150, 20);
                                insidebutton2.setLocation(340, 400);
                                seats.setSize(150, 20);
                                seats.setLocation(540, 400);

                                thepanel.add(insidebutton3);
                                insidebutton3.setSize(100, 20);
                                insidebutton3.setLocation(450, 500);

                                thepanel.setLayout(null);
                                // thepanel.setLayout(new GridLayout(2,8));

                                moviedescriptionandpurchase.add(thepanel);

                            }

                            @Override
                            public void mousePressed(MouseEvent e) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                // TODO Auto-generated method stub

                            }
                        });

                        // mo.add(lblcurent, BorderLayout.CENTER);
                        mo.pack();
                        mo.setLocationRelativeTo(null);
                        mo.setVisible(true);
                        moviepanellll.add(lblcurent);
                        moviepanellll.setBackground(new java.awt.Color(66, 69, 73));
                    }
                }

                if (movienum % 4 == 0) {
                    moviepanellll.setLayout(new GridLayout(movienum / 4, 4));
                } else {
                    moviepanellll.setLayout(new GridLayout((movienum / 4) + 1, 4));
                }

                JScrollPane pane = new JScrollPane(
                        moviepanellll, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                pane.getVerticalScrollBar().setUnitIncrement(30);
                mo.add(pane);
                pane.setBorder(null);
                // mo.add(moviepanellll);
                mo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mo.setVisible(true);
                mo.setExtendedState(JFrame.MAXIMIZED_BOTH);
                // mo.setUndecorated(true);

                //////////////////////////////////////////////////////////////////////////////////

            }
        });

    }

    // this is the modifyticketactionlisterner
    public class modiFFyTicketActionlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ModifyTicketUI();

        }

    }

    public class filterbynameactionlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!searchbyname.getText().toString().equals("")) {
                mo.dispose();
                SwingOpenImage3(searchbyname.getText().toString());
            } else {
                SwingOpenImage("");
            }

        }

    }

    public void SwingOpenImage3(String inn) {
        // this is the method perform filter
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                mo = new JFrame("");
                mo.getContentPane().setBackground(new java.awt.Color(66, 69, 73));
                BorderLayout bo = new BorderLayout(15, 15);

                JPanel thetextpanel = new JPanel();

                JLabel extraLable1 = new JLabel("Search by name");
                extraLable1.setFont(new java.awt.Font("Segoe UI", 1, 18));
                searchbyname = new JTextField();
                searchbyname.setPreferredSize(new Dimension(100, 50));
                Icon extraIcon = new ImageIcon("images/search.png");
                JButton ExtragobButton = new JButton(extraIcon);
                ExtragobButton.setPreferredSize(new Dimension(50, 50));
                ExtragobButton.addActionListener(new filterbynameactionlistener());

                mo.setLayout(bo);
                searchlabel = new JLabel("Search by genre");
                searchlabel.setFont(new java.awt.Font("Segoe UI", 1, 18));

                // textfield for search
                ggeennrra = new JTextField();

                Icon icon2 = new ImageIcon("images/search.png");

                gobButton = new JButton(icon2);
                gobButton.setPreferredSize(new Dimension(50, 50));
                gobButton.addActionListener(new filterActionlistionen());

                moviepanellll = new JPanel(new BorderLayout());

                // moviepanellll.setBorder(BorderFactory.createCompoundBorder(
                // BorderFactory.createEmptyBorder(10, 10, 10, 10),
                // BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK)));
                BufferedImage icccon = null;
                try {
                    icccon = ImageIO.read(new File("images/logo.png"));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                JLabel icon1 = new JLabel();
                icon1.setIcon(new ImageIcon(icccon.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

                // thetextpanel.setLayout(new FlowLayout());

                JLabel Jl1 = new JLabel(
                        "MovieTix                                                                                                                                                  ");
                Jl1.setFont(new java.awt.Font("Segoe UI", 1, 18));
                thetextpanel.add(icon1);
                thetextpanel.add(Jl1);

                thetextpanel.add(extraLable1);
                thetextpanel.add(searchbyname);
                thetextpanel.add(ExtragobButton);

                thetextpanel.add(searchlabel);
                thetextpanel.add(ggeennrra);
                thetextpanel.add(gobButton);
                ggeennrra.setPreferredSize(new Dimension(100, 50));
                thetextpanel.setMinimumSize(new Dimension(1000, 50));

                BufferedImage icccon3 = null;
                try {
                    icccon3 = ImageIO.read(new File("images/account.png"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                // this is the account icon
                JLabel icon3 = new JLabel();
                icon3.setIcon(new ImageIcon(icccon3.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                icon3.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ticketHistoryListener showhistory = new ticketHistoryListener();
                        showhistory.actionPerformed(null);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                });

                thetextpanel.add(icon3);
                mo.add(thetextpanel, BorderLayout.NORTH);

                thetextpanel.setBackground(new java.awt.Color(114, 137, 218));

                int movienum = 0;

                for (Object eachmovie : Movie.movies) {

                    JSONObject onemovie = (JSONObject) eachmovie;
                    String pppp = onemovie.get("poster").toString();
                    String dddd = onemovie.get("descriptions").toString();
                    String mmmname = onemovie.get("name").toString();
                    String ppppp = onemovie.get("price").toString();
                    String ggggeeeera = onemovie.get("genre").toString();
                    String ss = onemovie.get("showtimes").toString();
                    // for(int i=0; i<onemovie.get("showtimes").size()){

                    // }
                    String res = ss.substring(ss.indexOf("time"), ss.length());

                    if (inn.equals(mmmname)) {
                        movienum++;
                        BufferedImage imgg = null;
                        try {
                            imgg = ImageIO.read(new File(pppp));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ArrayList<String> slidcedlist = new ArrayList<>();

                        for (int i = 1; i < ss.length(); i++) {
                            if (ss.charAt(i) == 'M') {
                                if (ss.charAt(i - 1) == 'A' || ss.charAt(i - 1) == 'P') {
                                    slidcedlist.add(ss.substring(i - 7, i + 7));

                                }
                            }
                        }
                        JComboBox box = new JComboBox<>(slidcedlist.toArray());

                        JLabel lblcurent = new JLabel(mmmname);
                        lblcurent.setIcon(new ImageIcon(imgg.getScaledInstance(261, 380, Image.SCALE_SMOOTH)));
                        lblcurent.setHorizontalAlignment(SwingConstants.CENTER);
                        lblcurent.setVerticalAlignment(SwingConstants.TOP);
                        lblcurent.setHorizontalTextPosition(SwingConstants.CENTER);
                        lblcurent.setVerticalTextPosition(SwingConstants.BOTTOM);

                        lblcurent.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                        lblcurent.setForeground(new java.awt.Color(229, 226, 226));
                        lblcurent.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {

                            }

                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                formMouseEntered(evt);
                            }

                            public void mouseExited(java.awt.event.MouseEvent evt) {
                                formMouseExited(evt);
                            }

                            private void formMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseEntered
                                // lblcurent.setSize(new Dimension(200, 300));
                                lblcurent.setBorder(new MatteBorder(2, 0, 0, 0, new java.awt.Color(66, 69, 73)));
                            }// GEN-LAST:event_formMouseEntered

                            private void formMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseExited
                                lblcurent.setBorder(null);
                            }// GEN-LAST:event_formMouseExited

                        });
                        lblcurent.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                // TODO Auto-generated method stub
                                JFrame moviedescriptionandpurchase = new JFrame();

                                moviedescriptionandpurchase.pack();
                                moviedescriptionandpurchase.setLocationRelativeTo(null);
                                moviedescriptionandpurchase.setBounds(300, 90, 1000, 600);
                                moviedescriptionandpurchase.setVisible(true);
                                JPanel thepanel = new JPanel();

                                JLabel panelname = new JLabel("Ticket purchase");
                                panelname.setFont(new Font("Arial", Font.PLAIN, 30));
                                panelname.setSize(300, 30);
                                panelname.setLocation(400, 30);
                                thepanel.add(panelname);
                                // movie.name
                                JLabel moviename = new JLabel(mmmname, SwingConstants.CENTER);
                                moviename.setFont(new Font("Arial", Font.PLAIN, 15));

                                JTextArea moivediscription = new JTextArea(
                                        dddd,
                                        50, 50);

                                //
                                // movie.price
                                JLabel price = new JLabel(ppppp, SwingConstants.CENTER);
                                amount = new JTextField("Enter amount:");

                                String thetime = res.substring(35, 49).toString();
                                // JLabel insidebutton1 = new JLabel("Time:" + thetime, SwingConstants.CENTER);
                                // insidebutton1.setFont(new Font("Arial", Font.PLAIN, 15));
                                // insidebutton1.addActionListener(new insidebutton1ActionListener());
                                time = new JTextField();

                                JButton insidebutton2 = new JButton("Seats layout:");
                                insidebutton2.setFont(new Font("Arial", Font.PLAIN, 15));
                                insidebutton2.addActionListener(new insidebutton2ActionListener());

                                seats = new JTextField();

                                Coupon = new JLabel("Enter your coupon:", SwingConstants.CENTER);
                                Coupon.setFont(new Font("Arial", Font.PLAIN, 15));
                                field1 = new JTextField(30);

                                creaditcardtext = new JLabel("Enter your Credit card:", SwingConstants.CENTER);
                                creditcard = new JTextField(30);
                                creaditcardtext.setFont(new Font("Arial", Font.PLAIN, 15));

                                JLabel movielabel = new JLabel("Movie name:", SwingConstants.CENTER);
                                movienameforpurchase = new JTextField();
                                movielabel.setFont(new Font("Arial", Font.PLAIN, 15));

                                insidebutton3 = new JButton("Purchase");
                                insidebutton3.addActionListener(new purchaseListener());

                                moivediscription.setLineWrap(true);
                                moivediscription.setWrapStyleWord(true);
                                moivediscription.setOpaque(false);
                                moivediscription.setEditable(false);

                                thepanel.add(moviename);
                                thepanel.add(moivediscription);
                                moviename.setSize(150, 20);
                                moviename.setLocation(340, 100);
                                moivediscription.setSize(300, 20);
                                moivediscription.setLocation(540, 100);

                                thepanel.add(movielabel);
                                thepanel.add(movienameforpurchase);
                                movielabel.setSize(150, 20);
                                movielabel.setLocation(340, 150);
                                movienameforpurchase.setSize(150, 20);
                                movienameforpurchase.setLocation(540, 150);

                                thepanel.add(price);
                                thepanel.add(amount);
                                price.setSize(150, 20);
                                price.setLocation(340, 200);
                                amount.setSize(150, 20);
                                amount.setLocation(540, 200);

                                thepanel.add(Coupon);
                                thepanel.add(field1);
                                Coupon.setSize(150, 20);
                                Coupon.setLocation(340, 250);
                                field1.setSize(150, 20);
                                field1.setLocation(540, 250);

                                thepanel.add(creaditcardtext);
                                thepanel.add(creditcard);
                                creaditcardtext.setSize(160, 20);
                                creaditcardtext.setLocation(340, 300);
                                creditcard.setSize(150, 20);
                                creditcard.setLocation(540, 300);

                                thepanel.add(box);
                                thepanel.add(time);
                                box.setSize(160, 20);
                                box.setLocation(340, 350);

                                time.setSize(150, 20);
                                time.setLocation(540, 350);

                                thepanel.add(insidebutton2);
                                thepanel.add(seats);
                                insidebutton2.setSize(150, 20);
                                insidebutton2.setLocation(340, 400);
                                seats.setSize(150, 20);
                                seats.setLocation(540, 400);

                                thepanel.add(insidebutton3);
                                insidebutton3.setSize(100, 20);
                                insidebutton3.setLocation(450, 500);

                                thepanel.setLayout(null);
                                // thepanel.setLayout(new GridLayout(2,8));

                                moviedescriptionandpurchase.add(thepanel);

                            }

                            @Override
                            public void mousePressed(MouseEvent e) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                // TODO Auto-generated method stub

                            }
                        });

                        // mo.add(lblcurent, BorderLayout.CENTER);
                        mo.pack();
                        mo.setLocationRelativeTo(null);
                        mo.setVisible(true);
                        moviepanellll.add(lblcurent);
                        moviepanellll.setBackground(new java.awt.Color(66, 69, 73));
                    }
                }

                if (movienum % 4 == 0) {
                    moviepanellll.setLayout(new GridLayout(movienum / 4, 4));
                } else {
                    moviepanellll.setLayout(new GridLayout((movienum / 4) + 1, 4));
                }

                JScrollPane pane = new JScrollPane(
                        moviepanellll, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                pane.getVerticalScrollBar().setUnitIncrement(30);
                mo.add(pane);
                pane.setBorder(null);
                // mo.add(moviepanellll);
                mo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mo.setVisible(true);
                mo.setExtendedState(JFrame.MAXIMIZED_BOTH);
                // mo.setUndecorated(true);

                //////////////////////////////////////////////////////////////////////////////////

            }
        });

    }

    public static void main(String[] args) {
        new AppGUI();
    }

}
