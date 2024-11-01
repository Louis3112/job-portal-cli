package source.utils.auth;

import source.data.user.User;
import source.utils.global.Global;

public class Auth {
    private User<?> loggedInUser;

    public Auth() {
        loggedInUser = null;
    }

    public void setLoggedInUser(User<?> loggedInUser) {this.loggedInUser = loggedInUser;}
    public User<?> getLoggedInUser() {return loggedInUser;}

    public void login() {
        System.out.println("===Login Menu====");

        String email;
        while (true) {
            System.out.print("\tEnter Your Email : ");
            email = Global.sc.nextLine();

            if (!Global.hashUsers.containsKey(email)) {
                System.out.println("\tError: Email didn't exist, Enter another email!");
                System.out.println("\tPress enter to retry...");
                Global.sc.nextLine();
                Global.clearConsole();
                continue;
            }

            break;
        }

        User<?> user = Global.hashUsers.get(email);
        setLoggedInUser(user);
        System.out.println("\nLogged in!");
        System.out.println("Press enter to continue...");
        Global.sc.nextLine();
    }
}
