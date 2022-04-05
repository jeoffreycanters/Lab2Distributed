import java.io.IOException;
public class Bank_Client {
    //HERE WE TEST OUR SERVER / CLIENT
    static int getBalance(String user){
        int balance = 0;
        try {
            balance = Integer.parseInt((String)REST_Client.GET("http://localhost:8080/bank/users/" + user));
            //so we GET http://localhost.8080/bank/users/user
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        return balance;
    }

    static int putBalance(String user, String body ) {
        int balance = 0;
        try {
            balance = Integer.parseInt((String)REST_Client.PUT("http://localhost:8080/bank/users/" + user, body));
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return balance;
    }
    static long newUser(String user, String body){
        try{
            return Long.parseLong((String)REST_Client.POST("http://localhost:8080/bank/users/" + user,body));
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    static void deleteUser(String user){
        try {
            REST_Client.DELETE("http://localhost:8080/bank/users/" + user);
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args){
        System.out.println("Starting BankApp.Bank_Client...");
        System.out.println("New Client jeoffrey: POST /bank/users/jeoffrey with body: " + Bank_Client.newUser("jeoffrey", "50"));
        System.out.println("PUT /bank/users/jeoffrey 200 , balance: " + Bank_Client.putBalance("jeoffrey","200"));
        System.out.println("PUT /bank/users/jeoffrey -75, balance: " + Bank_Client.putBalance("jeoffrey","-75"));
        System.out.println("GET /bank/users/jeoffrey, balance: " + Bank_Client.getBalance("jeoffrey"));
        System.out.println("DELETE /bank/users/jeoffrey");
        Bank_Client.deleteUser("jeoffrey");
    }
}
