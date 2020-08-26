package colas.simulador;

import DataPersistence.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Logic {
    private Scanner input = new Scanner(System.in);

    public ArrayList<BankTeller> cashier = new ArrayList<>();
    ArrayList<Inventory> registry;

    public int numberQueue = 0; //con cuantas colas voy a trabajar
    public int totalQueueTime; //
    public int totalTransactionsTime;
    public int totalPeopleAttended = 0;


    Queue normalQueue = new Queue();
    Queue specialQueue = new Queue();

    Data data = new Data();

    public void QueueQuantity() {

        do {
            System.out.println("¿Cuantas colas desea utilizar 1 o 2?");
            numberQueue = input.nextInt();
            switch (numberQueue) {
                case 1:
                    numberQueue = 1;
                    break;
                case 2:
                    numberQueue = 2;
                    break;
                default:
                    numberQueue = 0;
                    System.out.println("Los unicos valores validos son: 1 o 2");
                    break;
            }
        } while (numberQueue == 0);
    }

    public void createTellers() {
        System.out.println("¿Cuantas cajas desea crear?");
        int tellerQ = input.nextInt();
        for (int i = 0; i < tellerQ; i++) {
            BankTeller teller = new BankTeller(0);
            cashier.add(teller);
        }
        System.out.println("¿Cuantas cajas nuevas en su puesto desea crear");
        int tellerSlow = input.nextInt();
        for (int i = 0; i < tellerSlow; i++) {
            BankTeller teller = new BankTeller(1); //....................
            cashier.add(teller);
        }
    }

    public void simulation() {

        if (cashier.size() == 0 || numberQueue == 0) {
            System.out.println("Ingrese el numero de colas y cajas");
        } else {
            registry = data.loadD();

            for (int i = 0; i < registry.size(); i++) {

                loadPeopleQueue(i);
                dequeuePeople(); //sacar personas de las colas para meterlas a las cajas
                if (i != 0) {
                    calculateTimes();
                }
                dequeuePeopleBankTeller();
                print(i);
            }
        }
        totalAverage();
    }

    private void print(int i) {
        int peopleInTeller = 0;

        System.out.println("");
        System.out.println("**********************************Minuto " + "[" + i + "]*********************************");
        System.out.println("--> Personas en cola normal: " + "(" + normalQueue.list.size() + ")");
        System.out.println("--> Personas en cola especial: " + "(" + specialQueue.list.size() + ")");
        for (int k = 0; k < cashier.size(); k++) {
            if (cashier.get(k).isBusy()) {
                peopleInTeller++;
            }
        }
        System.out.println("--> Personas en caja: " + "(" + peopleInTeller + ")");
        System.out.println("--> Personas atendidas: " + "(" + totalPeopleAttended + ")");
        System.out.println("*******************************************************************************");


    }

    private void dequeuePeopleBankTeller() { //sacar gente de las cajas
        for (int i = 0; i < cashier.size(); i++) {
            if (cashier.get(i).isBusy()) {
                if (cashier.get(i).getTransactTime() == cashier.get(i).getAttentionTime()) {
                    totalTransactionsTime += cashier.get(i).getAttentionTime(); //+= lo que ya tengo más lo que viene
                    cashier.get(i).setBusy(false);
                    cashier.get(i).setTransactTime(0);
                    cashier.get(i).setAttentionTime(0);
                    totalPeopleAttended = totalPeopleAttended + 1;
                }
            }
        }
    }

    private void calculateTimes() { //calcular tiempo 
        if (!normalQueue.isEmpty()) {
            for (int i = 0; i < normalQueue.list.size(); i++) {
                normalQueue.list.get(i).timeIncrease();
            }
            if (!specialQueue.isEmpty()) {
                for (int j = 0; j < specialQueue.list.size(); j++) {
                    specialQueue.list.get(j).timeIncrease();
                }
            }
        }

        for (int i = 0; i < cashier.size(); i++) {
            if (cashier.get(i).isBusy()) {
                cashier.get(i).increaseAttentionTime();
            }
        }
    }

    public void dequeuePeople() { //sacar personas para que sean atendidas en la caja
        for (int i = 0; i < cashier.size(); i++) {
            if (!cashier.get(i).isBusy()) {
                if (!specialQueue.isEmpty()) {
                    Person newPerson;
                    newPerson = specialQueue.dequeue();
                    totalQueueTime += newPerson.getTime(); //TIMMMMMMME
                    cashier.get(i).setBusy(true); //ahora la caja esta ocupada
                    double randomValue = Math.random();
                    if (cashier.get(i).getNormalBankTeller() == 0) {
                        cashier.get(i).setTransactTime(getMinutes(randomValue));
                    } else {
                        cashier.get(i).setTransactTime(getMinutes(randomValue) + 1);
                    }
                }


                if (!normalQueue.isEmpty() && !cashier.get(i).isBusy()) {
                    Person newPerson;
                    newPerson = normalQueue.dequeue();
                    totalQueueTime += newPerson.getTime();
                    cashier.get(i).setBusy(true);
                    double randomValue = Math.random();
                    if (cashier.get(i).getNormalBankTeller() == 0) {
                        cashier.get(i).setTransactTime(getMinutes(randomValue));
                    } else {
                        cashier.get(i).setTransactTime(getMinutes(randomValue) + 1);
                    }
                }
            }
        }
    }

    private int getMinutes(double value) { //arreglo
        int minutes = 0;
        if (value >= 0 || value <= 0.20)
            minutes = 1;
        if (value > 0.20 || value <= 0.40)
            minutes = 2;
        if (value > 0.40 || value <= 0.60)
            minutes = 3;
        if (value > 0.60 || value <= 0.80)
            minutes = 5;
        if (value > 0.80 || value <= 0.90)
            minutes = 8;
        if (value > 0.90 || value <= 0.95)
            minutes = 13;
        if (value > 0.95)
            minutes = 13 + (int) (13 * Math.random());
        return minutes;

    }

    private void loadPeopleQueue(int i) { // 3  2
        int normal = registry.get(i).getNormal();
        int special = registry.get(i).getSpecial();


        if (numberQueue == 1) {
            for (int l = 0; l < normal; l++) {
                Person normalPerson = new Person();
                normalQueue.insert(normalPerson);


            }
            for (int k = 0; k < special; k++) { //se le debe de dar prioridad a esta
                Person specialPerson = new Person();
                normalQueue.insert(specialPerson);

            }
        }
        if (numberQueue == 2) {
            for (int a = 0; a < normal; a++) {
                Person normalPerson = new Person();
                normalQueue.insert(normalPerson);

            }
            for (int b = 0; b < special; b++) {
                Person specialPerson = new Person();
                specialQueue.insert(specialPerson);

            }
        }
    }

    public void totalAverage() { //promedio de trámites de todas las personas
        int transact = 0;
        int totalWaitTransact = 0;
        int averageWait = 0;
        int peopleInTeller = 0;
        for (int k = 0; k < cashier.size(); k++) {
            if (cashier.get(k).isBusy()) {
                peopleInTeller++;
            }
        }

        averageWait = totalQueueTime / (specialQueue.list.size() + normalQueue.list.size() + totalPeopleAttended + peopleInTeller);
        System.out.println("");
        System.out.println("Promedio de espera de todas las personas --> " + averageWait + " min");
        System.out.println("");


            transact = totalTransactionsTime / totalPeopleAttended;
            System.out.println("Tiempo promedio de tramites --> " + transact + " min");
            System.out.println("");

            totalWaitTransact = (totalQueueTime + totalTransactionsTime) / totalPeopleAttended;
            System.out.println("Tiempo promedio total --> " + totalWaitTransact + " min");
            System.out.println("");

    }
}














