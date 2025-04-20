package com.example.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todolist.entity.Todo;
import com.example.todolist.enums.Status;
import org.springframework.data.domain.Sort;

public interface TodoRepository extends JpaRepository<Todo, Long>{

    List<Todo> findByStatus(Status status, Sort sort);
    }
