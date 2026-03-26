package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;
import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO dto);

    UserDTO getUser(String userId);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(String userId, UserDTO dto);

    void deleteUser(String userId);
}