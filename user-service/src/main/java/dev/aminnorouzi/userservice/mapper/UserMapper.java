package dev.aminnorouzi.userservice.mapper;

import dev.aminnorouzi.userservice.dto.UserResponse;
import dev.aminnorouzi.userservice.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public User mapToUser(UserResponse response) {
        return modelMapper.map(response, User.class);
    }

    public UserResponse mapFromUser(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    public UserResponse.Pageable mapFromUser(Page<User> users) {
        return modelMapper.map(users, UserResponse.Pageable.class);
    }
}
