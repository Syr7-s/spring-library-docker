package com.syrisa.springlibrarydocker.service;

import com.syrisa.springlibrarydocker.model.impl.User;

public interface UserService extends ModelService<User> {

    int userIdLength = 10;

    User getById(long userID);

    String delete(long userID);
}
