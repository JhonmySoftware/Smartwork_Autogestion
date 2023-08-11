package com.infotracktest.autogestion.utlis;

public class DatosRandom {
    private static int min_num;
    private static long max_num;
    private static int num_rand;

    public static int getMin_num() {
        return min_num;
    }

    public static void setMin_num(int min_num) {
        DatosRandom.min_num = min_num;
    }

    public static long getMax_num() {
        return max_num;
    }

    public static void setMax_num(long max_num) {
        DatosRandom.max_num = max_num;
    }

    public static int getNum_rand() {
        return num_rand;
    }

    public static void setNum_rand(int num_rand) {
        DatosRandom.num_rand = num_rand;
    }

    public static int numDocumentoAleatorio() {
        setMin_num(1000000000);
        setMax_num(9999999999L);

        // Cálculo del número aleatorio dentro del rango y solo positivo
        long range = getMax_num() - getMin_num() + 1;
        long positiveRandom = (long) (Math.random() * range) + getMin_num();
        int positiveRandomInt = (int) positiveRandom;
        if (positiveRandomInt < 0) {
            positiveRandomInt = -positiveRandomInt;
        }
        System.out.println("El número random generado es: " + positiveRandomInt);
        return positiveRandomInt;
    }



}

