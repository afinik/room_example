package com.ellize.roomexample;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ColumnInfo.BLOB;
import static androidx.room.ColumnInfo.TEXT;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uuid;
    @ColumnInfo(name = "user_name")
    public String name;
    @ColumnInfo(typeAffinity = TEXT)
    public String surname;
    public int age;
    public int role;
    @Ignore
    public boolean looked;


    public User(String name, String surname, int age, int role) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.role = role;
        looked = false;
    }
}
