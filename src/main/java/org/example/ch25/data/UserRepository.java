package org.example.ch25.data;

import org.springframework.data.repository.CrudRepository;
import org.example.ch25.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findByUsername(String username);

}
