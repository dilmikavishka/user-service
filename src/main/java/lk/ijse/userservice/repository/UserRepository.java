package lk.ijse.userservice.repository;

import lk.ijse.userservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByPhoneNumber(String phoneNumber);
}