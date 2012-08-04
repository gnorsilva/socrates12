package org.craftedsw.trip;

import org.craftedsw.exceptions.UserNotLoggedInException;
import org.craftedsw.user.User;
import org.craftedsw.user.UserSession;

import java.util.ArrayList;
import java.util.List;


public class TripService {

	public List<Trip> getTripsIfUserIsFriendOfTheLoggedInUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = getLoggedUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = findTripsForUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

    protected List<Trip> findTripsForUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
