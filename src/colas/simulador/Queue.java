package colas.simulador;

import java.util.ArrayList;

public class Queue {
    private int startQueue;
    private int sizeQueue;
    public ArrayList<Person> list = new ArrayList<>();


    public Queue() {
        startQueue = 0;
        sizeQueue = 0;
    }

    public boolean isEmpty() {
        if (list == null || list.size() == 0) {
            return true;
        } else {
            return false;
        }

    }

    public void insert(Person newPerson) {
        list.add(newPerson);
        sizeQueue++;

    }

    public Person dequeue() {
        Person tope;
        if (!isEmpty()) {
            tope = list.get(0);
            list.remove(0);
            return tope;

        } else {
            System.out.println("No hay personas en la cola");
            return null;
        }

    }
}




