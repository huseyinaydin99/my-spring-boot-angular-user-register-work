package com.user.restapi.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.impl.UserImpl;
import com.user.restapi.UserController;
import com.user.service.IUserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserControllerImpl implements UserController {

	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@GetMapping(path = "/test")
	public String test() {
		return "test";
	}
	
	@PostMapping(path = "/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public void add(@RequestBody UserImpl user) {
		System.out.println(user);
		this.userService.save(user);
		System.out.println("add çalıştı");
	}

	//@PostMapping(path = "/delete",consumes = "application/json", produces = "application/json")
	
	
	@DeleteMapping(path = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public boolean delete(@RequestBody UserImpl user) {
		return this.userService.delete(user);
	}

	//@PostMapping(path = "/update",consumes = "application/json", produces = "application/json")
	@PutMapping(path = "/update",consumes = "application/json", produces = "application/json")
	@Override
	public boolean update(@RequestBody UserImpl user) {
		return this.userService.update(user);
	}

	@GetMapping(path = "/getUser",consumes = "application/json", produces = "application/json")
	@Override
	public UserImpl get(@RequestBody UserImpl user) {
		return this.userService.get(user);
	}

	@GetMapping(path = "/getId/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public UserImpl get(@PathVariable("id")Long id) {
		return this.userService.get(id);
	}

	@GetMapping(path = "/getAll")
	@Override
	public List<UserImpl> getAll() {
		return this.userService.getAll();
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public boolean delete(@PathVariable("id")Long id) {
		return this.userService.delete(id);
	}

}
