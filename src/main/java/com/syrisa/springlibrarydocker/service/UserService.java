package com.syrisa.springlibrarydocker.service;

import com.syrisa.springlibrarydocker.model.impl.User;

public interface UserService extends ModelService<User> {
    User getById(long userID);

    String delete(long userID);
}
