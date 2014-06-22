package main;

import java.sql.SQLException;
import java.util.Date;

import util.ConcurrentLRUCache;
import util.FakeDBAccess;

/**
 * Class Name: LoginDataHelpher.java
 * 
 * Class implementing LoginService interface to check whether the user has
 * logged in within the last 24 hours
 * 
 * @author Sumanth Lakshminarayana, UTA ID: 1000830230
 */

public class LoginDataHelpher implements LoginService {
	ConcurrentLRUCache<String, Date> cache = new ConcurrentLRUCache<String, Date>(1000);
	FakeDBAccess serverData = new FakeDBAccess();
	
	// @Override
		public boolean hasUserLoggedInWithin24(String userId) {
			Date startDate = null;
			if (cache.hasKey(userId)) {
				startDate = cache.get(userId);
			} else {
				startDate = serverData.getLastLoginForUser(userId);
				//Update cache
				cache.put(userId, startDate);
			}
			Date endDate = new Date();
			if(dateCheck24(startDate, endDate))
				return true;
			else{
				cache.removeOldestByTime(userId);
				return false;
			}
		}
		
		// @Override
		public void userJustLoggedIn(String userId) {
			Date date = new Date();
			//Update cache
			cache.put(userId, date);
			try {
				serverData.setLastLoginForUser(userId, date);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * Returns true if the time difference is less than 24 hours 
		 * else returns false
		 * 
		 * @param userId
		 */
		public boolean dateCheck24(Date startDate, Date endDate) {
			long hours = (endDate.getTime() - startDate.getTime()) / 1000 * 3600;
			if (hours < 24) return true;
			return false;
		}
}
