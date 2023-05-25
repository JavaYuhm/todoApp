package com.todoapp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DynamicInsert
@Getter
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private String date;

    private String time;
    @Setter
    private boolean completed;

    @Setter
    private LocalDateTime regDate;
    @Setter
    private LocalDateTime modDate;


    @Setter
    @Column(columnDefinition = "varchar(1) default 'Y'")
    private String useYn;

    @Builder
    Todo(String item, String date, String time, boolean completed, LocalDateTime regDate, LocalDateTime modDate){
        this.item = item;
        this.time = time;
        this.date = date;
        this.completed = completed;
        this.regDate = regDate;
        this.modDate = modDate;

    }

}
