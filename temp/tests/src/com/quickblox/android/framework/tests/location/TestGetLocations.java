package com.quickblox.android.framework.tests.location;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.net.request.QBLocationRequestBuilder;
import com.quickblox.android.framework.modules.location.net.request.SortField;
import com.quickblox.android.framework.modules.location.net.request.SortOrder;
import com.quickblox.android.framework.modules.location.net.result.QBLocationPagedResult;
import com.quickblox.android.framework.modules.location.net.server.QBLocations;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.tests.GenericTestCase;
import com.quickblox.android.framework.tests.helpers.ComplexUser;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * User: Oleg Soroka
 * Date: 14.09.12
 * Time: 19:34
 */
public class TestGetLocations extends GenericTestCase {

    public static ArrayList<ComplexUser> users = new ArrayList<ComplexUser>();
    public static ArrayList<QBLocation> locations = new ArrayList<QBLocation>();

    public static int usersCount = 4;
    public static int locationsPerUser = 2;
    public static String PASSWORD_POSTFIX = "_password";

    public static Integer totalEntriesExpected = usersCount * locationsPerUser;
    public static Integer currentPageDefaultExpected = 1;
    public static Integer perPageDefaultExpected = 10;

    public void testEmptyParams() {
        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkSchemaValidation("getLocations", result);

                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;
                assertEquals(currentPageDefaultExpected, pagedResult.getCurrentPage());
                assertEquals(perPageDefaultExpected, pagedResult.getPerPage());
                assertEquals(totalEntriesExpected, pagedResult.getTotalEntries());
            }
        });
    }

    /* Current page, per page */

    public void testPerPageAndCurrentPage() {
        final Integer perPage = 2;
        final Integer currentPage = 3;

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setPerPage(perPage);
        requestBuilder.setCurrentPage(currentPage);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;
                assertEquals(perPage, pagedResult.getPerPage());
                assertEquals(currentPage, pagedResult.getCurrentPage());
            }
        });
    }

    public void testPerPageAndCurrentPageLessThanMin() {
        final Integer perPage = 0;
        final Integer currentPage = 0;

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setPerPage(perPage);
        requestBuilder.setCurrentPage(currentPage);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_UNPROCESSABLE_ENTITY, result);
                if (!result.isSuccess()) {
                    assertEquals(result.getErrors().get(0), "per_page should be an integer between 1 and 100");
                    assertEquals(result.getErrors().get(1), "page should be a positive integer");
                }
            }
        });
    }

    public void testCurrentPageMoreThanMax() {
        final Integer perPage = 101; // Max is 100.

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setPerPage(perPage);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_UNPROCESSABLE_ENTITY, result);
                if (!result.isSuccess()) {
                    assertEquals(result.getErrors().get(0), "per_page should be an integer between 1 and 100");
                }
            }
        });
    }

    /* Created at */

    public void testCreatedAt() {
        QBLocation location = users.get(0).getLocation(0);

        int timestamp = (int) (location.getCreatedAt().getTime() / 1000);

        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        for (QBLocation l : locations) {
            if ((int) (l.getCreatedAt().getTime() / 1000) == timestamp) {
                locationIds.add(l.getId());
            }
        }

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setCreatedAt(timestamp);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;
                assertEquals(locationIds.size(), pagedResult.getLocations().size());

                for (QBLocation l : pagedResult.getLocations()) {
                    assertTrue(locationIds.contains(l.getId()));
                }
            }
        });
    }

    /* Created at */

    public void testCreatedAtEmptyResult() {
        QBLocation location = users.get(0).getLocation(0);

        int timestamp = (int) (location.getCreatedAt().getTime() / 1000) - 1;

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setCreatedAt(timestamp);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_NOT_FOUND, result);
                checkEmptyResponseBody(result);
            }
        });
    }

    public void testCreatedAtIncorrect() {
        int timestamp = 0;

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setCreatedAt(timestamp);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_UNPROCESSABLE_ENTITY, result);
                if (!result.isSuccess()) {
                    assertEquals("created_at should be a timestamp", result.getErrors().get(0));
                }
            }
        });
    }

    /* User id */

    public void testUserId() {
        final ComplexUser cUser = users.get(0);

        QBUser user = cUser.getUser();
        long userId = user.getId();

        final ArrayList<Integer> locationIds = new ArrayList<Integer>();
        for (QBLocation l : cUser.getLocations()) {
            locationIds.add(l.getId());
        }

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setUserId(userId);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;

                assertEquals(cUser.getLocations().size(), pagedResult.getLocations().size());
                assertEquals(new Integer(cUser.getLocations().size()), pagedResult.getTotalEntries());
                for (QBLocation l : pagedResult.getLocations()) {
                    assertTrue(locationIds.contains(l.getId()));
                }
            }
        });
    }

    public void testUserIdIncorrect() {
        long userId = 0;

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setUserId(userId);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_UNPROCESSABLE_ENTITY, result);
                if (!result.isSuccess()) {
                    assertEquals("user_id should be a positive integer", result.getErrors().get(0));
                }
            }
        });
    }

    public void testUserIdNonexistent() {
        long userId = 1;

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setUserId(userId);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_FORBIDDEN, result);
                if (!result.isSuccess()) {
                    assertEquals("user_id don't belongs to current application", result.getErrors().get(0));
                }
            }
        });
    }

    /* User ids */

    public void testUserIds() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        int usersDepth = 3;

        Integer ids[] = new Integer[usersDepth];

        for (int i = 0; i < usersDepth; i++) {
            for (QBLocation l : users.get(i).getLocations()) {
                locationIds.add(l.getId());
            }

            ids[i] = users.get(i).getUser().getId();
        }

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setUserIds(ids[0], ids[1], ids[2]);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);

                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;

                assertEquals(locationIds.size(), pagedResult.getLocations().size());
                assertEquals(new Integer(locationIds.size()), pagedResult.getTotalEntries());
                for (QBLocation l : pagedResult.getLocations()) {
                    assertTrue(locationIds.contains(l.getId()));
                }
            }
        });
    }

    public void testUserIdsNonexistent() {
        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setUserIds(1, 2, 3);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_NOT_FOUND, result);
                checkEmptyResponseBody(result);
            }
        });
    }

    public void testUserIdsIncorrect() {
        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setUserIds(-1, -2, -3);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_UNPROCESSABLE_ENTITY, result);
                if (!result.isSuccess()) {
                    assertEquals("user_ids should be a positive integer list divided by coma. " +
                            "Example user.ids=2,4,5,333", result.getErrors().get(0));
                }
            }
        });
    }

    /* User name */

    public void testUserName() {
        final ComplexUser cUser = users.get(0);

        String userName = cUser.getUser().getFullName();

        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        for (ComplexUser cu : users) {
            if (cu.getUser().getFullName().equals(userName)) {
                for (QBLocation l : cu.getLocations()) {
                    locationIds.add(l.getId());
                }
            }
        }

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setUserName(userName);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;

                assertEquals(locationIds.size(), pagedResult.getLocations().size());
                assertEquals(new Integer(locationIds.size()), pagedResult.getTotalEntries());
                for (QBLocation l : pagedResult.getLocations()) {
                    assertTrue(locationIds.contains(l.getId()));
                }
            }
        });
    }

    public void testUserNameNonexistent() {
        String userName = "Nonexistent Name";

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setUserName(userName);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_NOT_FOUND, result);
                checkEmptyResponseBody(result);
            }
        });
    }

    /* External ids */

    public void testExternalIds() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        int usersDepth = 3;

        String ids[] = new String[usersDepth];

        for (int i = 0; i < usersDepth; i++) {
            for (QBLocation l : users.get(i).getLocations()) {
                locationIds.add(l.getId());
            }

            ids[i] = users.get(i).getUser().getExternalId();
        }

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setUserExternalIds(ids[0], ids[1], ids[2]);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);

                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;

                assertEquals(locationIds.size(), pagedResult.getLocations().size());
                assertEquals(new Integer(locationIds.size()), pagedResult.getTotalEntries());
                for (QBLocation l : pagedResult.getLocations()) {
                    assertTrue(locationIds.contains(l.getId()));
                }
            }
        });
    }

    public void testExternalIdsNonexistent() {
        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setUserExternalIds("1", "2", "3");

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_NOT_FOUND, result);
                checkEmptyResponseBody(result);
            }
        });
    }

    /* Min created at */

    int usersCountHalf = usersCount / 2;

    public void testMinCreatedAt() {
        QBLocation location = users.get(usersCountHalf).getLocation(0);

        int timestamp = (int) (location.getCreatedAt().getTime() / 1000);

        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        for (QBLocation l : locations) {
            if ((int) (l.getCreatedAt().getTime() / 1000) >= timestamp) {
                locationIds.add(l.getId());
            }
        }

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setMinCreatedAt(timestamp);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;
                assertEquals(locationIds.size(), pagedResult.getLocations().size());

                for (QBLocation l : pagedResult.getLocations()) {
                    assertTrue(locationIds.contains(l.getId()));
                }
            }
        });
    }

    public void testMinCreatedAtEmptyResult() {
        QBLocation location = users.get(usersCount - 1).getLocation(locationsPerUser - 1);

        int timestamp = (int) (location.getCreatedAt().getTime() / 1000) + 1;

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setMinCreatedAt(timestamp);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_NOT_FOUND, result);
                checkEmptyResponseBody(result);
            }
        });
    }

    public void testMinCreatedAtIncorrect() {
        int timestamp = 0;

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setMinCreatedAt(timestamp);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_UNPROCESSABLE_ENTITY, result);
                if (!result.isSuccess()) {
                    assertEquals("min_created_at should be a timestamp", result.getErrors().get(0));
                }
            }
        });
    }

    /* Min created at */

    public void testMaxCreatedAt() {
        QBLocation location = users.get(usersCountHalf).getLocation(0);

        int timestamp = (int) (location.getCreatedAt().getTime() / 1000);

        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        for (QBLocation l : locations) {
            if ((int) (l.getCreatedAt().getTime() / 1000) <= timestamp) {
                locationIds.add(l.getId());
            }
        }

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setMaxCreatedAt(timestamp);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;
                assertEquals(locationIds.size(), pagedResult.getLocations().size());

                for (QBLocation l : pagedResult.getLocations()) {
                    assertTrue(locationIds.contains(l.getId()));
                }
            }
        });
    }

    public void testMaxCreatedAtEmptyResult() {
        QBLocation location = users.get(usersCount - 1).getLocation(locationsPerUser - 1);

        int timestamp = 1;

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setMaxCreatedAt(timestamp);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_NOT_FOUND, result);
                checkEmptyResponseBody(result);
            }
        });
    }

    public void testMaxCreatedAtIncorrect() {
        int timestamp = 0;

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setMaxCreatedAt(timestamp);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_UNPROCESSABLE_ENTITY, result);
                if (!result.isSuccess()) {
                    assertEquals("max_created_at should be a timestamp", result.getErrors().get(0));
                }
            }
        });
    }

    /* Gep rect */

    // Coordinates for follow points
    // https://img.skitch.com/20120918-pxb8tubeijqd1ukrdrfwgnd7qc.png
    // First point is SW, second is NE

    // SW point to build rect
    double borderPointLat1 = 49.990106;
    double borderPointLng1 = 36.185703;

    // NE point to build rect
    double borderPointLat2 = 50.043934;
    double borderPointLng2 = 36.28767;


    // Center point for radius area
    public static double lat1 = 50.010431;
    public static double lng1 = 36.224327;

    public static double lat2 = 50.004694;
    public static double lng2 = 36.240807;

    // Check with http://www.sunearthtools.com/tools/distance.php
    public static int distanceInMeters = 1338;

    public void testGeoRect() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        locationIds.add(locations.get(0).getId());
        locationIds.add(locations.get(1).getId());

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setGeoRect(borderPointLat1, borderPointLng1, borderPointLat2, borderPointLng2);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;
                assertEquals(locationIds.size(), pagedResult.getLocations().size());

                for (QBLocation l : pagedResult.getLocations()) {
                    assertTrue(locationIds.contains(l.getId()));
                }
            }
        });
    }

    public void testGeoRectNonexistent() {
        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setGeoRect(0, 0, 1, 1);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_NOT_FOUND, result);
                checkEmptyResponseBody(result);
            }
        });
    }

    public void testGeoRectPointOnBorder() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        locationIds.add(locations.get(0).getId());

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setGeoRect(lat1, lng1, borderPointLat2, borderPointLng2);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;
                assertEquals(locationIds.size(), pagedResult.getLocations().size());

                for (QBLocation l : pagedResult.getLocations()) {
                    assertTrue(locationIds.contains(l.getId()));
                }
            }
        });
    }

    /* Radius */

    public void testRadius() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        locationIds.add(locations.get(0).getId());
        locationIds.add(locations.get(1).getId());

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setRadius(lat1, lng1, distanceInMeters);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;
                assertEquals(locationIds.size(), pagedResult.getLocations().size());

                for (QBLocation l : pagedResult.getLocations()) {
                    assertTrue(locationIds.contains(l.getId()));
                }
            }
        });
    }

    public void testRadiusTwoPoints() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        // Check with http://www.sunearthtools.com/tools/distance.php
        int distanceInMeters = 1338;

        locationIds.add(locations.get(0).getId());
        locationIds.add(locations.get(1).getId());

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setRadius(lat1, lng1, lat2, lng2);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;
                assertEquals(locationIds.size(), pagedResult.getLocations().size());

                for (QBLocation l : pagedResult.getLocations()) {
                    assertTrue(locationIds.contains(l.getId()));
                }
            }
        });
    }

    public void testRadiusEmpty() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setRadius(0, 0, 1);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_NOT_FOUND, result);
                checkEmptyResponseBody(result);
            }
        });
    }

    public void testRadiusIncorrect() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setRadius(0, 0, -1);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_UNPROCESSABLE_ENTITY, result);
                if (!result.isSuccess()) {
                    assertEquals("radius should be a positive integer", result.getErrors().get(0));
                }
            }
        });
    }

    /* Sort */

    public void testSortCreatedAtAsc() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        Comparator<QBLocation> c = new Comparator<QBLocation>() {
            @Override
            public int compare(QBLocation location, QBLocation location1) {
                return location.getCreatedAt().compareTo(location1.getCreatedAt());
            }
        };

        final ArrayList<QBLocation> sortedLocations = new ArrayList<QBLocation>(locations);
        Collections.sort(sortedLocations, c);

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setSort(SortField.CREATED_AT, SortOrder.ASCENDING);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);

                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;

                for (int i = 0; i < pagedResult.getLocations().size(); i++) {
                    QBLocation expected = sortedLocations.get(i);
                    QBLocation actual = pagedResult.getLocations().get(i);
                    assertEquals(expected.getId(), actual.getId());
                }
            }
        });
    }

    public void testSortCreatedAtDesc() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        Comparator<QBLocation> c = new Comparator<QBLocation>() {
            @Override
            public int compare(QBLocation location, QBLocation location1) {
                return location.getCreatedAt().compareTo(location1.getCreatedAt());
            }
        };

        final ArrayList<QBLocation> sortedLocations = new ArrayList<QBLocation>(locations);
        Collections.sort(sortedLocations, Collections.reverseOrder(c));

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setSort(SortField.CREATED_AT, SortOrder.DESCENDING);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);

                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;

                for (int i = 0; i < pagedResult.getLocations().size(); i++) {
                    QBLocation expected = sortedLocations.get(i);
                    QBLocation actual = pagedResult.getLocations().get(i);
                    assertEquals(expected.getId(), actual.getId());
                }
            }
        });
    }

    public void testSortLatitudeAsc() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        Comparator<QBLocation> c = new Comparator<QBLocation>() {
            @Override
            public int compare(QBLocation location, QBLocation location1) {
                return location.getLatitude().compareTo(location1.getLatitude());
            }
        };

        final ArrayList<QBLocation> sortedLocations = new ArrayList<QBLocation>(locations);
        Collections.sort(sortedLocations, c);

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setSort(SortField.LATITUDE, SortOrder.ASCENDING);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);

                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;

                for (int i = 0; i < pagedResult.getLocations().size(); i++) {
                    QBLocation expected = sortedLocations.get(i);
                    QBLocation actual = pagedResult.getLocations().get(i);
                    assertEquals(expected.getId(), actual.getId());
                }
            }
        });
    }

    public void testSortLatitudeDesc() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        Comparator<QBLocation> c = new Comparator<QBLocation>() {
            @Override
            public int compare(QBLocation location, QBLocation location1) {
                return location.getLatitude().compareTo(location1.getLatitude());
            }
        };

        final ArrayList<QBLocation> sortedLocations = new ArrayList<QBLocation>(locations);
        Collections.sort(sortedLocations, Collections.reverseOrder(c));

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setSort(SortField.LATITUDE, SortOrder.DESCENDING);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);

                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;

                for (int i = 0; i < pagedResult.getLocations().size(); i++) {
                    QBLocation expected = sortedLocations.get(i);
                    QBLocation actual = pagedResult.getLocations().get(i);
                    assertEquals(expected.getId(), actual.getId());
                }
            }
        });
    }

    public void testSortLongitudeAsc() {
        final ArrayList<Integer> locationIds = new ArrayList<Integer>();

        Comparator<QBLocation> c = new Comparator<QBLocation>() {
            @Override
            public int compare(QBLocation location, QBLocation location1) {
                return location.getLongitude().compareTo(location1.getLongitude());
            }
        };

        final ArrayList<QBLocation> sortedLocations = new ArrayList<QBLocation>(locations);
        Collections.sort(sortedLocations, c);

        QBLocationRequestBuilder requestBuilder = new QBLocationRequestBuilder();
        requestBuilder.setSort(SortField.LONGITUDE, SortOrder.ASCENDING);

        QBLocations.getLocations(requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);

                QBLocationPagedResult pagedResult = (QBLocationPagedResult) result;

                for (int i = 0; i < pagedResult.getLocations().size(); i++) {
                    QBLocation expected = sortedLocations.get(i);
                    QBLocation actual = pagedResult.getLocations().get(i);
                    assertEquals(expected.getId(), actual.getId());
                }
            }
        });
    }

}