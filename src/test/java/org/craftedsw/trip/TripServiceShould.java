package org.craftedsw.trip;

import org.craftedsw.exceptions.UserNotLoggedInException;
import org.craftedsw.user.User;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class TripServiceShould {

    private static final User STRANGER = new User();
    private static final User GUEST = null;
    private static final User UNUSED_USER = null;

    TripService tripService = spy(new TripService());

    @Test
    public void returnZeroTripsWhenAUserIsNotAFriendOfTheLoggedInUser() throws Exception {
        User loggedUser = new User();
        setUser(loggedUser);
        List<Trip> trips = tripService.getTripsIfUserIsFriendOfTheLoggedInUser(STRANGER);
        assertThat(trips.size(), is(0));
    }

    @Test(expected = UserNotLoggedInException.class)
    public void throwAnExceptionWhenTheUserIsAGuest() throws Exception {
        setUser(GUEST);
        tripService.getTripsIfUserIsFriendOfTheLoggedInUser(UNUSED_USER);
    }

    private void setUser(User user) {
        doReturn(user).when(tripService).getLoggedUser();
    }
}
