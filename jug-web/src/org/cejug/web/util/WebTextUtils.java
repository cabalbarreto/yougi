package org.cejug.web.util;

import java.util.Date;
import java.util.StringTokenizer;

import org.cejug.entity.City;
import org.cejug.entity.Country;
import org.cejug.entity.Province;
import org.cejug.util.TextUtils;

public final class WebTextUtils extends TextUtils {

	/** This method replaces every line break in the text by a html paragraph.
     *  Empty lines are ignored. It returns a text that appears formatted in a html page. */
    public static String convertLineBreakToHTMLParagraph(String str) {
    	if(str == null)
    		return null;
    	
		StringBuilder formattedStr = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(str, "\n");
		String token;
		while(st.hasMoreTokens()) {
			token = st.nextToken().trim();
			if(!token.isEmpty()) {
				formattedStr.append("<p>");
				formattedStr.append(token);
				formattedStr.append("</p>");
			}
		}
		
		return formattedStr.toString();
    }
    
    public static String getFormattedDate(Date date) {
    	if(date == null)
    		return "";
    	
    	ResourceBundle rb = new ResourceBundle();
    	return getFormattedDate(date, rb.getMessage("formatDate"));
    }
    
    public static String getFormattedTime(Date time) {
    	if(time == null)
    		return "";
    	
    	ResourceBundle rb = new ResourceBundle();
    	return getFormattedTime(time, rb.getMessage("formatTime"), "GMT-3");
    }
    
    public static String getFormattedDateTime(Date dateTime) {
    	if(dateTime == null)
    		return "";
    	
    	ResourceBundle rb = new ResourceBundle();
    	return getFormattedDateTime(dateTime, rb.getMessage("formatDateTime"), "GMT-3");
    }
    
    public static String printAddress(String address, Country country, Province province, City city, String postalCode) {
        StringBuilder fullAddress = new StringBuilder();
        String commaSeparator = ", ";
        if(address != null || !address.isEmpty())
            fullAddress.append(address);
        
        if(city != null) {
            if(!fullAddress.toString().isEmpty())
                fullAddress.append(commaSeparator);
            
            fullAddress.append(city.getName());
        }
        
        if(province != null) {
            if(!fullAddress.toString().isEmpty())
                fullAddress.append(commaSeparator);
            
            fullAddress.append(province.getName());
        }
        
        if(country != null) {
            if(!fullAddress.toString().isEmpty())
                fullAddress.append(" - ");
            
            fullAddress.append(country.getName());
        }
        
        if(postalCode != null) {
            if(!fullAddress.toString().isEmpty())
                fullAddress.append(".");
            ResourceBundle rb = new ResourceBundle();
            fullAddress.append(" ");
            fullAddress.append(rb.getMessage("postalCode"));
            fullAddress.append(": ");
            fullAddress.append(country.getName());
        }
        
        return fullAddress.toString();
    }
}