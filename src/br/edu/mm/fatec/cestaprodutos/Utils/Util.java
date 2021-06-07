package br.edu.mm.fatec.cestaprodutos.Utils;

import java.util.Scanner;

public class Util {
    public static Integer scanInteger() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public static Integer scanInteger(String message) {
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        return in.nextInt();
    }

    public static String scanString() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static String scanString(String message) {
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        return in.nextLine();
    }

    public static Double scanDouble(String message) {
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        return in.nextDouble();
    }

    public static Double scanDouble() {
        Scanner in = new Scanner(System.in);
        return in.nextDouble();
    }
}
