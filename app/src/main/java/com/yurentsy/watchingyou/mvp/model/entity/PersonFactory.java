package com.yurentsy.watchingyou.mvp.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silan on 29.10.2018.
 */

public class PersonFactory {

    public static List<Person> getList() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("2001",
                "Vladimir",
                "Putin",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/vladimir-putin.jpg"));
        personList.add(new Person("2002",
                "Donald",
                "Tramp",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/donald-trump.jpg"));
        personList.add(new Person("2003",
                "Doris",
                "Leuthard",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/doris-leuthard.jpg"));
        personList.add(new Person("2004",
                "Emmanuel",
                "Macron",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/emmanuel-macron.jpg"));
        personList.add(new Person("2005",
                "Erna",
                "Solberg",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/erna-solberg.jpg"));
        personList.add(new Person("2006",
                "Giorgio",
                "Napolitano",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/president-giorgio-napolitano.jpg"));
        personList.add(new Person("2007",
                "Ivo",
                "Josipovic",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/ivo-josipovic.jpg"));

        personList.add(new Person("2008",
                "Justin",
                "Trudeau",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/justin-trudeau.jpg"));

        personList.add(new Person("2009",
                "Theresa",
                "May",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/theresa-may.jpg"));
        personList.add(new Person("2010",
                "Charles",
                "Michel",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/charles-michel.jpg"));
        personList.add(new Person("2011",
                "Hassan",
                "Rouhani",
                "president",
                "https://www.worldpresidentsdb.com/images/presidents/hassan-rouhani.jpg"));
        return personList;
    }
}
