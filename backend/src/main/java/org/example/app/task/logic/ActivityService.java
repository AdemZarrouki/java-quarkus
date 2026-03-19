package org.example.app.task.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.openapi.quarkus.ollama_api_yaml.api.DefaultApi;
import org.openapi.quarkus.ollama_api_yaml.model.QueryLlmRequest;

@ApplicationScoped
public class ActivityService {

  @Inject
  @RestClient
  DefaultApi defaultApi;

  public String getRandomActivity() {
    QueryLlmRequest request = new QueryLlmRequest();
    request.setModel("llama3.2");
    request.setStream(false);
    request.setKeepAlive("10m");
    request.setPrompt(
        "Give me exactly one random item (containing maximal 5-6 words) which I can add to my ToDo list and return only this item without any additional text.");
    return defaultApi.queryLlm(request).getResponse();
  }

}
