import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nAfter assign (new instance): " + assigned);
        System.out.println("\nAfter escalate (new instance): " + escalated);
        System.out.println("\nOriginal unchanged: " + t);

        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nERROR: list was mutable!");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nTags list is unmodifiable — immutability confirmed.");
        }
        System.out.println("Final escalated ticket: " + escalated);
    }
}
