package com.example.tetris;

import android.widget.TextView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RankingBehaviorTest {

    private String textBeforeNombre;
    private String textBeforeModo;
    private String textBeforePuntuacion;

    @Rule
    public ActivityTestRule<ActivityRanking> activityRanking
            = new ActivityTestRule<>(ActivityRanking.class);

    /*@Rule
    public IntentsTestRule<A> intentsTestRule =
            new IntentsTestRule<>(MyActivity.class);*/

    @Before
    public void initValidString() {
        TextView nombre = (TextView) activityRanking.getActivity().findViewById(R.id.textViewRanking1);
        TextView modo = (TextView) activityRanking.getActivity().findViewById(R.id.textViewRanking2);
        TextView puntuacion = (TextView) activityRanking.getActivity().findViewById(R.id.textViewRanking3);

        textBeforeNombre = nombre.getText().toString();
        textBeforeModo = modo.getText().toString();
        textBeforePuntuacion = puntuacion.getText().toString();
    }

    @Test
    public void changeText_sameActivity() {

        onView(withId(R.id.botonSubir)).perform(click());
        onView(withId(R.id.botonBajar)).perform(click());

        onView(withId(R.id.textViewRanking1)).check(matches(withText(textBeforeNombre)));
        onView(withId(R.id.textViewRanking2)).check(matches(withText(textBeforeModo)));
        onView(withId(R.id.textViewRanking3)).check(matches(withText(textBeforePuntuacion)));

    }

    @Test
    public void launchMainActivity() {

        ViewActions.closeSoftKeyboard();
        onView(withId(R.id.botonVolverEnRanking)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.botonVolverEnRanking)).perform(click());

        assertTrue(activityRanking.getActivity().isFinishing());

    }

}