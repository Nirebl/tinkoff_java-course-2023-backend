package edu.java.scrapper.clients.github;

import edu.java.scrapper.clients.WebScrapperClient;

public class GitHubClient extends WebScrapperClient {
    public GitHubClient(String baseUrl) {
        super(baseUrl);
    }

    public ActivityItemDTO[] getActivities(String owner, String repo, TimePeriod period) {
        return super.retrieve(
            ActivityItemDTO[].class,
            String.format(
                "/repos/%s/%s/activity?time_period=%s",
                owner,
                repo,
                period
            )
        ).block();
    }
}
