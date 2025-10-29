package dev.marko.aiexceptiondetection;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class AiService {

    public static String analyze(String exceptionText) throws IOException {
        String prompt = "Analyze this Java exception and suggest a fix:\\n" + exceptionText;

        String apiKey = System.getenv("OPENAI_API_KEY");
        URL url = new URL("https://api.openai.com/v1/chat/completions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setDoOutput(true);

        String jsonInput = "{ \"model\": \"gpt-4o-mini\", \"messages\": [{\"role\":\"user\",\"content\":\"" + prompt + "\"}] }";

        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonInput.getBytes());
        }
        catch (Exception ex) {
            return "Error calling AI service: " + ex.getMessage();
        }

        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "No response from AI";
        }
        catch (Exception ex) {
            return "Error calling AI service: " + ex.getMessage();
        }
    }
}
