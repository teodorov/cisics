package cisics.force;

/**
 * Created by Ciprian TEODOROV on 28/02/17.
 */
public abstract class Force {
    boolean isOn = true;
    boolean isOff() {
        return !isOn;
    }
    boolean isOn() {
        return isOn;
    }
    void setOn() {
        isOn = true;
    }
    void setOff() {
        isOn = false;
    }

    public abstract void apply();
}
