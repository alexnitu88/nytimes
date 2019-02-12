package me.alexnitu.list;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import me.alexnitu.R;
import me.alexnitu.helper.EspressoIdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ArticleListScreenTest {

    @Rule
    public ActivityTestRule<ArticleListActivity> activityTestRule = new ActivityTestRule<>(ArticleListActivity.class);

    @Before
    public void setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.INSTANCE);
    }

    @After
    public void destroy() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.INSTANCE);
    }

    @Test
    public void clickArticle_showDetails() {
        onView(withId(R.id.article_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.article_details_container)).check(matches(isDisplayed()));
    }

    @Test
    public void homePressInDetails_goesBackToList() {
        onView(withId(R.id.article_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        onView(withId(R.id.article_rv)).check(matches(isDisplayed()));
    }
}
