package lk.ijse.userservice.service.impl;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.exception.DuplicateUserException;
import lk.ijse.userservice.exception.UserNotFoundException;
import lk.ijse.userservice.repository.UserRepository;
import lk.ijse.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lk.ijse.userservice.mapper.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO dto) {

        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }

        User user = User.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        User saved = userRepository.save(user);

        return userMapper.toDto(saved);
    }

    @Override
    public UserDTO getUser(String id) {
        log.debug("Fetching user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.debug("Fetching all users");

        return userRepository.findAll()
                .stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .address(user.getAddress())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
                .toList();
    }

    @Override
    public UserDTO updateUser(String id, UserDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setAddress(dto.getAddress());
        user.setPhoneNumber(dto.getPhoneNumber());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(String userId) {
        log.debug("Deleting user with ID: {}", userId);

        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found: " + userId);
        }

        userRepository.deleteById(userId);
    }
}