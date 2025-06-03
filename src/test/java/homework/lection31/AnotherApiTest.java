package homework.lection31;

import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AnotherApiTest {

    @Test
    public void responseContainsIdAndCorrespondingTitleTest() throws IOException, InterruptedException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        int searchedId = 1;
        String searchedTitle = "Audi";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://qauto.forstudy.space/api/cars/brands"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        softAssert.assertEquals(response.statusCode(), 200);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());
        JsonNode dataArray = root.get("data");
        boolean bodyContainsExpectedIdAndTitle = false;

        for (JsonNode carBrandObject : dataArray) {
            int id = carBrandObject.get("id").asInt();
            String title = carBrandObject.get("title").asText();

            if (id == searchedId && searchedTitle.equals(title)) {
                bodyContainsExpectedIdAndTitle = true;
                break;
            }
        }

        softAssert.assertTrue(bodyContainsExpectedIdAndTitle);
        softAssert.assertAll();
    }
}
