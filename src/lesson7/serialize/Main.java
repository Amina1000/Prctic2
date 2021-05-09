package lesson7.serialize;

import java.io.*;

/**
 * homework lesson7.serialize
 *
 * @author Amina
 * 09.05.2021
 */
public class Main {
    public static void main(String[] args) {
        String fileName = "C:\\JavaPractic\\Prctic2\\src\\lesson7\\serialize\\test.ser";
        SerializeEx serialize_1_out = new SerializeEx("example_1", 1);
        SerializeEx serialize_2_out = new SerializeEx("example_2", 2);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject(serialize_1_out);
            objectOutputStream.writeObject(serialize_2_out);

        } catch (IOException e) {
            e.printStackTrace();
        }
        SerializeEx serialize_1_in;
        SerializeEx serialize_2_in;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(fileName)));
            serialize_1_in = (SerializeEx) objectInputStream.readObject();
            serialize_2_in = (SerializeEx) objectInputStream.readObject();
            System.out.println(serialize_1_in);
            System.out.println(serialize_2_in);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
