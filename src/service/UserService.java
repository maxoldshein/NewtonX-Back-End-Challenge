package service;

import javax.ws.rs.core.Response;

import model.User;

//Interface to definite methods that the API must implement.
public interface UserService {
	//Method to create a new user.
	public User addUser(User user);
	
	//Method to delete a specific user by their unique ID.
	public Response deleteUser(int id);
	
	//Method to retrieve a specific user by their unique ID.
	public User getUser(int id);
	
	//Method to get all the current users.
	public User[] getAllUsers();
}
