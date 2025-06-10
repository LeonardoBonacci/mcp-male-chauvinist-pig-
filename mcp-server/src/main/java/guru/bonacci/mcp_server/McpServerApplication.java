package guru.bonacci.mcp_server;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class McpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpServerApplication.class, args);
	}

	@Bean
	MethodToolCallbackProvider methodToolCallbackProvider(Lucerna lucerna) {
		return MethodToolCallbackProvider
				.builder()
				.toolObjects(lucerna)
				.build();
	}
}

@Component
class Lucerna {
	
	@Tool(description =  "find the secret of a specific person")
	String whatIsYourSecret(@ToolParam(description = "the name of the person") String name) {
		System.out.println("I'm glad you asked " + name);
		return "43";
	}
}
