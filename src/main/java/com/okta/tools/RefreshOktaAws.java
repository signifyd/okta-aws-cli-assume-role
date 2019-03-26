package com.okta.tools;

import java.time.Instant;
import com.amazonaws.services.securitytoken.model.AssumeRoleWithSAMLResult;
import com.amazonaws.services.securitytoken.model.AssumedRoleUser;

/**
 * A simple class to allow users to refresh their okta/aws session without
 * running any of the wrapper classes supplied by okta.
 */
public class RefreshOktaAws {

    public RefreshOktaAws() { }
    
    public void run(String[] args) throws Exception {
        if (args.length > 0 && "logout".equals(args[0])) {
            OktaAwsCliEnvironment oktaEnvironment = OktaAwsConfig.loadEnvironment();
            OktaAwsCliAssumeRole role = OktaAwsCliAssumeRole.withEnvironment(oktaEnvironment);
            role.logoutSession();
            System.out.println("You have been logged out");
            System.exit(0);
            return;
        }

        OktaAwsCliEnvironment oktaEnvironment = OktaAwsConfig.loadEnvironment();
        OktaAwsCliAssumeRole role = OktaAwsCliAssumeRole.withEnvironment(oktaEnvironment);
        AssumeRoleWithSAMLResult awsSamlRole = (AssumeRoleWithSAMLResult)AWSCredentialsUtil.getAWSCredential();
        String awsProfile = awsSamlRole.getAssumedRoleUser().getAssumedRoleId();
        System.out.println("export AWS_PROFILE=" + awsProfile + "_source");

    }
    /**
     * If the session has expired, prompts the user for credentials
     * Echoes the current profile or the one that was refreshed.
     *
     * Accepts a logout command to end the current session.
     */
    public static void main(String[] args) throws Exception {
        RefreshOktaAws refreshOktaAws = new RefreshOktaAws();
        refreshOktaAws.run(args);
    }
}
