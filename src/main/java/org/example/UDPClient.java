package org.example;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) {

        final int PUERTO = 5000;

        try (
                DatagramSocket socket = new DatagramSocket();
                Scanner sc = new Scanner(System.in)
        ) {

            InetAddress serverAddress =
                    InetAddress.getByName("127.0.0.1");

            int option;

            do {
                System.out.println("\n--- MENU UDP ---");
                System.out.println("1. Suma");
                System.out.println("2. Resta");
                System.out.println("3. Multiplicación");
                System.out.println("4. División");
                System.out.println("5. ¿Es primo?");
                System.out.println("6. ¿Par o impar?");
                System.out.println("0. Salir");

                option = sc.nextInt();

                String message;

                if (option >= 1 && option <= 4) {
                    System.out.print("Numero 1: ");
                    int n1 = sc.nextInt();
                    System.out.print("Numero 2: ");
                    int n2 = sc.nextInt();
                    message = option + "#" + n1 + "#" + n2;

                } else if (option == 5 || option == 6) {
                    System.out.print("Numero: ");
                    int n = sc.nextInt();
                    message = option + "#" + n;

                } else if (option == 0) {
                    message = "0";
                } else {
                    continue;
                }

                // Enviar paquete
                byte[] data = message.getBytes();
                DatagramPacket packet =
                        new DatagramPacket(
                                data,
                                data.length,
                                serverAddress,
                                PUERTO
                        );

                socket.send(packet);

                if (option == 0) break;

                // Recibir respuesta
                byte[] buffer = new byte[1024];
                DatagramPacket responsePacket =
                        new DatagramPacket(buffer, buffer.length);

                socket.receive(responsePacket);

                String response = new String(
                        responsePacket.getData(),
                        0,
                        responsePacket.getLength()
                );

                System.out.println(response);

            } while (option != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
