class SmartSpeaker implements Switchable,Connectable,Volume{
    private boolean isOn = false;
    private int volume = 35; // Default brightness level
    private boolean isConnected = false;

    // Switchable methods
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("SmartSpeaker is turned ON.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("SmartSpeaker is turned OFF.");
    }

    // Adjustable methods
    @Override
    public void increaseVol() {
        if (volume < 100) {
            volume += 10;
            System.out.println("Volume increased to " + volume + ".");
        } else {
            System.out.println("Volume is already at maximum.");
        }
    }

    @Override
    public void decreaseVol() {
        if (volume > 0) {
            volume -= 10;
            System.out.println("volume decreased to " + volume + ".");
        } else {
            System.out.println("volume is already at minimum.");
        }
    }

    // Connectable methods
    @Override
    public void connect() {
        isConnected = true;
        System.out.println("SmartSpeaker is connected to the network.");
    }

    @Override
    public void disconnect() {
        isConnected = false;
        System.out.println("SmartSpeaker is disconnected from the network.");
    }

}
class Fan implements Switchable, Adjustable{
    private boolean isOn = false;
    private int speed = 1; // Default speed level

    // Switchable methods
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Fan is turned ON.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Fan is turned OFF.");
    }

    // Adjustable methods
    @Override
    public void increase() {
        if (speed < 5) {
            speed += 1;
            System.out.println("speed increased to " + speed + ".");
        } else {
            System.out.println("speed is already at maximum.");
        }
    }

    @Override
    public void decrease() {
        if (speed > 0) {
            speed -= 1;
            System.out.println("Speed decreased to " + speed + ".");
        } else {
            System.out.println("Speed is already at minimum.");
        }
    }
}

public class SmartHome {
    public static void main(String[] args){
        SmartSpeaker sp= new SmartSpeaker();
        sp.turnOn();
        sp.connect();
        sp.increaseVol();
        sp.decreaseVol();
        sp.disconnect();
        sp.turnOff();
        
        Fan fan = new Fan();
        fan.turnOn();
        fan.increase();
        fan.increase();
        fan.increase();
        fan.increase();
        fan.increase();
        fan.decrease();
        fan.decrease();
        fan.decrease();
        fan.decrease();
        fan.decrease();
        fan.decrease();
        fan.turnOff();
    }
}