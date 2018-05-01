package entity;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {
   public static void main(String args[]){
   
      ObjectMapper mapper = new ObjectMapper();
      String jsonString = "{\"id\":100, \"title\":\"Article\"}";
      
      try{
         Article article = mapper.readValue(jsonString, Article.class);
         
         System.out.println(article);
         
         jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(article);
         
         System.out.println(jsonString);
      }
      catch (JsonParseException e) { e.printStackTrace();}
      catch (JsonMappingException e) { e.printStackTrace(); }
      catch (IOException e) { e.printStackTrace(); }
   }
}
