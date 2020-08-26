package colas.simulador;

import java.io.IOException;

public class Main {
    public static void main(String [] args) throws IOException {
        UserInterface UI = new UserInterface();
        int option=0;

        do{
            UI.showMenu();

        }while(option != 4);


    }
}
