ConcurrentLRUCache.java
A Simple Cache library to store user ID<String>-key and Date-value pair

Attributes of the current system:
Maximum Size: Holds up to 1000 UserID , preferably users having login time less than 24 hours

Functionality:
1.	Store the UserID  in cache, Also if the size has reached its maximum then remove the least recently used data, i.e. the first element pushed in the queue is removed
2.	Get the userID and Date pair if it exists
3.	Check if the user exists in the cache.
4.	Remove the specified user ID (login time>24 hrs)

LoginDataHelpher.java

Attributes
Cache and server database instance.

Functionality:
1.	hasUserLoggedInWithin24 method takes userID checks 
	a)	If the user ID exists in the cache, then retrieve the user and his login time and send it to the dateCheck24 which returns true if the time difference is less than 24 hours  else returns false
	b)	If the dateCheck24 returns false, then remove the user ID from the cache
	c)	If the user ID doesn’t exist in the cache, then retrieve the user and his login time from the server database and add it in the cache and send it to dateCheck24.

2.	userJustLoggedIn(String userId) method takes userID checks
	a)		When the user logs into system add the userID and current login time in cache. Update the sever database also.

