package guru.bonacci.mcp_client;

import java.time.Duration;
import java.util.List;

import org.springframework.ai.mcp.customizer.McpSyncClientCustomizer;
import org.springframework.stereotype.Component;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomMcpSyncClientCustomizer implements McpSyncClientCustomizer {

	@Override
	public void customize(String serverConfigurationName, McpClient.SyncSpec spec) {

		// Customize the request timeout configuration
		spec.requestTimeout(Duration.ofSeconds(30));

		// Sets the root URIs that this client can access.
//        spec.roots(roots);

//		 Sets a custom sampling handler for processing message creation requests.
//    spec.sasampling((CreateMessageRequest messageRequest) -> {
//        // Handle sampling
//        CreateMessageResult result = ...
//        return result;
//    });

		// Adds a consumer to be notified when the available tools change, such as tools
		// being added or removed.
		spec.toolsChangeConsumer((List<McpSchema.Tool> tools) -> {
			// Handle tools change
			System.out.println("Tools changed, and now what...");
			tools.forEach(System.out::println);
		});

		// Adds a consumer to be notified when the available resources change, such as
		// resources
		// being added or removed.
		spec.resourcesChangeConsumer((List<McpSchema.Resource> resources) -> {
			// Handle resources change
			System.out.println("Resources changed, and not what...");
			resources.forEach(System.out::println);
		});

		// Adds a consumer to be notified when the available prompts change, such as
		// prompts
		// being added or removed.
		spec.promptsChangeConsumer((List<McpSchema.Prompt> prompts) -> {
			// Handle prompts change
			System.out.println("Prompts changed, and now what...");
			prompts.forEach(System.out::println);
		});

		// Adds a consumer to be notified when logging messages are received from the
		// server.
		spec.loggingConsumer((McpSchema.LoggingMessageNotification log) -> {
			// Handle log messages
			System.out.println("log the logs... " + log);
		});
	}
}