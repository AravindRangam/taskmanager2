package com.taskList.employee.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.taskList.employee.Service.TaskService;
import com.taskList.employee.entity.Task;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/tasks")
public class TaskController {

	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> tasks = taskService.getAllTasks();
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		Optional<Task> task = taskService.getTaskById(id);
		return task.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		Task createdTask = taskService.createTask(task);
		return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
		Task updatedTask = taskService.updateTask(id, task);
		return new ResponseEntity<>(updatedTask, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
