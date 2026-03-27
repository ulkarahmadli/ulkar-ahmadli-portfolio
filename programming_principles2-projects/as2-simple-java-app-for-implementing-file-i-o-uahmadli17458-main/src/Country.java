/**
 * Country class containing country data
 */
public class Country {
    private String id;
    private String countryName;
    private String continent;
    private Double population;
    private Double IMF_GDP;
    private Double UN_GDP;
    private Double IMF_GDP_per_capita;
    private Double UN_GDP_per_capita;

    /**
     * Constructor to construct Country instance with the given fields.
     * UN_GDP_per_capita is not given. We calculate it from UN_GDP and population
     */
    public Country(
            String id,
            String countryName,
            String continent,
            Double population,
            Double IMF_GDP,
            Double UN_GDP,
            Double IMF_GDP_per_capita
    ) {
        this.id = id;
        this.countryName = countryName;
        this.continent = continent;
        this.population = population;
        this.IMF_GDP = IMF_GDP;
        this.UN_GDP = UN_GDP;
        this.IMF_GDP_per_capita = IMF_GDP_per_capita;
        if (UN_GDP != null && population != null && population > 0) {
            this.UN_GDP_per_capita = UN_GDP / population;
        }
    }

    /**
     * Getter fields to provide encapsulation
     */
    public String getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getContinent() {
        return continent;
    }

    public Double getPopulation() {
        return population;
    }

    public Double getIMF_GDP() {
        return IMF_GDP;
    }

    public Double getUN_GDP() {
        return UN_GDP;
    }

    public Double getIMF_GDP_per_capita() {
        return IMF_GDP_per_capita;
    }

    public Double getUN_GDP_per_capita() {
        return UN_GDP_per_capita;
    }

    /**
     * Parse country data from a comma-separated string to create an instance of Country
     */
    public static Country parseFrom(String countryData) {
        String[] fields = countryData.split(",");
        String id = fields[1];
        String countryName = fields[2];
        String continent = fields[3];
        Double population = Double.parseDouble(fields[4]);
        Double IMF_GDP = Double.parseDouble(fields[5]);
        Double UN_GDP = Double.parseDouble(fields[6]);
        Double IMF_GDP_per_capita = Double.parseDouble(fields[7]);

        return new Country(
                id,
                countryName,
                continent,
                population,
                IMF_GDP,
                UN_GDP,
                IMF_GDP_per_capita
        );
    }

    /**
     * Parse the current country instance to a comma-separated string
     */
    public String parseTo() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f,%.2f",
                id, countryName, continent, population, IMF_GDP, UN_GDP, IMF_GDP_per_capita, UN_GDP_per_capita);
    }

    /**
     * Parse the given country instance to a comma-separated string
     */
    public static String parseTo(Country countryInstance) {
        return countryInstance.parseTo();
    }
}
