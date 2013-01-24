package com.quickblox.android.framework.tests.helpers;

import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.users.models.QBUser;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 16.09.12
 * Time: 12:39
 */
public class ComplexUser {

    QBUser user;
    ArrayList<QBLocation> locations = new ArrayList<QBLocation>();

    public ComplexUser(QBUser user) {
        this.user = user;
    }

    public QBUser getUser() {
        return user;
    }

    public void setUser(QBUser user) {
        this.user = user;
    }

    public ArrayList<QBLocation> getLocations() {
        return locations;
    }

    public void addLocation(QBLocation location) {
        locations.add(location);
    }

    public QBLocation getLocation(int id) {
        return locations.get(id);
    }

    @Override
    public String toString() {
        return "ComplexUser{" +
                "testUserAccount=" + user +
                ", locations=" + locations +
                '}';
    }
}
