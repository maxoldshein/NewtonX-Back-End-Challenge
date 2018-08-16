package client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import model.User;

public class UserTestClient {
	public static void main(String[] args) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		//POST Example
		//Add the first user (firstName: "Maxwell", lastName: "Oldshein")
		System.out.println("Adding the first user...");
		
		ResteasyWebTarget add = client.target("http://localhost:8080/NewtonXBackEndChallenge/user/add");
		
		User userOne = new User();
		userOne.setFirstName("Maxwell");
		userOne.setLastName("Oldshein");
		
		Response addResponse = add.request().post(Entity.entity(userOne, MediaType.APPLICATION_JSON));
		
		String addValue = addResponse.readEntity(String.class);
		System.out.println(addValue);
		System.out.println();
		
		addResponse.close();
		
		//POST Example
		//Add a second user (firstName: "Steve", lastName: "Jobs")
		System.out.println("Adding the second user...");
		
		User userTwo = new User();
		userTwo.setFirstName("Steve");
		userTwo.setLastName("Jobs");
		
		addResponse = add.request().post(Entity.entity(userTwo, MediaType.APPLICATION_JSON));
		
		addValue = addResponse.readEntity(String.class);
		System.out.println(addValue);
		System.out.println();
		
		addResponse.close();
		
		//POST Example
		//Try to add a user with no first name; should get rejected.
		System.out.println("Adding a user with no first name that should return an error...");
		
		User firstNameErrorUser = new User();
		firstNameErrorUser.setLastName("Oldshein");
		
		addResponse = add.request().post(Entity.entity(firstNameErrorUser, MediaType.APPLICATION_JSON));
		
		addValue = addResponse.readEntity(String.class);
		System.out.println(addValue);
		System.out.println();
		
		addResponse.close();
		
		//POST Example
		//Try to add a user with no last name; should get rejected.
		System.out.println("Adding a user with no last name that should return an error...");
		
		User lastNameErrorUser = new User();
		lastNameErrorUser.setFirstName("Maxwell");
		
		addResponse = add.request().post(Entity.entity(lastNameErrorUser, MediaType.APPLICATION_JSON));
		
		addValue = addResponse.readEntity(String.class);
		System.out.println(addValue);
		System.out.println();
		
		addResponse.close();
		
		//POST Example
		//Add a third user (firstName: "Jeff", lastName: "Bezos")
		System.out.println("Adding the third user...");
		
		User userThree = new User();
		userThree.setFirstName("Jeff");
		userThree.setLastName("Bezos");
		
		addResponse = add.request().post(Entity.entity(userThree, MediaType.APPLICATION_JSON));
		
		addValue = addResponse.readEntity(String.class);
		System.out.println(addValue);
		System.out.println();
		
		addResponse.close();
		
		//GET Example
		//Get the user with ID = 1, should return the user with first name "Maxwell", last name "Oldshein".
		System.out.println("Getting the user with ID = 1");
		
		ResteasyWebTarget get = client.target("http://localhost:8080/NewtonXBackEndChallenge/user/1/get");
		
		Response getResponse = get.request().get();
		
		String getValue = getResponse.readEntity(String.class);
		System.out.println(getValue);
		System.out.println();
		
		getResponse.close();
		
		//GET Example
		//Get all the currentUsers.
		System.out.println("Getting all the current users...");
		
		ResteasyWebTarget getAllUsers = client.target("http://localhost:8080/NewtonXBackEndChallenge/user/getAllUsers");
		
		Response getAllUsersResponse = getAllUsers.request().get();
		
		String getAllUsersValue = getAllUsersResponse.readEntity(String.class);
		System.out.print(getAllUsersValue);
		System.out.println();
		
		getAllUsersResponse.close();
	}
}
