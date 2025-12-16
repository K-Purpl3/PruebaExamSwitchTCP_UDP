package org.example;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

    public static void main(String[] args) {
        final int PUERTO = 5000;

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {

            System.out.println("Servidor UDP iniciado...");

            byte[] buffer = new byte[1024];

            while (true) {
                // Recibir paquete
                DatagramPacket packet =
                        new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(
                        packet.getData(), 0, packet.getLength()
                );

                if (message.equals("0")) {
                    continue; // UDP no cierra conexión
                }

                String[] parts = message.split("#");
                int option = Integer.parseInt(parts[0]);
                String result;

                switch (option) {
                    case 1:
                        result = suma(
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2]));
                        break;

                    case 2:
                        result = resta(
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2]));
                        break;

                    case 3:
                        result = multiplicacion(
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2]));
                        break;

                    case 4:
                        result = division(
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2]));
                        break;

                    case 5:
                        result = esPrimo(Integer.parseInt(parts[1]));
                        break;

                    case 6:
                        result = parImpar(Integer.parseInt(parts[1]));
                        break;

                    default:
                        result = "Opción no válida";
                }

                // Enviar respuesta
                byte[] response = result.getBytes();
                DatagramPacket responsePacket =
                        new DatagramPacket(
                                response,
                                response.length,
                                packet.getAddress(),
                                packet.getPort()
                        );

                socket.send(responsePacket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MÉTODOS DEL SERVIDOR 👇

    static String suma(int a, int b) {
        return "Resultado suma: " + (a + b);
    }

    static String resta(int a, int b) {
        return "Resultado resta: " + (a - b);
    }

    static String multiplicacion(int a, int b) {
        return "Resultado multiplicacion: " + (a * b);
    }

    static String division(int a, int b) {
        if (b == 0) return "Error: division por 0";
        return "Resultado division: " + (a / b);
    }

    static String esPrimo(int n) {
        if (n <= 1) return n + " NO es primo";
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return n + " NO es primo";
        }
        return n + " ES primo";
    }

    static String parImpar(int n) {
        return (n % 2 == 0) ? n + " es PAR" : n + " es IMPAR";
    }
}
