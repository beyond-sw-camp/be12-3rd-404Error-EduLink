package com.example.package404.instructor.controller;


import com.example.package404.instructor.model.dto.req.HomeworkReqDto;
import com.example.package404.instructor.service.HomeworkService;
import com.example.package404.user.model.User;
import com.example.package404.user.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructor/homework")

public class HomeworkController {


    private HomeworkService homeworkService;

    private UserRepository userRepository;

    @PostMapping("/create/{courseIdx}")
    public void createHomework(/*@AuthenticationPrincipal User user , */ @PathVariable Long courseIdx, @RequestBody HomeworkReqDto homeworkReqDto) {


        homeworkService.homeworkCreate(courseIdx , homeworkReqDto);
    }

    @GetMapping("/list")
    public void listHomework(@PathVariable Long courseIdx) {

        homeworkService.findAll();


    }


//    @GetMapping("/edit")

}
