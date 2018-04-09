package com.okta.tools;

import java.time.Instant;

/**
 * A simple class to allow users to refresh their okta/aws session without
 * running any of the wrapper classes supplied by okta.
 */
public class RefreshOktaAws {

    private RefreshOktaAws() { }

    /**
     * If the session has expired, prompts the user for credentials
     * Echoes the current profile or the one that was refreshed.
     *
     * Accepts a logout command to end the current session.
     */
    public static void main(String[] args) throws Exception {
        if (args.length > 0 && "logout".equals(args[0])) {
        OktaAwsConfig.createAwscli().logoutSession();
        System.out.println("You have been logged out");
        System.exit(0);
        return;
    }

    String profileName = OktaAwsConfig.createAwscli().run(Instant.now());
    System.out.println("export AWS_PROFILE=" + profileName);
    }
}
