public class LightsPanel implements SmartClassroomDevice, Switchable, Dimmable {
    @Override public void powerOn()  {}
    @Override public void powerOff() { System.out.println("Lights OFF"); }
    @Override public void setBrightness(int pct) { System.out.println("Lights set to " + pct + "%"); }
}
