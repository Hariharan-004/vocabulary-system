package com.vocabulary.vocabulary_system;

import org.springframework.beans.factory.annotation.Value; // ADD THIS
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service

public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    public String getDefinition(String word,String field,String level)
    {
        try{
            String prompt=buildPrompt(word,field,level);
            String requestBody=buildRequestBody(prompt);

            HttpClient client=HttpClient.newHttpClient();
            HttpRequest request=HttpRequest.newBuilder().uri(URI.create(apiUrl+"?key="+apiKey))
                    .header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseResponse(response.body());
        }
        catch (Exception e)
        {
            return "Definition not available at the moment";
        }
    }

    private String buildPrompt(String word,String field,String level)
    {
        return String.format("Explain the word '%s' in the context of '%s "+
                "at %s level. Give a clear definition and "+
                "2 example sentences.Keep it concise.",word,field,level);

    }
    private String buildRequestBody(String prompt)
    {
        return """
                {
                  "contents":[
                     {
                        "parts":[
                            {
                               "text":"%s"
                             }
                          ]
                       }
                      ]
                }            
                """.formatted(prompt.replace("\"","'"));
    }
    private String parseResponse(String responseBody) {
        try {
            System.out.println("GEMINI RESPONSE: " + responseBody);

            int textIndex = responseBody.indexOf("\"text\":");
            if (textIndex == -1) return "Could not get definition";

            int quoteStart = responseBody.indexOf("\"", textIndex + 7) + 1;
            int quoteEnd = responseBody.indexOf("\"", quoteStart);

            while (quoteEnd > 0 && responseBody.charAt(quoteEnd - 1) == '\\') {
                quoteEnd = responseBody.indexOf("\"", quoteEnd + 1);
            }

            if (quoteStart <= 0 || quoteEnd <= 0) {
                return "Could not parse definition";
            }

            return responseBody.substring(quoteStart, quoteEnd)
                    .replace("\\n", "\n")
                    .replace("\\\"", "\"")
                    .replace("\\'", "'");

        } catch (Exception e) {
            System.out.println("Parse error: " + e.getMessage());
            return "Could not parse response";
        }
    }

}
