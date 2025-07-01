package design_patterns.singleton_pattern.security_policy_enforcer_singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections; // For unmodifiable list

/**
 * The SecurityPolicyEnforcer class implements the Singleton pattern.
 * It acts as a central component responsible for loading and enforcing security policies.
 * It uses the Initialization-on-demand holder idiom for lazy, thread-safe initialization.
 */
public class SecurityPolicyEnforcer {

    // Internal list to store policy rules.
    // In a real system, this would be loaded from a database, file, or a dedicated policy service.
    private final List<String> policies;

    // Private constructor to prevent direct instantiation
    private SecurityPolicyEnforcer() {
        System.out.println("SecurityPolicyEnforcer: Initializing policy enforcement system...");
        this.policies = new ArrayList<>();
        // Load some default policies or fetch them from a configuration source
        policies.add("allow:admin:/config:read");
        policies.add("allow:admin:/config:write");
        policies.add("deny:user:/config:write");
        policies.add("allow:user:/data:read");
        policies.add("deny:guest:*:*"); // Example: guest cannot access anything
        System.out.println("SecurityPolicyEnforcer: Loaded " + policies.size() + " default policies.");
    }

    // Static nested class that holds the single instance of SecurityPolicyEnforcer
    private static class EnforcerHolder {
        private static final SecurityPolicyEnforcer INSTANCE = new SecurityPolicyEnforcer();
    }

    // Public static method to get the single instance
    public static SecurityPolicyEnforcer getInstance() {
        return EnforcerHolder.INSTANCE;
    }

    /**
     * Checks if a given user has permission to perform an action on a resource based on loaded policies.
     * Simplified logic for demonstration.
     *
     * @param user The user attempting the action.
     * @param resource The resource being accessed.
     * @param action The action being performed (e.g., "read", "write", "delete").
     * @return True if the action is allowed, false otherwise.
     */
    public boolean checkAccess(String user, String resource, String action) {
        String request = action + ":" + user + ":" + resource;
        System.out.println("Enforcer: Checking access for request: " + request);

        // Simple policy evaluation: prioritize deny over allow, specific over general
        boolean isAllowed = false;
        boolean isDeniedExplicitly = false;

        for (String policy : policies) {
            String[] parts = policy.split(":"); // e.g., "allow:admin:/config:read"
            String policyEffect = parts[0];     // "allow" or "deny"
            String policyUser = parts[1];
            String policyResource = parts[2];
            String policyAction = parts[3];

            // Check if policy matches the request
            boolean userMatch = policyUser.equals("*") || policyUser.equals(user);
            boolean resourceMatch = policyResource.equals("*") || policyResource.equals(resource);
            boolean actionMatch = policyAction.equals("*") || policyAction.equals(action);

            if (userMatch && resourceMatch && actionMatch) {
                if ("deny".equals(policyEffect)) {
                    isDeniedExplicitly = true;
                    // In a real system, more complex rule ordering/prioritization would be needed
                    break; // Found a deny rule, stop checking
                } else if ("allow".equals(policyEffect)) {
                    isAllowed = true;
                }
            }
        }

        if (isDeniedExplicitly) {
            System.out.println("Access DENIED for " + user + " to " + resource + " for action " + action + " (explicit deny).");
            return false;
        }
        if (isAllowed) {
            System.out.println("Access GRANTED for " + user + " to " + resource + " for action " + action + ".");
            return true;
        }

        System.out.println("Access DENIED for " + user + " to " + resource + " for action " + action + " (no matching allow).");
        return false;
    }

    /**
     * Adds a new policy rule.
     * In a real system, this would involve validating the rule and persistence.
     * @param policyRule The policy string (e.g., "allow:user:resource:action").
     */
    public void addPolicy(String policyRule) {
        if (policies.add(policyRule)) {
            System.out.println("Enforcer: Added new policy: " + policyRule);
        }
    }

    /**
     * Removes an existing policy rule.
     * @param policyRule The policy string to remove.
     */
    public void removePolicy(String policyRule) {
        if (policies.remove(policyRule)) {
            System.out.println("Enforcer: Removed policy: " + policyRule);
        } else {
            System.out.println("Enforcer: Policy not found: " + policyRule);
        }
    }

    /**
     * Gets an unmodifiable list of current policies.
     * @return An unmodifiable list of policy strings.
     */
    public List<String> getCurrentPolicies() {
        return Collections.unmodifiableList(policies);
    }
}