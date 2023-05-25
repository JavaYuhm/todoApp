package com.todoapp.repository;

import com.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUseYnEqualsOrderByRegDateDesc(String useYn);
    List<Todo> findAllByUseYnEqualsOrderByRegDateAsc(String useYn);

    @Query(value = "UPDATE TODO SET USE_YN = 'N'",
            nativeQuery = true)
    void updateTodoAllClear();

}
