package me.alexnitu.helper;

import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResource {

    private static final String IDLING_RESOURCE_NAME = "espresso_idling_resource";

    public static final CountingIdlingResource INSTANCE =
            new CountingIdlingResource(IDLING_RESOURCE_NAME);

    private EspressoIdlingResource() {
    }
}
