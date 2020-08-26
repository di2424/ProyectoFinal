package DataPersistence;

import colas.simulador.Inventory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
    ArrayList<Inventory> registry = new ArrayList<>();

    public ArrayList<Inventory> loadD() {
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader("simulacion.txt"));
            String line = bufferedReader.readLine();


            while (line != null) {
                Inventory inventory = new Inventory();
                String[] data = line.split(",");
                inventory.setNormal(Integer.parseInt(data[0]));
                inventory.setSpecial(Integer.parseInt(data[1]));
                line = bufferedReader.readLine();
                registry.add(inventory);
            }
            bufferedReader.close();

            return registry;
        } catch (IOException e) {
            return null;
        }


    }
}
