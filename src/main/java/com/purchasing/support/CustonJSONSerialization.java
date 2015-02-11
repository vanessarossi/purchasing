package com.purchasing.support;

import com.google.gson.*;

import javax.enterprise.context.Dependent;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author vanessa
 */
@Dependent
public class CustonJSONSerialization implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private final SimpleDateFormat iso8601Format;


    public CustonJSONSerialization() {
        this.iso8601Format = new SimpleDateFormat("dd/MM/yyyy");

    }

    @Override
    public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
        String dateString = iso8601Format.format(date);
         return new JsonPrimitive(dateString);

    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
         try {
             return iso8601Format.parse(json.getAsString());
        } catch (ParseException e) {
             throw new JsonSyntaxException(json.getAsString(), e);

        }

    }
}
