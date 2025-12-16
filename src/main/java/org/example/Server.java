package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            System.out.println("Servidor iniciado...");

            Socket client = serverSocket.accept();
            System.out.println("Cliente conectado");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(
                    client.getOutputStream(), true);

            String message;

            while ((message = in.readLine()) != null) {

                if (message.equals("0")) {
                    out.println("Conexion cerrada");
                    break;
                }

                String[] parts = message.split("#");
                int option = Integer.parseInt(parts[0]);
                String result;

                switch (option) {
                    case 1:
                        result = suma(
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2])
                        );
                        break;

                    case 2:
                        result = resta(
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2])
                        );
                        break;

                    case 3:
                        result = multiplicacion(
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2])
                        );
                        break;

                    case 4:
                        result = division(
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2])
                        );
                        break;

                    case 5:
                        result = esPrimo(Integer.parseInt(parts[1]));
                        break;

                    case 6:
                        result = parImpar(Integer.parseInt(parts[1]));
                        break;

                    case 7:
                        result = cienPrimos();
                        break;

                    case 8:
                        result = adivinaNumero(Integer.parseInt(parts[1]));
                        break;

                    default:
                        result = "Opción no válida";
                }

                out.println(result);
            }

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MÉTODOS DEL SERVIDOR



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

    static String cienPrimos() {
        StringBuilder sb = new StringBuilder("Numeros primos: ");

        for (int i = 0; i < 100; i++) {
            int n = (int)(Math.random() * 100) + 1;
            if (esPrimoNum(n)) {
                sb.append(n).append(" ");
            }
        }
        return sb.toString(); // UNA SOLA LÍNEA
    }


    static boolean esPrimoNum(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static String adivinaNumero(int intento) {
        int secreto = (int)(Math.random() * 10) + 1;
        if (intento == secreto) {
            return "Correcto El numero era " + secreto;
        } else {
            return "Incorrecto El numero era " + secreto;
        }
    }

}
