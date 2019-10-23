package masterpeer;

public class Break {

    public int intervalLength;
    public int breakLength;

    public Break(int intervalLength, int breakLength) {
        this.intervalLength = intervalLength;
        this.breakLength = breakLength;
    }

    public int getIntervalLength() {
        return intervalLength;
    }

    public void setIntervalLength(int intervalLength) {
        this.intervalLength = intervalLength;
    }

    public int getBreakLength() {
        return breakLength;
    }

    public void setBreakLength(int breakLength) {
        this.breakLength = breakLength;
    }

}
