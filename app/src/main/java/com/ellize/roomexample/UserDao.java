package com.ellize.roomexample;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAllUsers();
    @Query("SELECT * FROM user WHERE age > :minAge")
    List<User> getUsersByMinAge(int minAge);

    @Query("SELECT * FROM user WHERE uuid = :id")
    List<User> getUserById(int id);

    @Insert(onConflict = REPLACE)
    void insert(User user);
    @Update(onConflict = IGNORE)
    void update(User user);
    @Delete
    void delete(User user);

}
