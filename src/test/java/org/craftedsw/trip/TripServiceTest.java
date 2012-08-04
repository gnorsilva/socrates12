package org.craftedsw.trip;

import org.craftedsw.user.User;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class TripServiceTest {

    @Test
    public void testName() throws Exception {
        User bob = new User();
        TripService tripService = spy(new TripService());
        doReturn(bob).when(tripService).getLoggedUser();
        List<Trip> trips = tripService.getTripsByUser(bob);
        assertThat(trips.size(), is(0));
    }
}
