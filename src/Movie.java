import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Movie {
    private static String filename;
    // I change here
    // I change to public static below
    public static JSONArray movies;

    public Movie(String filename) {
        this.filename = filename;
        this.movies = loadMovies();
    }

    private JSONArray loadMovies() {
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

    public static void saveMovies() {
        try (FileWriter file = new FileWriter(filename)) {
            file.write(movies.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getMovie(String movieName) {
        for (Object movieObj : movies) {
            JSONObject movie = (JSONObject) movieObj;
            if (movie.get("name").equals(movieName)) {
                return movie;
            }
        }
        return null;
    }

    public static void addMovie(String movieName, String showtimes2, int numTickets, String description, String path,
            String gen, String price) {
        JSONObject newMovie = new JSONObject();
        newMovie.put("name", movieName);
        newMovie.put("descriptions", description);

        JSONObject newShowtime = new JSONObject();
        newShowtime.put("time", showtimes2);
        newShowtime.put("tickets", numTickets);

        JSONArray showtimes = new JSONArray();
        showtimes.add(newShowtime);
        newMovie.put("showtimes", showtimes);

        // I add
        newMovie.put("poster", path);
        newMovie.put("genre", gen);
        newMovie.put("price", price);

        for (Object movieObj : movies) {
            JSONObject movie = (JSONObject) movieObj;
            if (movie.get("name").equals(movieName)) {
                showtimes = (JSONArray) movie.get("showtimes");
                for (Object showtimeObj : showtimes) {
                    JSONObject showtime = (JSONObject) showtimeObj;
                    if (showtime.get("time").equals(showtimes2)) {
                        int currentNumTickets = ((Long) showtime.get("tickets")).intValue();
                        showtime.put("tickets", currentNumTickets + numTickets);
                        saveMovies();
                        System.out.println("Successfully added " + numTickets + " tickets to showtime " + showtimes2
                                + " for " + movieName);
                        return;
                    }
                }
                showtimes.add(newShowtime);
                saveMovies();
                System.out.println("Successfully added showtime " + showtimes2 + " with " + numTickets
                        + " tickets for " + movieName);
                return;
            }
        }

        movies.add(newMovie);
        saveMovies();
        System.out.println("Successfully added movie " + movieName + " with showtime " + showtimes2 + " and "
                + numTickets + " tickets");
    }

    public static boolean removeMovie(String rrrr) {

        for (Object movieObj : movies) {
            JSONObject movie = (JSONObject) movieObj;
            // System.out.println("movie name");
            // System.out.println(movie.get("name"));
            // System.out.println("rrrr ");
            // System.out.println(rrrr);
            if (movie.get("name").equals(rrrr)) {
                movies.remove(movie);
                saveMovies();
                return true;
            }
        }
        return false;
    }

    public JSONObject getTickets(String movieName, String showtime) {
        JSONObject movie = getMovie(movieName);
        if (movie == null) {
            return null;
        }
        JSONArray showtimes = (JSONArray) movie.get("showtimes");
        for (Object showtimeObj : showtimes) {
            JSONObject showtimeJson = (JSONObject) showtimeObj;
            if (showtimeJson.get("time").equals(showtime)) {
                return showtimeJson;
            }
        }
        return null;
    }

    public boolean addTickets(String movieName, String showtime, int numTickets) {
        JSONObject movie = getMovie(movieName);
        if (movie == null) {
            return false;
        }
        JSONArray showtimes = (JSONArray) movie.get("showtimes");
        for (Object showtimeObj : showtimes) {
            JSONObject showtimeJson = (JSONObject) showtimeObj;
            if (showtimeJson.get("time").equals(showtime)) {
                int currentTickets = ((Long) showtimeJson.get("tickets")).intValue();
                showtimeJson.put("tickets", currentTickets + numTickets);
                saveMovies();
                return true;
            }
        }
        return false;
    }

    public static boolean removeTickets(String movieName, String showtime, int numTickets) {
        JSONObject movie = getMovie(movieName);
        if (movie == null) {
            return false;
        }
        JSONArray showtimes = (JSONArray) movie.get("showtimes");
        for (Object showtimeObj : showtimes) {
            JSONObject showtimeJson = (JSONObject) showtimeObj;
            if (showtimeJson.get("time").equals(showtime)) {
                int currentTickets = ((Long) showtimeJson.get("tickets")).intValue();
                if (currentTickets >= numTickets) {
                    showtimeJson.put("tickets", currentTickets - numTickets);
                    saveMovies();
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Movie movies = new Movie("movies.json");
        // movies.addMovie("The Matrix", "10:00 AM", 50, "A sci-fi action movie", "pp",
        // "Science-fic", "100");
        // movies.addMovie("The Matrix", "1:00 PM", 30, "A sci-fi action movie", "pp",
        // "Science-fic", "100");
        // movies.addMovie("The Matrix", "3:00 PM", 20, "A sci-fi action movie", "pp",
        // "Science-fic", "100");
        // movies.addMovie("Star Wars", "2:00 PM", 40, "A space opera movie", "pp",
        // "Science-fic", "100");
        // movies.addMovie("Star Wars", "4:00 PM", 30, "A space opera movie", "pp",
        // "Science-fic", "100");
        // movies.addMovie("Jurassic Park", "11:00 AM", 25, "A science fiction adventure
        // movie", "pp", "Science-fic",
        // "100");
        // movies.addMovie("Jurassic Park", "2:00 PM", 35, "A science fiction adventure
        // movie", "pp", "Science-fic",
        // "100");
        // movies.addMovie("Jurassic Park", "5:00 PM", 20, "A science fiction adventure
        // movie", "pp", "Science-fic",
        // "100");
    }

}
