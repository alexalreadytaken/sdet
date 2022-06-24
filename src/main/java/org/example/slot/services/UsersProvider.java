package org.example.slot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.slot.models.ui.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersProvider {

    @Value("${users.file}")
    private String usersFile;

    private List<User> users;

    @PostConstruct
    @SneakyThrows
    private void loadUsers(){
        var om =  new ObjectMapper();
        var usersStream = new FileInputStream(usersFile);
        var usersArray = om.readValue(usersStream, User[].class);
        users = Arrays.stream(usersArray).toList();
    }

    public User getUserById(String id) {
        return users.stream()
                .filter(u -> u.id().equals(id))
                .findAny()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                String.format("user by id %s not found", id)
                        )
                );
    }
}
