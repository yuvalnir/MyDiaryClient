package il.ac.hit.mvc.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class GsonDateDeSerializer implements JsonDeserializer<Date> {

    public SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss a");
    public SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            String j = json.getAsJsonPrimitive().getAsString();
            return parseDate(j);
        } catch (ParseException e) {
            System.out.println("We are going to ignore the exception for now so it would continue creating the page.. ");
//            throw new JsonParseException(e.getMessage(), e);
            return new Date();
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        if (dateString != null && dateString.trim().length() > 0) {
            try {
                return format1.parse(dateString);
            } catch (ParseException pe) {
                return format2.parse(dateString);
            }
        } else {
            return null;
        }
    }

}
