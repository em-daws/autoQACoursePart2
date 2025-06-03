package homework.lection30;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APITest {

    @Test
    public void logoutTest() throws IOException, InterruptedException, URISyntaxException {
        String expectedBody = "{\"status\":\"ok\"}";
        SoftAssert softAssert = new SoftAssert();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://qauto.forstudy.space/api/auth/logout"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        softAssert.assertEquals(response.body(), expectedBody);
        softAssert.assertEquals(response.statusCode(), 200);

        softAssert.assertAll();
    }
}
