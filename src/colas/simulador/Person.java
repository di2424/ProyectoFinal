package colas.simulador;

public class Person {

    private int queueTime =0;

    public int getTime() {
        return queueTime;
    }

    public void setTime(int time) {
        this.queueTime = time;
    }

    public void timeIncrease(){
        queueTime++;
    }


}
