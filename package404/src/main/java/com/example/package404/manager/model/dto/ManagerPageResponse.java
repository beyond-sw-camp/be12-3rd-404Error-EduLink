package com.example.package404.manager.model.dto;

import com.example.package404.user.model.User;
import org.springframework.data.domain.Page;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ManagerPageResponse {
    private List<ManagerResponseDto> content;
    private int currentPage;
    private int totalPages;
    private long totalElements;

    public static ManagerPageResponse from(Page<User> page) {
        ManagerPageResponse response = new ManagerPageResponse();
        response.setContent(page.getContent().stream().map(ManagerResponseDto::of).collect(Collectors.toList()));
        response.setCurrentPage(page.getNumber());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        return response;
    }
}
