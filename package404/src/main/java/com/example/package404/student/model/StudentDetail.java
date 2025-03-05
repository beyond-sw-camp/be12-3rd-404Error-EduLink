package com.example.package404.student.model;

import com.example.package404.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class StudentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    private Long idx;
    private String address;
    private Boolean testStatus;
    private Integer perception;
    private Integer attendance;
    private Integer leaveEarly;
    private Integer outing;
    private Integer vacationLeft;

    @OneToOne
    @JoinColumn(name="user_idx")
    @JsonIgnore
    private User user;

    public void updateTestStatus() {
        this.testStatus = true;
    }
    public void updatePerception() {
        this.perception += 1;
    }
    public void updateAttendance() {
        this.attendance += 1;
    }
    public void updateLeaveEarly() {
        this.leaveEarly += 1;
    }
    public void updateOuting() {
        this.outing += 1;
    }
    public void updateVacationLeft() {
        this.vacationLeft -= 1;
    }
}
