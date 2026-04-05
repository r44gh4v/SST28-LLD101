public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter fixedLimiter = new FixedWindowCounterRateLimiter(5, 60000);
        BusinessService fixedService = new BusinessService(fixedLimiter);
        
        System.out.println("====== Testing with Fixed Window Algorithm ======");
        simulateTraffic(fixedService);

        RateLimiter slidingLimiter = new SlidingWindowCounterRateLimiter(5, 60000);
        BusinessService slidingService = new BusinessService(slidingLimiter);

        System.out.println("\n====== Testing with Sliding Window Algorithm ======");
        simulateTraffic(slidingService);
    }

    private static void simulateTraffic(BusinessService service) {
        String tenantId = "T1";
        
        service.processRequest(tenantId, false);
        
        for (int i = 1; i <= 6; i++) {
            service.processRequest(tenantId, true);
        }
    }
}