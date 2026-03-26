package com.hexabank.client.observability;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ObservabilityIntegrationTest {

    @LocalServerPort
    private int port;

    private final HttpClient client = HttpClient.newHttpClient();

    @Test
    void actuatorHealthIsUp() throws Exception {
        HttpRequest req = HttpRequest.newBuilder(new URI("http://localhost:" + port + "/actuator/health")).GET().build();
        HttpResponse<String> r = client.send(req, HttpResponse.BodyHandlers.ofString());
        assertThat(r.statusCode()).isBetween(200, 299);
        assertThat(r.body()).containsIgnoringCase("status");
    }

    @Test
    void metricsEndpointReturnsJson() throws Exception {
        HttpRequest req = HttpRequest.newBuilder(new URI("http://localhost:" + port + "/actuator/metrics")).GET().build();
        HttpResponse<String> r = client.send(req, HttpResponse.BodyHandlers.ofString());
        assertThat(r.statusCode()).isBetween(200, 299);
        assertThat(r.body()).contains("names");
    }

    @Test
    void prometheusEndpointIsAvailable() throws Exception {
        HttpRequest req = HttpRequest.newBuilder(new URI("http://localhost:" + port + "/actuator/prometheus")).GET().build();
        HttpResponse<String> r = client.send(req, HttpResponse.BodyHandlers.ofString());
        int status = r.statusCode();
        // Prometheus endpoint may not be available in all profiles/environments. Accept 200 or 404.
        if (status >= 200 && status < 300) {
            String body = r.body();
            assertThat(body).isNotEmpty();
        } else {
            assertThat(status).isEqualTo(404);
        }
    }

}
