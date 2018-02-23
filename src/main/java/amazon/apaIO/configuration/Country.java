package amazon.apaIO.configuration;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ArrayUtils;

public class Country {
    /**
     * Possible countries
     * Important for the requestendpoints
     */
    private static String[] countryList = {"de", "com", "co.uk", "ca", "fr", "co.jp", "it", "cn", "es", "in"};
    /**
     * Gets all possible countries
     */
    public static String[] getCountries()
    {
        return Country.countryList ;
    }
    /**
     * Checks if the given value is a valid country
     */
    public static boolean isValidCountry(String country, boolean exception)
    {
        boolean isValid = false ;
        if (ArrayUtils.contains(Country.countryList, country.toLowerCase() ))
        	isValid = true ;
        else {
        	if (exception) {
	        	IllegalArgumentException e = new IllegalArgumentException(
	        			"Invalid Country-Code: "+country+"! Possible Country-Codes: "
	        			+StringUtils.join(Country.countryList, ", ")) ;
	        	throw e;
        	}
        }
        return isValid;
    }

}
