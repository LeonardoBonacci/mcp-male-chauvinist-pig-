package guru.bonacci.mcp_client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.modelcontextprotocol.client.McpSyncClient;

@SpringBootApplication
public class McpClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(McpClientApplication.class, args);
	}

	
	private final ChatClient chatClient;

	public McpClientApplication(ChatClient.Builder builder, McpSyncClient mcpSyncClient) {
		this.chatClient = builder
					  .defaultToolCallbacks(new SyncMcpToolCallbackProvider(mcpSyncClient))
						.build();
	}
	
	@Override
	public void run(String... args) throws Exception {
		var response = chatClient
        .prompt()
        .user("what is the secret of Mira Caldwell, and what is her age?")
        .call()
        .content();
		System.out.println(response);
	}
}