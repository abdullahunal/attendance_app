package com.example.codeit.attendancecheck.persistence;
import java.util.List;

public interface IDatabaseOperations {
    void addUser(User user);

    // User read(User user);

    List<User> getUserList();

    boolean updateUser(User user);

    boolean deleteUser(int id);
}
