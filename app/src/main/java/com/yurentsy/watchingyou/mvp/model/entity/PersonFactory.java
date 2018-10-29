package com.yurentsy.watchingyou.mvp.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silan on 29.10.2018.
 */

public class PersonFactory {

    public static List<Person> getList(){
        List<Person> personList=new ArrayList<>();
        personList.add(new Person("2001",
                "Vladimir",
                "Putin",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/vladimir-putin.jpg"));
        personList.add(new Person("2001",
                "Vladimir",
                "Putin",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/vladimir-putin.jpg"));
        personList.add(new Person("2001",
                "Vladimir",
                "Putin",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/vladimir-putin.jpg"));
        personList.add(new Person("2001",
                "Vladimir",
                "Putin",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/vladimir-putin.jpg"));
        personList.add(new Person("2001",
                "Vladimir",
                "Putin",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/vladimir-putin.jpg"));
        personList.add(new Person("2001",
                "Vladimir",
                "Putin",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/vladimir-putin.jpg"));
        return personList;
    }
}
