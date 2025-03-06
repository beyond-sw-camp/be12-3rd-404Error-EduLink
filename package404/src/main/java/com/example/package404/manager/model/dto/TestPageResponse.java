package com.example.package404.manager.model.dto;

import com.example.package404.manager.model.Test;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

@Data
public class TestPageResponse {
    private List<TestResponseDto> content;
    private int currentPage;
    private int totalPages;
    private long totalElements;

    public static TestPageResponse from(Page<Test> page) {
        TestPageResponse response = new TestPageResponse();
        response.setContent(page.getContent().stream()
                .map(TestResponseDto::of)
                .collect(Collectors.toList()));
        response.setCurrentPage(page.getNumber());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        return response;
    }
}
