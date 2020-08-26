package colas.simulador;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class UserInterface {
    private PrintStream output = new PrintStream(System.out);
    private Scanner input = new Scanner(System.in);
    Logic logic = new Logic();

    public void showMenu() throws IOException {

        System.out.println("");
        System.out.println("******************************************************+*********** MENU ************************************************************************");
        System.out.println("");
        System.out.println("Elija una de las siguientes opciones:");
        System.out.println("");
        System.out.println("-----------------------1. Ingresar la cantidad de cajas-------------------------");
        System.out.println("-----------------------2. Ingresar cantidad de colas----------------------------");
        System.out.println("-----------------------3. Iniciar simulacion------------------------------------");
        System.out.println("-----------------------4. Salir-------------------------------------------------");
        System.out.println("");
        System.out.println("************************************************************************************************************************************************");
        System.out.println("");

   int option=input.nextInt();

        switch (option) {
            case 1:
                logic.createTellers();
                break;
            case 2:
                logic.QueueQuantity();
                break;
            case 3:
                logic.simulation();
                break;
            case 4:
                System.out.println("**************************SIMULACION FINALIZADA***********************************");
                break;
            default:
                System.out.println("***Opcion incorrecta***");

        }
    }
}
