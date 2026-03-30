public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        reg.getFirst(Switchable.class).powerOn();
        reg.getFirst(InputConnectable.class).connectInput("HDMI-1");
        reg.getFirst(Dimmable.class).setBrightness(60);
        reg.getFirst(TemperatureControllable.class).setTemperatureC(24);
        System.out.println("Attendance scanned: present=" +
                reg.getFirst(AttendanceScannable.class).scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        for (Switchable s : reg.getAll(Switchable.class)) {
            s.powerOff();
        }
    }
}
