package com.heinrian.labtodobackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todoItem")
public class TodoItem {

    @Id
    @Column(name = "todo", length = 1024, nullable = false, unique = true)
    private String todo;

    @Column(name = "priority")
    private int priority;

}