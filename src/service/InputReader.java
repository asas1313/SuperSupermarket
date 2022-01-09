package service;

import exceptions.PayNotAcceptedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() {
        String readString = "";
        try {
            readString = reader.readLine().toUpperCase();

            if (readString.equals("STOP")) {
                System.out.println("Thank You for your shopping. Good bye!");
                System.exit(0);
            }

        } catch (IOException e) {
            System.out.println("Could not read Your input.");
        }

        return readString;
    }

    public static float readFloat() {
        String readString = readLine();

        float readFloat;
        try {
            readFloat = Float.parseFloat(readString);
        } catch (NumberFormatException nfe) {
            throw new PayNotAcceptedException();
        }

        return readFloat;
    }
}
