import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for file i/o operations
 */
public class FileManager {
    private static final String DATA_FOLDER_PATH = "./data/";

    /**
     * Load countries from countries.csv file located in data folder
     * Skip first line which is column names
     */
    public static List<Country> loadCountries() throws IOException {
        List<Country> countries = new ArrayList<>();
        File file = new File("data/countries.csv");
        BufferedReader br = new BufferedReader(new FileReader(file));

        // skip the column names
        String line = br.readLine();

        while ((line = br.readLine()) != null) {
            Country country = Country.parseFrom(line);
            countries.add(country);
        }
        br.close();
        return countries;
    }

    /**
     * Create a file with the given name under data folder, and store the given
     * countries list to the file.
     * If the fileName already exists the current file will be overridden
     */
    public static void saveCountries(List<Country> countries, String fileName) throws IOException {
        String filePath = DATA_FOLDER_PATH + fileName;
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Country country : countries) {
                writer.println(country.parseTo());
            }
        }
    }
}

