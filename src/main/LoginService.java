package main;

/**
 * Class Name: ConcurrentLRUCache.java
 * 
 * Checks whether the user has logged in within the last 24 hours
 * 
 * @author Sumanth Lakshminarayana, UTA ID: 1000830230
 */
public interface LoginService {

	/**
	 * @param userId
	 * @return True if the user HAS logged in within 24 hours False otherwise
	 */
	public boolean hasUserLoggedInWithin24(String userId);

	/**
	 * Sets the last login time for the user to now.
	 * 
	 * @param userId
	 */
	public void userJustLoggedIn(String userId);
}
