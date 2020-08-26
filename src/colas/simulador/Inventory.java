package colas.simulador;

public class Inventory {
    private int normal;
    private int special;

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "normal=" + normal +
                ", special=" + special +
                '}';
    }
}
