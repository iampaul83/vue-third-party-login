package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubService {
  private static final String CLIENT_ID = "2262130954437574af16";
  private static final String CLIENT_SECRET = "daca6eec78fed4b970ddf8d5f9522db6242531e0";
  @Autowired private ObjectMapper objectMapper;

  private final OkHttpClient client = new OkHttpClient();
  public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

  public String getAccessToken(String code) throws IOException {
    Map<String, String> request = new HashMap<>();
    request.put("client_id", CLIENT_ID);
    request.put("client_secret", CLIENT_SECRET);
    request.put("code", code);
    Map<String, Object> response =
        this.post(
            "https://github.com/login/oauth/access_token",
            objectMapper.writeValueAsString(request),
            null);
    return (String) response.get("access_token");
  }

  public Object getUser(String accessToken) throws IOException {
    Map<String, Object> response = this.get("https://api.github.com/user", accessToken);
    return response;
  }

  private Map<String, Object> get(String url, String accessToken) throws IOException {
    Builder requestBuilder = new Builder().url(url).get().addHeader("Accept", "application/json");
    if (accessToken != null) {
      requestBuilder.addHeader("Authorization", "token " + accessToken);
    }
    Request request = requestBuilder.build();
    try (Response response = client.newCall(request).execute()) {
      String responseJson = response.body().string();
      return objectMapper.readValue(responseJson, Map.class);
    }
  }

  private Map<String, Object> post(String url, String json, String accessToken) throws IOException {
    RequestBody body = RequestBody.create(JSON, json);
    Builder requestBuilder =
        new Builder().url(url).post(body).addHeader("Accept", "application/json");
    if (accessToken != null) {
      requestBuilder.addHeader("Authorization", "token " + accessToken);
    }
    Request request = requestBuilder.build();
    try (Response response = client.newCall(request).execute()) {
      String responseJson = response.body().string();
      return objectMapper.readValue(responseJson, Map.class);
    }
  }
}
