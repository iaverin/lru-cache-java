import com.iaverin.java.lrucache.LruCache;

public class App {
    public static void main(String[] args) throws Exception {

        final class User {
            public String id;
            private String login;

            User(String id, String login) {
                this.id = id;
                this.login = login;
            }

            public String getLogin() {
                return this.login;
            }
        }

        LruCache<User> lruCache = new LruCache<User>(10);
        System.out.println("Creating users and put them in stack...");
        for (int i = 0; i < 10; i++) {
            String userId = "user_id_" + String.valueOf(i);
            String userLogin = "user_login_" + String.valueOf(i);
    
            User user = new User(userId, userLogin);
            lruCache.putItem(userId, user);
        }
        lruCache.printStack();
        User userFromCache = lruCache.getItem("user_id_5");

        System.out.println("Get user from cache with id: " + userFromCache.id + " login:" + userFromCache.getLogin());
        lruCache.printStack();
        
        User userNew = new User("user_id_11", "user_login_11");
        System.out.println("Put " + userNew.id + " in cache");
        lruCache.putItem("user_id_11", userNew);
        lruCache.printStack();

        String userIdToRemove = "user_id_6";
        System.out.println("Remove user " + userIdToRemove +" from cache");
        System.out.println("is it removed: " + String.valueOf(lruCache.invalidateItem(userIdToRemove)));
        lruCache.printStack();

        userNew = new User("user_id_12", "user_login_12");
        System.out.println("Put " + userNew.id + " in cache");
        lruCache.putItem("user_id_12", userNew);
        lruCache.printStack();

        userFromCache = lruCache.getItem("user_id_3");
        System.out.println("Get user from cache with id: " + userFromCache.id + " login:" + userFromCache.getLogin());
        lruCache.printStack();

        userFromCache = lruCache.getItem("user_id_3");
        System.out.println("Get user from cache with id: " + userFromCache.id + " login:" + userFromCache.getLogin());
        lruCache.printStack();
    }
}
