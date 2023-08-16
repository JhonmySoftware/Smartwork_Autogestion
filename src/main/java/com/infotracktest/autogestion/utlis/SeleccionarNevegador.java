package com.infotracktest.autogestion.utlis;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SeleccionarNevegador {

    public static Properties properties;

    public static void SeleccionarNevegador() throws IOException {
        properties = new Properties();
        properties.load(new FileReader("serenity.properties"));
    }

}
