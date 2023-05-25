package com.todoapp.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TodoDto {
    private String item;
    private String date;
    private String time;

    private boolean completed;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public Todo toEntity(){
        return Todo.builder()
                .item(item)
                .date(date)
                .time(time)
                .completed(completed)
                .regDate(regDate)
                .modDate(modDate)
                .build();
    }
}
