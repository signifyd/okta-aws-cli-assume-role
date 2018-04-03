package com.okta.tools;

import java.time.Instant;

/*
 * Prompts the user for credentials if needed and then echoes
 * the profile that was refreshed.
 * 
 * Accepts a logout command to end the current session.
 */

public class RefreshOktaAws {
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