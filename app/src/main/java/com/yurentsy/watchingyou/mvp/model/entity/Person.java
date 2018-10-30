package com.yurentsy.watchingyou.mvp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by silan on 29.10.2018.
 */

public class Person {
/*    "id": 2001,
            "name": "Vladimir",
            "surname": "Putin",
            "position": "",
            "urlPhoto": "https://www.worldpresidentsdb.com/images/presidents/vladimir-putin.jpg"*/
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("position")
    @Expose
    private String position;

    @SerializedName("urlPhoto")
    @Expose
    private String urlPhoto;

    public Person(String id, String name, String surname, String position, String urlPhoto) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.urlPhoto = urlPhoto;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }


    public String getPosition() {
        return position;
    }


    public String getUrlPhoto() {
        return urlPhoto;
    }

}
