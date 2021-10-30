package util;

public class MyNumber {
    private Integer intValue;

    public MyNumber(Integer intValue) {
        this.intValue = intValue;
    }
    public void Iteration(){
        this.intValue++;
    }

    public void setIntValue(Integer value) {
        this.intValue = value;
    }

    public Integer getIntValue() {
        return this.intValue;
    }

    @Override
    public String toString() {
        return Integer.toString(intValue);
    }
}