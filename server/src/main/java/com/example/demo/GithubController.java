package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubController {
  @Autowired private GithubService githubService;

  private String accessToken = null;

  @PostMapping("/access_token")
  public Object getAccessToken(@RequestBody Map<String, String> request) throws Exception {
    String code = request.get("code");
    accessToken = githubService.getAccessToken(code);
    return null;
  }

  @GetMapping("/username")
  public Object getUserName() throws Exception {
    return githubService.getUser(accessToken);
  }
}
