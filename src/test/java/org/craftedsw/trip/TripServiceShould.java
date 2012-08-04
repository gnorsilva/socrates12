package org.craftedsw.trip;

import org.craftedsw.exceptions.UserNotLoggedInException;
import org.craftedsw.user.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class TripServiceShould {

    private static final User STRANGER = new User();
    private static final User GUEST = null;
    private static final User UNUSED_USER = null;

    TripService tripService = spy(new TripService());

    @Test
    public void returnZeroTripsWhenAUserIsNotAFriendOfTheLoggedInUser() throws Exception {
        User loggedUser = new User();
        setLoggedUser(loggedUser);
        List<Trip> trips = tripService.getTripsIfUserIsFriendOfTheLoggedInUser(STRANGER);
        assertThat(trips.size(), is(0));
    }

    @Test(expected = UserNotLoggedInException.class)
    public void throwAnExceptionWhenTheUserIsAGuest() throws Exception {
        setLoggedUser(GUEST);
        tripService.getTripsIfUserIsFriendOfTheLoggedInUser(UNUSED_USER);
    }

    @Test
    public void return2TripsWhenAUserWhoHas2TripsIsAFriendOfTheLoggedInUser() throws UserNotLoggedInException {
        User loggedUser = new User();
        setLoggedUser(loggedUser);

        User friend = new User();
        friend.addFriend(loggedUser);
        friend.addTrip(new Trip());
        friend.addTrip(new Trip());

        List<Trip> trips = new ArrayList<Trip>();
        trips.add(new Trip());
        trips.add(new Trip());
        doReturn(trips).when(tripService).findTripsForUser(friend);

        List<Trip> friendTrips = tripService.getTripsIfUserIsFriendOfTheLoggedInUser(friend);
        assertThat(friendTrips.size(), is(2));
    }


    private void setLoggedUser(User user) {
        doReturn(user).when(tripService).getLoggedUser();
    }
}
