package colas.simulador;

public class BankTeller {
    public boolean busy = false;
    private int transactTime = 0; //math random
    private int attentionTime = 0;//aumenta en 1
    private int normalBankTeller =0;




    public BankTeller(int typeTeller) {
        this.normalBankTeller = typeTeller;
        this.transactTime = 0;
        this.attentionTime = 0;
        this.busy = false;
    }
    public int getNormalBankTeller() {
        return normalBankTeller;
    }

    public void setNormalBankTeller(int normalBankTeller) {
        this.normalBankTeller = normalBankTeller;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public int getTransactTime() {
        return transactTime;
    }

    public void setTransactTime(int transactTime) {
        this.transactTime = transactTime;
    }

    public int getAttentionTime() {
        return attentionTime;
    }

    public void setAttentionTime(int attentionTime) {
        this.attentionTime = attentionTime;
    }

    public void increaseAttentionTime(){
        attentionTime++;
    }

}

