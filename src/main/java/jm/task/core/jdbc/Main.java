package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Eva", "Po", (byte)  44);
        userServiceImpl.saveUser("Olga", "Orlova", (byte) 25);
        userServiceImpl.saveUser("Jane", "Hat", (byte) 32);
        userServiceImpl.saveUser("Johnny", "Depp", (byte) 59);
        System.out.println(userServiceImpl.getAllUsers());
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();

    }
}
