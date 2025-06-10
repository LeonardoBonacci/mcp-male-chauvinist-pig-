package guru.bonacci.mcp_client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;

@Configuration
public class JustAnotherConfig {

	@Bean
	McpSyncClient mcpSyncClient() {
		var mcp = McpClient
				.sync(HttpClientSseClientTransport.builder("http://localhost:8081").build())
				.build();
		mcp.initialize();
		return mcp;
	}
}
