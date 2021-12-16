package com.bridgelabz.GreetingApp.controller;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.GreetingApp.model.Greeting;
import com.bridgelabz.GreetingApp.model.User;
import com.bridgelabz.GreetingApp.service.GreetingService;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value={"/greeting","/greeting/",""})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
	@Autowired
	private GreetingService greetingService;
	
	@GetMapping("greeting/service")
    public Greeting greeting() {
        return greetingService.greetingMessage();
    }
	
	@GetMapping("/getGreeting/{id}")
	public Greeting getGreeting(@PathVariable long id) {
		return greetingService.getGreetingById(id);
	}

	@PostMapping("/addGreeting")
	public Greeting addGreeting(@RequestParam(value = "firstName", defaultValue = "World") String firstName,
			@RequestParam(value = "lastName", defaultValue = "") String lastName) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return greetingService.addGreeting(user);
	}	

	@GetMapping("/getAllGreeting")
	public List<Greeting> getAllGreeting() {
		return greetingService.getAllGreeting();
	}

	@DeleteMapping("/deleteGreeting/{id}")
	public void deleteGreeting(@PathVariable long id) {
		greetingService.deleteGreeting(id);
	}

	@PutMapping("/updateGreeting/{id}/{firstName}/{lastName}")
	public Greeting updateGreeting(@PathVariable long id, @PathVariable String firstName,
			@PathVariable String lastName) {
		User user = new User(firstName, lastName);
		return greetingService.updateGreeting(id, user);
	}
}