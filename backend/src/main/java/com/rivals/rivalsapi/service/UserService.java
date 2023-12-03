package com.rivals.rivalsapi.service;

import com.rivals.rivalsapi.dto.challenge.ChallengeDto;
import com.rivals.rivalsapi.dto.user.UserDto;
import com.rivals.rivalsapi.model.Challenge;
import com.rivals.rivalsapi.model.User;
import com.rivals.rivalsapi.repository.ChallengeRepository;
import com.rivals.rivalsapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

    public Page<UserDto> getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return userRepository.findAll(pageable)
                .map(UserDto::fromUser);
    }
    public UserDto followUser(String username) {
        Pair<User, User> userContext = getContextUsers(username);
        User user = userContext.a;
        User target = userContext.b;

        if (user.equals(target)) throw new IllegalArgumentException("You can't follow yourself");
        if (user.getFollowing().contains(target)) throw new IllegalArgumentException("You're already following this user");
        user.follow(target);
        userRepository.save(user);
        return UserDto.fromUser(target);
    }

    public UserDto unfollowUser(String username) {
        Pair<User, User> userContext = getContextUsers(username);
        User user = userContext.a;
        User target = userContext.b;

        if (user.equals(target)) throw new IllegalArgumentException("You can't unfollow yourself");
        if (!user.getFollowing().contains(target)) throw new IllegalArgumentException("You're not following this user");
        user.unfollow(target);
        userRepository.save(user);
        return UserDto.fromUser(target);
    }

    public List<UserDto> getFollowing() {
        User user = getContextUsers();
        return user.getFollowing().stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    public List<UserDto> getFollowers() {
        User user = getContextUsers();

        return user.getFollowers().stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    public List<ChallengeDto> getStarredChallenges(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return getContextUsers().getStarred()
                .stream().map(ChallengeDto::fromChallenge)
                .toList();
    }

    public ChallengeDto starChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
        User user = getContextUsers();
        user.starChallenge(challenge);
        userRepository.save(user);
        return ChallengeDto.fromChallenge(challenge);
    }

    public Object unstarChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));
        User user = getContextUsers();
        user.unstarChallenge(challenge);
        userRepository.save(user);
        return ChallengeDto.fromChallenge(challenge);
    }

    private User getContextUsers() {
        Authentication authorization = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authorization.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Pair<User, User> getContextUsers(String username) {
        Authentication authorization = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authorization.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User target = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new Pair<>(user, target);
    }
}
