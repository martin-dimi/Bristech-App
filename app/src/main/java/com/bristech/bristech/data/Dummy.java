package com.bristech.bristech.data;


import com.bristech.bristech.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class Dummy {

    public static List<Event> getEvents(){
        Event testEvent1 = new Event("testTitle1", "testTopic1", "testDate1",
                "testTime1", 1, "testLocation1",
                "testShortDescription1");
        Event testEvent2 = new Event("testTitle2", "testTopic2", "testDate2",
                "testTime2", 2, "testLocation2",
                "testShortDescription2");
        Event testEvent3 = new Event("testTitle3", "testTopic3", "testDate3",
                "testTime3", 3, "testLocation3",
                "testShortDescription3");
        List<Event> eventsList = new ArrayList<Event>();
        eventsList.add(testEvent1);
        eventsList.add(testEvent2);
        eventsList.add(testEvent3);

        return eventsList;
    }

}
