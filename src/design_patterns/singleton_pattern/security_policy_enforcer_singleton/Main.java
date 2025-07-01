package design_patterns.singleton_pattern.security_policy_enforcer_singleton;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Demonstrating Singleton Security Policy Enforcement Point ---");

        // --- Step 1: Get the single instance of the SecurityPolicyEnforcer ---
        // This will trigger its initialization and loading of default policies.
        SecurityPolicyEnforcer enforcer1 = SecurityPolicyEnforcer.getInstance();
        System.out.println("\nEnforcer 1 initialized. Current Policies:");
        enforcer1.getCurrentPolicies().forEach(System.out::println);

        // --- Step 2: Get another instance (it will be the same object) ---
        SecurityPolicyEnforcer enforcer2 = SecurityPolicyEnforcer.getInstance();
        System.out.println("\nIs enforcer1 the same instance as enforcer2? " + (enforcer1 == enforcer2));
        System.out.println("enforcer1 hashcode: " + enforcer1.hashCode());
        System.out.println("enforcer2 hashcode: " + enforcer2.hashCode());
        // Hash codes being identical confirms they are the same object in memory.

        System.out.println("\n--- Performing Access Checks ---");

        // Test cases based on default policies:
        // admin can read config
        checkAccess(enforcer1, "admin", "/config", "read"); // Should be GRANTED
        // admin can write config
        checkAccess(enforcer1, "admin", "/config", "write"); // Should be GRANTED
        // user cannot write config (explicit deny)
        checkAccess(enforcer1, "user", "/config", "write");  // Should be DENIED
        // user can read data
        checkAccess(enforcer1, "user", "/data", "read");     // Should be GRANTED
        // user cannot delete data (no allow rule, no explicit deny)
        checkAccess(enforcer1, "user", "/data", "delete");   // Should be DENIED
        // guest cannot do anything (explicit deny all)
        checkAccess(enforcer1, "guest", "/any_resource", "any_action"); // Should be DENIED
        // unknown user accessing unknown resource (no matching rule)
        checkAccess(enforcer1, "stranger", "/private", "access"); // Should be DENIED

        System.out.println("\n--- Modifying Policies ---");
        // Add a new policy: user can write to data
        enforcer2.addPolicy("allow:user:/data:write");
        System.out.println("Policies after adding new rule:");
        enforcer1.getCurrentPolicies().forEach(System.out::println); // enforcer1 also sees the change

        // Test the new policy
        checkAccess(enforcer1, "user", "/data", "write"); // Should now be GRANTED

        // Remove a policy
        enforcer2.removePolicy("deny:guest:*:*");
        System.out.println("Policies after removing rule:");
        enforcer1.getCurrentPolicies().forEach(System.out::println);

        // Test after removal
        checkAccess(enforcer1, "guest", "/any_resource", "any_action"); // Should now be DENIED (as there's still no allow)
        // Note: The example policy logic is simple. In a real system, rule order and default denies are critical.
    }

    private static void checkAccess(SecurityPolicyEnforcer enforcer, String user, String resource, String action) {
        System.out.println("\nChecking: User='" + user + "', Resource='" + resource + "', Action='" + action + "'");
        enforcer.checkAccess(user, resource, action);
    }
}