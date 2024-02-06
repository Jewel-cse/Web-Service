package com.amazingJava.myFirstWebApp.ToDo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class toDoService {
    private static List<toDo> toDos = new ArrayList<toDo>();
    private  static  int todoCount = 0;
    static {
        toDos.add(new toDo(++todoCount,"jewel","learn AWS",
                LocalDate.now().plusYears(1),false));
        toDos.add(new toDo(++todoCount,"jewel","learn Spring",
                LocalDate.now().plusYears(3),false));
        toDos.add(new toDo(++todoCount,"jewel","learn Security",
                LocalDate.now().plusYears(2),true));

    }

    public List<toDo> findByUserName(String userName){
        return toDos;
    }

    public void addToDos(String name,String description,LocalDate targetDate,boolean done){
        toDos.add(new toDo(++todoCount,name,description,targetDate,done));
    }
    public void deleteById(int id){
        Predicate<? super toDo> predicate = toDo -> toDo.getId() == id;
        toDos.removeIf(predicate);
    }

    public toDo findById(int id) {
        Predicate<? super toDo> predicate = toDo -> toDo.getId() == id;
        toDo todo = toDos.stream().filter(predicate).findFirst().get();

        return todo;
    }

    public void updateToDo(toDo todo) {
        //delete by id
        //add new todo
        deleteById(todo.getId());
        addToDos(todo.getUserName(),todo.getDescription(),todo.getTargetDate(),todo.isDone());
    }
}
