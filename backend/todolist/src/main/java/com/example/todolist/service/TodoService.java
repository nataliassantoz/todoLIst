package com.example.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.todolist.entity.Todo;
import com.example.todolist.enums.Status;
import com.example.todolist.repository.TodoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> criarTodo (Todo  todo){
        todoRepository.save(todo);
        return listarTodo();
    }
    public List<Todo> listarTodo() {
        return todoRepository.findAll(Sort.by("titulo").ascending());
    }
    

    public List<Todo> listarTodosPendentes() {
        return todoRepository.findByStatus(Status.PENDENTE, Sort.by("titulo").ascending());
    }
    
    public List<Todo> listarTodosEmAndamento() {
        return todoRepository.findByStatus(Status.EM_ANDAMENTO, Sort.by("titulo").ascending());
    }
    
    public List<Todo> listarTodosFinalizados() {
        return todoRepository.findByStatus(Status.REALIZADO, Sort.by("titulo").ascending());
    }
    

    public Optional<Todo> buscarTodo(Long id){
        Optional todoEncontrada = todoRepository.findById(id);
        if(todoEncontrada.isPresent()){
            return todoEncontrada;
        }
        else{
            return Optional.empty();
        }
    }

    public Todo atualizarTodo(Long id, Todo todoAtualizada){
        Optional<Todo> todoEncontrado = buscarTodo(id);

        if(todoEncontrado.isPresent()){

            Todo todoExistente = todoEncontrado.get(); 
            todoExistente.setTitulo(todoAtualizada.getTitulo());
            todoExistente.setDescricao(todoAtualizada.getDescricao());
            todoExistente.setStatus(todoAtualizada.getStatus());

           return todoRepository.save(todoExistente);

        }
        else{
            System.out.println("Todo com ID " + id + " não encontrado.");
            return null;
        }

    }

    public void marcarTodoConcluida(Long id){
        Optional<Todo> todoEncontrado = buscarTodo(id);

        if(todoEncontrado.isPresent()){
            Todo todoExistente = todoEncontrado.get(); 
            
            todoExistente.setStatus(Status.REALIZADO);
        }
    }
    public void marcarTodoEmAndamento(Long id){
        Optional<Todo> todoEncontrado = buscarTodo(id);

        if(todoEncontrado.isPresent()){
            Todo todoExistente = todoEncontrado.get(); 
            
            todoExistente.setStatus(Status.EM_ANDAMENTO);
        }
    }

    public void excluirTarefa(Long id){
        todoRepository.deleteById(id);
    }
  
 }
