package com.example.package404.user.model;

import com.example.package404.board.model.Board;
import com.example.package404.comment.model.Comment;
import com.example.package404.instructor.model.Instructor;
import com.example.package404.student.model.StudentDetail;
import com.example.package404.user.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;


import org.apache.catalina.Role;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private LocalDate birth;
    private boolean enabled;
    private String role;

    @JsonIgnore
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = true)
    private StudentDetail studentDetail;


    // 게시판이랑 관계 설정
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Board> boards;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Instructor instructor;

    // 댓글이랑 관계 설정
//    @OneToMany(mappedBy = "user")
//    private List<Comment> comments;

//    @ElementCollection(fetch = FetchType.EAGER) // 다중 권한 저장
//    private List<String> roles = new ArrayList<>();


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role)); // 예: "ROLE_USER", "ROLE_ADMIN" 등

        return authorities;
    }
//    public static UserDetails loadUserByEmail(String email, UserRepository userRepository) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + email));
//
//        return user;
//    }




    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void assignInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
