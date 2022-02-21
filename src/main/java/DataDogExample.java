import com.datadog.api.v1.client.ApiClient;
import com.datadog.api.v1.client.ApiException;
import com.datadog.api.v1.client.Configuration;
import com.datadog.api.v1.client.api.EventsApi;
import com.datadog.api.v1.client.model.EventAlertType;
import com.datadog.api.v1.client.model.EventCreateRequest;
import com.datadog.api.v1.client.model.EventCreateResponse;
import com.datadog.api.v1.client.model.EventPriority;

import java.util.ArrayList;
import java.util.HashMap;

public class DataDogExample {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        HashMap<String, String> serverVariables = new HashMap<>();
        serverVariables.put("site", "datadoghq.eu");
        defaultClient.setServerVariables(serverVariables);

        HashMap<String, String> secrets = new HashMap<>();
        secrets.put("apiKeyAuth", "<Enter Your Key Here>");
        defaultClient.configureApiKeys(secrets);
        EventsApi apiInstance = new EventsApi(defaultClient);

        EventCreateRequest body =
                new EventCreateRequest()
                        .title("Testing Datadog Events")
                        .text("This is a test message.")
                        .priority(EventPriority.LOW)
                        .alertType(EventAlertType.INFO)
                        .tags(
                                new ArrayList<>() {
                                    {
                                        add("app:test-app");
                                    }
                                });

        try {
            EventCreateResponse result = apiInstance.createEvent(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling EventsApi#createEvent");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
