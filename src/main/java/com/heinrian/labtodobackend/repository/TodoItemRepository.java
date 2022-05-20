package com.heinrian.labtodobackend.repository;

import com.heinrian.labtodobackend.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, String> {

}