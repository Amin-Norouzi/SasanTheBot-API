package dev.aminnorouzi.userservice.service;

import dev.aminnorouzi.userservice.dto.UserRequest;
import dev.aminnorouzi.userservice.exception.IllegalUserRequestException;
import dev.aminnorouzi.userservice.exception.UserNotFoundException;
import dev.aminnorouzi.userservice.model.Status;
import dev.aminnorouzi.userservice.model.User;
import dev.aminnorouzi.userservice.repository.UserRepository;
import dev.aminnorouzi.userservice.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Clock clock;
    private final PasswordUtil passwordUtil;

    public User create(UserRequest request) {
        exists(request.getEmail());

        String hashedPassword = passwordUtil.hash(request.getPassword());

        User user = User.builder()
                .telegramId(request.getTelegramId())
                .telegramChatId(request.getTelegramChatId())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(hashedPassword)
                .status(Status.ACTIVE)
                .build();

        User saved = userRepository.save(user);

        log.info("Created new user: {}", saved);
        return saved;
    }

    private void exists(String email) {
        boolean exists = userRepository.existsByEmail(email);
        if (exists) {
            throw new IllegalUserRequestException("Email: %s already exists!".formatted(email));
        }
    }

    public User getById(Long id) {
        User found = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User: %s not found!".formatted(id)));

        log.info("Found a user: {}", found);
        return found;
    }

    public List<User> getAll() {
        List<User> found = userRepository.findAll();

        log.info("Found all user: {}", found);
        return found;
    }

    // slice
    public Page<User> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        Page<User> found = userRepository.findAll(pageable);

        log.info("Found all user: filter={}, {}", pageable, found);
        return found;
    }

    public void delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);

        log.info("Deleted a user: {}", user);
    }

    public User change(Long id, String request) {
        User user = getById(id);

        Status oldStatus = user.getStatus();
        Status newStatus = Status.valueOf(request.toUpperCase());

        user.setStatus(newStatus);
        User saved = userRepository.save(user);

        log.info("Changed user status: ps={}, ns={}, {}", oldStatus, newStatus, saved);
        return saved;
    }

}
