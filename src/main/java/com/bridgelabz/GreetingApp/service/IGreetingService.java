package com.bridgelabz.GreetingApp.service;

import com.bridgelabz.GreetingApp.model.Greeting; 
import java.util.List;
import com.bridgelabz.GreetingApp.model.User;

public interface IGreetingService {
	Greeting greetingMessage();
	Greeting addGreeting(User user);
	Greeting getGreetingById(long id);
	List<Greeting> getAllGreeting();
	void deleteGreeting(long id);
	Greeting updateGreeting(long id, User user);
}