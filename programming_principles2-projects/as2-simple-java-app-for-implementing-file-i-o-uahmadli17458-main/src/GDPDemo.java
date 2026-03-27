import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates all the functionality of the program
 */
public class GDPDemo {

    public static void main(String[] args) {
        try {
            List<Country> countries = FileManager.loadCountries();

            // Sorting
            sort(countries, "continent", "asc");
            sort(countries, "countryName", "asc");
            FileManager.saveCountries(countries, "sorted_by_continent_and_country.csv");

            sort(countries, "population", "desc");
            FileManager.saveCountries(countries, "sorted_by_population_desc.csv");

            // Filtration
            List<Country> oceaniaCountries = filterByContinent(countries, "Oceania");
            FileManager.saveCountries(oceaniaCountries, "oceania_countries.csv");

            List<Country> gdpPerCapitaRangeCountries = filterByPerCapita(countries, 400000.0, 500000.0);
            FileManager.saveCountries(gdpPerCapitaRangeCountries, "un_gdp_per_capita_range_countries.csv");

        } catch (IOException e) {
            // In case of exception print the error message
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void sort(List<Country> countries, String fieldName, String order) {
        FieldComparator comparator = new FieldComparator(fieldName, order);
        countries.sort(comparator);
    }

    private static List<Country> filterByContinent(List<Country> countries, String continent) {
        // We create a new list as we don't want to make changes to the original one
        List<Country> filteredCountries = new ArrayList<>();
        for (Country country : countries) {
            if (country.getContinent().equalsIgnoreCase(continent)) {
                filteredCountries.add(country);
            }
        }
        return filteredCountries;
    }

    private static List<Country> filterByPerCapita(List<Country> countries, double lower, double upper) {
        // We create a new list as we don't want to make changes to the original one
        List<Country> filteredCountries = new ArrayList<>();
        for (Country country : countries) {
            double perCapita = country.getUN_GDP_per_capita();
            if (perCapita >= lower && perCapita <= upper) {
                filteredCountries.add(country);
            }
        }
        return filteredCountries;
    }
}
