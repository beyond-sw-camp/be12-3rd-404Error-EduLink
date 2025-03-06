package com.example.package404.user.service;

import com.example.package404.global.exception.UserException;
import com.example.package404.global.response.responseStatus.UserResponseStatus;
import com.example.package404.instructor.model.dto.req.UpdateUserInstructorDto;
import com.example.package404.user.model.Dto.UserInsSignUpDto;
import com.example.package404.user.model.Dto.UserResponseDto;
import com.example.package404.user.model.User;

import com.example.package404.user.repository.UserRepository;
import com.example.package404.user.model.Dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    private static final List<String> VALID_ROLES = List.of("INSTRUCTOR", "MANAGER", "STUDENT");

    @Transactional
    public UserResponseDto.SignupResponse signup(UserRequestDto.SignupRequest dto, String role) {
         if (!pattern.matcher(dto.getEmail()).matches()) {
            throw new UserException(UserResponseStatus.INVALID_EMAIL_FORMAT);
        }

        // 이메일 중복 체크
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserException(UserResponseStatus.EMAIL_ALREADY_IN_USE);
        }

        User user = null;

        if ("MANAGER".equals(role)) {  // role.equals("MANAGER") 방식으로 비교
            user = managerSignup(dto);
        } else if ("STUDENT".equals(role)) {
            user = studentSignup(dto);
        } else if ("INSTRUCTOR".equals(role)) {
            user = instructorSignup(dto);
        } else {
            throw new UserException(UserResponseStatus.UNIDENTIFIED_ROLE);
        }
        // 이메일 형식 검증

        if (user != null) {
            return UserResponseDto.SignupResponse.from(user);
        }
        throw new UserException(UserResponseStatus.USER_SAVE_FAIL);
    }

    @Transactional
    public User instructorSignup(UserRequestDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = userRepository.save(dto.toEntity(encodedPassword, "INSTRUCTOR"));

        return user;
    }


    //
    public void CustomInstructorSignup(UserInsSignUpDto dto, String role) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        userRepository.save(dto.toEntity(encodedPassword , "INSTRUCTOR"));
    }

    @Transactional
    public User studentSignup(UserRequestDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = userRepository.save(dto.toEntity(encodedPassword, "STUDENT"));

        return user;
    }

    @Transactional
    public User managerSignup(UserRequestDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = userRepository.save(dto.toEntity(encodedPassword, "MANAGER"));

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }


    public User getUserinfo(Long userIdx){
        return userRepository.findById(userIdx)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public void setUserInfo(User user){
        userRepository.save(user);
    }


    public List<User> findUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public void setInstructorInfo(Long instructorIdx, UpdateUserInstructorDto dto) {
        userRepository.save(dto.toInstructorEntity(instructorIdx));
    }


    public List<User> findUsersByRoleWithInstructor() {
        return userRepository.findUsersWithInstructorByRole("INSTRUCTOR");
    }
}
