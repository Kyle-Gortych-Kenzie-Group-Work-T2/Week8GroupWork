package com.kenzie.groupactivity.amazonscout;

import java.util.Collections;
import java.util.List;

/**
 * Computes a round trip route (of {@code Navigation}s), given
 * the first half of the trip.
 */
public class RoundTripGenerator {
    /**
     * Computes a round trip route that starts with the provided Navigations.
     *
     * The returned List contains the {@code outboundSteps},
     * as well as the navigation steps to return back to the starting
     * point.
     *
     * Note that at the end of following the round trip route,
     * we end up pointed in the opposite direction from the starting
     * orientation.
     *
     * @param outboundSteps The "out" portion of the "out and back"
     *                      round trip; will not be modified
     * @return A *new* List with the full round trip sequence of
     *         Navigation steps, beginning with a copy of the {@code outboundSteps}
     */
    public List<NavigationStep> generateRoundTrip(List<NavigationStep> outboundSteps) {
        List<NavigationStep> roundTrip = new ArrayList<>();
        //append outbound steps and turn around.
        roundTrip.addAll(outboundSteps);

        roundTrip.addAll(NavigationStep.turnAround());

        List<NavigationStep> reversedSteps = NavigationStep.reversedSteps(outboundSteps);
        for(NavigationStep navigationStep : reversedSteps) {
            //add this to roundtrip steps list
            roundTrip.add(NavigationStep.reverseNavigationStep(navigationStep));
        }
        return roundTrip;
    }
}
