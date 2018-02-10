package com.bristech.bristech.data;


import com.bristech.bristech.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class Dummy {

    public static List<Event> getEvents(){
        Event testEvent1 = new Event(1, "title", "location", "date", "time", "description", "backdrop");
        Event testEvent2 = new Event(2, "title", "location", "date", "time", "description", "backdrop");
        Event testEvent3 = new Event(3, "title", "location", "date", "time", "description", "backdrop");

        List<Event> eventsList = new ArrayList<Event>();
        eventsList.add(testEvent1);
        eventsList.add(testEvent2);
        eventsList.add(testEvent3);

        return eventsList;
    }

}
