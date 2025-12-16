package org.example;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try (
                Socket socket = new Socket("127.0.0.1", 5000);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);
                Scanner sc = new Scanner(System.in)
        ) {

            int option;

            do {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Suma");
                System.out.println("2. Resta");
                System.out.println("3. Multiplicación");
                System.out.println("4. División");
                System.out.println("5. ¿Es primo?");
                System.out.println("6. ¿Par o impar?");
                System.out.println("7. Generar 100 numeros y mostrar primos");
                System.out.println("8. Adivina el numero (1-10)");
                System.out.println("0. Salir");

                option = sc.nextInt();

                if (option >= 1 && option <= 4) {
                    System.out.print("Numero 1: ");
                    int n1 = sc.nextInt();
                    System.out.print("Numero 2: ");
                    int n2 = sc.nextInt();

                    out.println(option + "#" + n1 + "#" + n2);

                } else if (option == 5 || option == 6) {
                    System.out.print("Numero: ");
                    int n = sc.nextInt();
                    out.println(option + "#" + n);

                } else if (option == 7) {
                    out.println("7");
                } else if (option == 8) {
                    System.out.print("Adivina el numero (1-10): ");
                    int intento = sc.nextInt();
                    out.println("8#" + intento);
                } else if (option == 0) {
                    out.println("0");
                    break;
                }

                String response = in.readLine();
                System.out.println(response);

            } while (option != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
