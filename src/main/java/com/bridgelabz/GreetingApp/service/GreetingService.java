package com.bridgelabz.GreetingApp.service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.GreetingApp.model.Greeting;
import com.bridgelabz.GreetingApp.model.User;
import com.bridgelabz.GreetingApp.repository.GreetingRepository;

@Service
public class GreetingService implements IGreetingService{

	private String template = "Hello, %s";
	private AtomicLong counter = new AtomicLong();

	@Autowired
	private GreetingRepository greetingRepository;
	
	@Override
    public Greeting greetingMessage() {
        return new Greeting(counter.incrementAndGet(), String.format(template));
    }

	@Override
	public Greeting addGreeting(User user) {
		String message = String.format(template, user.toString().isEmpty() ? "World" : user.toString());
		return greetingRepository.save(new Greeting(counter.incrementAndGet(), message));
	}

	@Override
	public Greeting getGreetingById(long id) {
		Optional<Greeting> greeting = greetingRepository.findById(id);
		if (greeting.isPresent()) {
			return greeting.get();
		}
		return null;
	}

	@Override
	public List<Greeting> getAllGreeting() {
		return greetingRepository.findAll();
	}

	@Override
	public void deleteGreeting(long id) {
		greetingRepository.deleteById(id);
	}

	@Override
	public Greeting updateGreeting(long id, User user) {
		Optional<Greeting> greetingOptional = greetingRepository.findById(id);
		Greeting greeting = greetingOptional.get();
		String message = String.format(template, user.toString().isEmpty() ? "World" : user.toString());
		greeting.setMessage(message);
		return greetingRepository.save(greeting);
	}
}