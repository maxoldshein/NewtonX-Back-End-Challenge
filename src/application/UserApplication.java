package application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import service.UserServiceImplementation;

public class UserApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	
	public UserApplication() {
		singletons.add(new UserServiceImplementation());
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
