package com.amazingJava.myFirstWebApp.ToDo;

import java.time.LocalDate;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class todoController {



    private final toDoService toDoService;
    public todoController(){
        super();
        this.toDoService = new toDoService();
    }

    //##################### show the TodoS List ##################
    //url: todo-list
    @RequestMapping("todo-list")
    public String toDoList(ModelMap model){
        List<toDo> toDos = toDoService.findByUserName("jewel");
        model.addAttribute("toDos",toDos);
        return "listTodo";
    }

    //################ Add new ToDos  ####################

    //GET, POST
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String userName = (String) model.get("name");
        toDo todo = new toDo(0, userName, "Default Desc", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);   //bound with the toDo.jsp attributes ,thats why agei initialize kore nite hobe
        return "toDo";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model,@Valid toDo todo,BindingResult result) {
        if(result.hasErrors()){
            return  "toDo";
            //return "redirect:add-todo";
        }

        String username = (String)model.get("name");
        toDoService.addToDos(username, todo.getDescription(),
                todo.getTargetDate(), false);
        return "redirect:todo-list";  //show controller
    }

    // ###################### Delete #######################
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id){
        //delete a row by id
        toDoService.deleteById(id);
        return "redirect:todo-list";
    }



    //##################### Update ########################
    @RequestMapping(value="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id,ModelMap model) {
        toDo todo = toDoService.findById(id);
        model.addAttribute("todo", todo);
        return "toDo";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid toDo todo, BindingResult result) {

        if(result.hasErrors()) {
            return "toDo";
        }

        String username = (String)model.get("name");
        toDoService.updateToDo(todo);
        return "redirect:todo-list";
    }
}
