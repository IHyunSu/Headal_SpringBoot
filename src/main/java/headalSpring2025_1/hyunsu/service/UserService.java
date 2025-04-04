package headalSpring2025_1.hyunsu.service;

import headalSpring2025_1.hyunsu.controller.UserRepository;
import headalSpring2025_1.hyunsu.domain.User;
import headalSpring2025_1.hyunsu.dto.UserSimpleResponseDto;
import headalSpring2025_1.hyunsu.repository.UserRepository;
//패키지명이 다를 시 본인 패키지명으로 작성해야 오류가 안납니다.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserSimpleResponseDto saveUser(User newUser) {
        // 중복 회원 검증
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new IllegalStateException("중복되는 username입니다.");
        }

        userRepository.save(newUser);
        return convertUserToSimpleDto(newUser, newUser);
    }

    public UserSimpleResponseDto convertUserToSimpleDto(User currentUser, User targetUser) {
        return new UserSimpleResponseDto(
                currentUser.getId(),
                currentUser.getUsername(),
                currentUser.getName(),
                null,
                false
        );
    }
}