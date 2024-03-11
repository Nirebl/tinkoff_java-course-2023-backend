package edu.java.scrapper.clients;

import edu.java.scrapper.clients.github.GitHubClient;
import edu.java.scrapper.clients.stackoverflow.StackOverflowClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {
    @Bean("github")
    public GitHubClient createGithubClient() {
        return new GitHubClient("https://api.github.com/");
    }

    @Bean("stackoverflow")
    public StackOverflowClient createStackOverflowClient() {
        return new StackOverflowClient("https://api.stackexchange.com/");
    }
}
