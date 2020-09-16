package com.pillikan.store.service;

import com.pillikan.store.dto.UserDto;
import com.pillikan.store.model.Role;
import com.pillikan.store.model.User;
import com.pillikan.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.pillikan.store.util.ValidationUtil.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    public User getById(int id) {
        User dbUser = checkNotFoundWithId(repository.getById(id), id);
        log.info("IN getById - user {} found with id {}", dbUser, id);
        return dbUser;
    }

    public List<User> getAll() {
        List<User> users = repository.getAll();
        log.info("IN getAll - {} users found", users.size());
        return users;
    }

    @Transactional
    public User create(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(encoder.encode(user.getPassword()));
        User dbUser = repository.create(user);
        log.info("IN create - {} is registered", dbUser);
        return dbUser;
    }

    @Transactional
    public User update(UserDto userDto, int id) {
        assureIdConsistent(userDto, id);
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User dbUser = getById(id);
        BeanUtils.copyProperties(userDto, dbUser);
        log.info("IN update - {} is updated", dbUser);
        return dbUser;
    }

    @Transactional
    public void delete(int id) {
        checkNotFound(repository.delete(id), "id=" + id);
        log.info("IN delete - is deleted with id {}", id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found");
        }
        log.info("IN loadUserByUsername - {} is found with email {}", user, email);
        return user;
    }

    public User getByEmail(String email) {
        User user = checkNotFound(repository.getByEmail(email), "email " + email);
        log.info("IN getByEmail - {} found with email {}", user, email);
        return user;
    }
}
