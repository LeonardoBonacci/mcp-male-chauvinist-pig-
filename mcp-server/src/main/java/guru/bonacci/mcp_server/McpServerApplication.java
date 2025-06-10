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
	MethodToolCallbackProvider methodToolCallbackProvider(Lucerna lucerna, Aetatis aetatis) {
		return MethodToolCallbackProvider
				.builder()
				.toolObjects(lucerna, aetatis)
				.build();
	}
}

@Component
class Lucerna {
	
	@Tool(description =  "find the secret of a specific person")
	String whatIsYourSecret(@ToolParam(description = "the name of the person") String name) {
		System.out.println("I'm glad you asked for your secret " + name);
		return 
				"This is " + name + "'s secret: "
				+
				"""
				While mainstream scholars accept Shakespeare of Stratford-upon-Avon as the author, 
				It's long been whispered that others (like Francis Bacon or Edward de Vere) may have written the works.
				""";
	}
}

@Component
class Aetatis {
	
	@Tool(description =  "find the age of a specific person")
	String whatIsYourAge(@ToolParam(description = "the name of the person") String name) {
		System.out.println("I'm glad you asked for your age " + name);
		return 
				"This is " + name + "'s age: 180";
	}
}
