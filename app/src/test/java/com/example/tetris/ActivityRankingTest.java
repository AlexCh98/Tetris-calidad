package com.example.tetris;

import android.os.FileUtils;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ActivityRankingTest {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void cargarDatos() throws IOException {
        ActivityRanking activityRanking = new ActivityRanking();
        DatosRanking datosRanking = new DatosRanking("40IMG", "El Pepe", "Clasico", 330, null);
        DatosRanking datosRanking2 = new DatosRanking("59IMG", "Marina", "Clasico", 270, null);
        List<DatosRanking> listExpected = new ArrayList<>();
        listExpected.add(datosRanking);
        listExpected.add(datosRanking2);

        final File file = tempFolder.newFile("test.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("40IMG/El Pepe/Clasico/330");
        writer.write("59IMG/Marina/Clasico/270");
        writer.close();

        activityRanking.cargarDatos(file);
        assertThat(activityRanking.getListaDatos(), is(listExpected));
    }

}