package com.syrisa.springlibrarydocker.service.impl;

import com.syrisa.springlibrarydocker.model.impl.User;
import com.syrisa.springlibrarydocker.repository.AddressRepository;
import com.syrisa.springlibrarydocker.repository.UserRepository;
import com.syrisa.springlibrarydocker.service.UserService;
import com.syrisa.springlibrarydocker.utility.generate.credentialnumber.CredentialNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public User create(User user) {
        try {
            user.setUserID(CredentialNumber.numberGenerate.generateNumber(USER_ID_LENGTH));
            addressRepository.save(user.getAddress());
            return userRepository.save(user);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @Override
    public User update(User user) {
        if (getById(user.getUserID()) != null) {
            return userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User could not updated.");
        }
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getById(long userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User could not found."));
    }

    @Override
    public String delete(long userID) {
        try {
            User user = getById(userID);
            userRepository.delete(user);
            return userID + " Credentialed user deleted.";
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User could not deleted.");
        }
    }
}
