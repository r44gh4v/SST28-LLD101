public class Main {
    public static void main(String[] args) {
        System.out.println("====== Pen LLD Testing ======\n");

        // 1. Refillable Pen Testing
        System.out.println("--- Testing Refillable Pen ---");
        Ink blueGelInk = new Ink(Color.BLUE, InkType.GEL);
        Nib standardNib = new Nib(0.5);
        Refill parkerRefill = new Refill(blueGelInk, standardNib, 20.0);

        RefillablePen ballpointPen = new RefillablePen("Parker", parkerRefill);
        
        // Try writing closed
        ballpointPen.write("Hello World"); 
        
        // Start pen and write
        ballpointPen.start();
        ballpointPen.write("Hello World");
        
        // Burn through the ink
        ballpointPen.write("This string is way longer than what is remaining"); // Length 46, but only 9 remaining
        System.out.println("Ink remaining: " + ballpointPen.getRefill().getInkLevel());

        // Reload using a substitutive Refill mechanism
        System.out.println("\nSwapping cartridge...");
        Refill newRedRefill = new Refill(new Ink(Color.RED, InkType.BALLPOINT), standardNib, 80.0);
        ballpointPen.changeRefill(newRedRefill);
        ballpointPen.write("This string should write successfully now!");

        ballpointPen.close();


        // 2. Fountain Pen Testing
        System.out.println("\n--- Testing Fountain Pen ---");
        Ink blackFountainInk = new Ink(Color.BLACK, InkType.FOUNTAIN);
        Nib fountainNib = new Nib(0.7);
        FountainPen fountainPen = new FountainPen("Lamy", blackFountainInk, fountainNib, 100.0);

        fountainPen.start();
        
        // Test empty write (fountain pens start empty in this LLD)
        fountainPen.write("Fountain pen requires manual filling first.");

        // Refill tank (additive)
        fountainPen.refillInk(50.0);
        fountainPen.write("Wrote successfully!");
        System.out.println("Tank level: " + fountainPen.getCurrentLevel());

        fountainPen.close();
    }
}
