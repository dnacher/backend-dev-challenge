package com.directa24.main.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

  private static final String TOTAL_PAGES_STRING = "total_pages";
  private static final String DATA = "data";
  private static final String DIRECTOR = "Director";

  public static void main(String[] args) {
    List<String> directors = getDirectors(3);
    System.out.println(String.join("\n", directors));
  }

  /*
   * Complete the 'getDirectors' function below.
   *
   * The function is expected to return a List<String>.
   * The function accepts int threshold as parameter.
   *
   * URL
   * https://directa24-movies.mocklab.io/api/movies/search?page=<pageNumber>
   * URL updated:
   * https://eroninternational-movies.wiremockapi.cloud/api/movies/search?page=<pageNumber>
   */
  public static List<String> getDirectors(int threshold) {
    int currentPage = 1;
    int totalPages = 1;
    Map<String, Integer> directorCount = new HashMap<>();
    String baseUrl = "https://eroninternational-movies.wiremockapi.cloud/";
    String urlString = baseUrl + "api/movies/search?page=" + currentPage;

    try {
      while (currentPage <= totalPages) {
        HttpURLConnection conn = getCallConnection(urlString);

        if (conn.getResponseCode() != 200) {
          throw new ChallengeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        StringBuilder response = getStringBuilder(conn);
        conn.disconnect();

        JSONObject jsonResponse = new JSONObject(response.toString());
        totalPages = jsonResponse.getInt(TOTAL_PAGES_STRING);
        JSONArray movies = jsonResponse.getJSONArray(DATA);

        IntStream.range(0, movies.length())
            .mapToObj(movies::getJSONObject)
            .map(movie -> movie.getString(DIRECTOR))
            .forEach(
                director -> directorCount.put(director, directorCount.getOrDefault(director, 0) + 1));
        currentPage++;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return getOrderedListByDirector(threshold, directorCount);
  }

  private static List<String> getOrderedListByDirector(int threshold, Map<String, Integer> directorCount) {
    return directorCount.entrySet().stream()
            .filter(entry -> entry.getValue() > threshold)
            .map(Map.Entry::getKey)
            .sorted()
            .collect(Collectors.toList());
  }

  private static StringBuilder getStringBuilder(HttpURLConnection conn) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
    StringBuilder response = new StringBuilder();
    String output;
    while ((output = br.readLine()) != null) {
      response.append(output);
    }
    return response;
  }

  public static HttpURLConnection getCallConnection(String urlString) throws IOException {
    URL url = new URL(urlString);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");
    return conn;
  }
}
