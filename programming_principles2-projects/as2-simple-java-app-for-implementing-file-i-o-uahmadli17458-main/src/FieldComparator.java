import java.util.Comparator;

/**
 * This class is used in sorting to compare two countries based on a given
 * fieldName and order (asc/desc).
 */
public class FieldComparator implements Comparator<Country> {
    private final String fieldName;
    private final boolean ascending;

    public FieldComparator(String fieldName, String order) {
        this.fieldName = fieldName;
        this.ascending = order.equalsIgnoreCase("asc");
    }

    /**
     * compare method will return a negative integer, zero, or a positive integer
     * as the first argument is less than, equal to, or greater than the second.
     * If order is desc, we negate the result.
     */
    @Override
    public int compare(Country country1, Country country2) {
        int result;
        switch (fieldName) {
            case "id":
                result = country1.getId().compareTo(country2.getId());
                break;
            case "countryName":
                result = country1.getCountryName().compareTo(country2.getCountryName());
                break;
            case "continent":
                result = country1.getContinent().compareTo(country2.getContinent());
                break;
            case "population":
                result = Double.compare(country1.getPopulation(), country2.getPopulation());
                break;
            case "IMF_GDP":
                result = Double.compare(country1.getIMF_GDP(), country2.getIMF_GDP());
                break;
            case "UN_GDP":
                result = Double.compare(country1.getUN_GDP(), country2.getUN_GDP());
                break;
            case "UN_GDP_per_capita":
                result = Double.compare(country1.getUN_GDP_per_capita(), country2.getUN_GDP_per_capita());
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
        return ascending ? result : -result;
    }
}
