package AirCheck;

import java.net.*;
import java.io.*;
import org.json.*;

public class AirCheck {
  public static void main(String[] args) {
    try {
      String apiUrl = "https://api.waqi.info/feed/here/?token="; // Replace with the actual API URL
      String token = "c3b583783fe8d1b1f0021d2f904250f1b683a99c";

      URL url = new URL(apiUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Authorization", "Bearer " + token);

      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
          content.append(inputLine);
        }
        in.close();

        JSONObject json = new JSONObject(content.toString());
        JSONObject data = json.getJSONObject("data");
        int aqi = data.getInt("aqi");

        System.out.println("Air Quality Index (AQI): " + aqi);
      } else {
        System.out.println("Error: Unable to retrieve data. Response code: " + responseCode);
      }
    } catch (Exception e) {
      System.out.println("Error: Unable to retrieve data. " + e.getMessage());
    }
  }
}

