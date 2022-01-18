package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  private static final String STATUS_RESOLVED = "resolved"; //2
  private static final String STATE_CLOSED = "closed"; //3

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public String getStateByIssueId(int id) throws IOException {
    String issueById = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", id))).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(issueById);
    JsonElement jsonIssues = parsed.getAsJsonObject().get("issues");
    Set<Issue> setIssues = new Gson().fromJson(jsonIssues, new TypeToken<Set<Issue>>() {
    }.getType());
    Issue selectedIssue = setIssues.iterator().next();
    String stateIssue = selectedIssue.getState();
    System.out.println("state_name = " + stateIssue);
    return stateIssue;
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    String issueStatus = getStateByIssueId(issueId);
    if (issueStatus.equals(STATE_CLOSED) || issueStatus.equals(STATUS_RESOLVED)) {
      return true;
    }
    return false;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}