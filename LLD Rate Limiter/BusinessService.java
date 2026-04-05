public class BusinessService {
    private final RateLimiter rateLimiter;

    public BusinessService(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    public void processRequest(String userId, boolean requiresExternalCall) {
        System.out.println("Processing business logic for user: " + userId);
        
        if (!requiresExternalCall) {
            return;
        }
        
        if (rateLimiter.allowRequest(userId)) {
            System.out.println("External API called successfully for: " + userId);
        } else {
            System.out.println("Rate limit exceeded. External API call rejected for: " + userId);
        }
    }
}