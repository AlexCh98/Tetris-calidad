package com.example.tetris.test;

import android.os.Bundle;

import androidx.test.runner.MonitoringInstrumentation;

import com.example.tetris.BuildConfig;

import java.io.File;

import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberInstrumentationCore;


/**
 * The CucumberOptions annotation is mandatory for exactly one of the classes in the test project.
 * Only the first annotated class that is found will be used, others are ignored. If no class is
 * annotated, an exception is thrown. This annotation does not have to placed in runner class
 */
@CucumberOptions(
        features = "features",
        glue = "com.example.tetris.test"
)
public class Instrumentation extends MonitoringInstrumentation {
    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

        String tags = BuildConfig.TEST_TAGS;
        if (!tags.isEmpty()) {
            arguments.putString("tags", tags.replaceAll(",", "--").replaceAll("\\s", ""));
        }

        instrumentationCore.create(arguments);
        start();
    }

    @Override
    public void onStart() {
        super.onStart();

        waitForIdleSync();
        instrumentationCore.start();
    }
}