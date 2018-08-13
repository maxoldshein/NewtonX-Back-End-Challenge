package service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.User;
import model.GenericResponse;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserServiceImplementation implements UserService {
	//A map of unique identifiers and their represented users to keep track of the current users in the system.
	private Map<Integer, User> users = new HashMap<Integer, User>();
	//The variable currentId keeps track of the current unique identifier, so it can be assigned to a new user when they are created.
	private static int currentId = 1;
	
	@Override
	@POST
	@Path("/add")
	//Method to create a new user, accepts a JSON payload with first name and last name, and return a new user with a unique identifier.
	//If an invalid name is contained in the JSON payload (first name left blank/null or last name left blank/null), a user with an ID of -1 is returned to indicate an error.
	public User addUser(User user) {
		//If a user is added without a first or last name, return a user with an ID of -1 to indicate an error.
		//Due to the error, the user is not added to the current users map.
		if (user.getFirstName() == "" || user.getLastName() == "" || user.getFirstName() == null || user.getLastName() == null) {
			user.setId(-1);
			user.setFirstName("USER WAS NOT ADDED; INVALID NAME: FIRST OR LAST NAME NOT PROVIDED!");
			user.setLastName("USER WAS NOT ADDED; INVALID NAME: FIRST OR LAST NAME NOT PROVIDED!");
			
			//Return the user with an ID of -1 to indicate an error.
			return user;
		}
		
		//Set the user's ID to the current unique identifier.
		user.setId(currentId);
		
		//This check is to make sure that no two users have the same unique identifier. It is in place to protect againt a case where the currentId variable is not being incremented for some reason.
		//In the rare case that the currentId already exists in the list of current users, return a user with an ID of -1 to indicate an error.
		//Due to the error, the user is not added to current users map.
		if (users.get(user.getId()) != null) {
			user.setId(-1);
			user.setFirstName("USER WAS NOT ADDED; USER SPECIFIED  ID ALREADY EXISTS!");
			user.setLastName("USER WAS NOT ADDED; USER SPECIFIED  ID ALREADY EXISTS!");
			
			//Return the user with an ID of -1 to indicate an error.
			return user;
		}
		
		//Add the user to the map of current users.
		users.put(currentId, user);
	
		//Increment the unique identifier so the next user gets a unique identifier.
		currentId++;
		
		//Return the user that was just created, with the unique identifier that was assigned to it.
		return user;
	}
	
	@Override
	@DELETE
	@Path("/{id}/delete")
	//Method to delete a user with a specific ID.
	public Response deleteUser(@PathParam("id") int id) {
		GenericResponse response = new GenericResponse();
		
		//If the user's unique identifier is not in the map of current users, return a Response with a status of false, an error message, and an error code.
		if (users.get(id) == null) {
			response.setStatus(false);
			response.setMessage("FAILED TO DELETE USER: USER WITH ID = " + id + " DOES NOT EXIST!");
			response.setErrorCode("USER_DOES_NOT_EXIST");
			
			return Response.status(422).entity(response).build();
		}
		
		//Delete the user from the map of current users.
		users.remove(id);
		
		response.setStatus(true);
		response.setMessage("User with ID = " + id + " successfully deleted.");
		response.setErrorCode(null);
		
		return Response.ok(response).build();
	}
	
	@Override
	@GET
	@Path("/{id}/get")
	//Method to return a user with a specific ID.
	//If the user does not exist in the current user map, then a user with an ID of -1 is returned to indicate an error.
	public User getUser(@PathParam("id") int id) {
		//If a user with the requested ID does not exist, return a user with the ID -1 to indicate there is no user with the provided ID.
		if (users.get(id) == null) {
			User user = new User();
			
			user.setId(-1);
			user.setFirstName("User does not exist.");
			user.setLastName("User does not exist.");
			
			return user;
		}
		
		//Return the user with the provided ID from the map of current users.
		return users.get(id);
	}
	
	@Override
	@GET
	@Path("/getAllUsers")
	//Method to return all current users as JSON.
	public User[] getAllUsers() {
		Set<Integer> userIds = users.keySet();
		User[] allUsers = new User[userIds.size()];
		int index = 0;
		
		//Add each user in the map of current users to an array.
		for (Integer id : userIds) {
			allUsers[index] = users.get(id);
			index++;
		}
		
		//Return the array of all current users.
		return allUsers;
	}
}
