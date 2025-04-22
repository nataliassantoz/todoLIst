package com.example.todolist.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todolist.entity.Todo;
import com.example.todolist.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping()
    public ResponseEntity<Todo> criar(@RequestBody Todo todo){
        Todo criado = todoService.criarTodo(todo);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public List<Todo> listarTodos(){
        return todoService.listarTodo();
    }

    @GetMapping("/pendentes")
    public List<Todo> listarTodosPendentes(){
        return todoService.listarTodosPendentes();
    }

    @GetMapping("/em-andamento")
    public List<Todo> listarTodosEmAndamento(){
        return todoService.listarTodosEmAndamento();
    }

    @GetMapping("/finalizados")
    public List<Todo> listarTodosFinalizados(){
        return todoService.listarTodosFinalizados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> buscarTodo(@PathVariable Long id){
    Optional<Todo> todo = todoService.buscarTodo(id);
    
        if(todo.isPresent()){
            return ResponseEntity.ok(todo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> AtualizarTodo(@PathVariable Long id, @RequestBody Todo todoAtualizada){
        Todo todoAtualizado = todoService.atualizarTodo(id, todoAtualizada);

        if (todoAtualizado != null){
            return ResponseEntity.ok(todoAtualizado);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/concluit")
    public ResponseEntity<String> marcarTodoConcluida( @PathVariable Long id){
        todoService.marcarTodoConcluida(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/em-andamento")
    public ResponseEntity<String> marcarTodoEmAndamento(@PathVariable Long id){
        todoService.marcarTodoEmAndamento(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTodo(@PathVariable Long id){
        todoService.excluirTarefa(id);
        return ResponseEntity.noContent().build();

    }
}
