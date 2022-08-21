package view;

import java.util.Objects;
import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu(){
        System.out.println("Welcome to online shop!!!");
        System.out.print("Enter your command(Enter help for get information about commands: ");
        String[] command = scanner.nextLine().split(" ");
        if(Objects.equals(command[0], "help") && command.length == 1){
            helpMenu();
        } else if(Objects.equals(command[0], "login")){
            if(Objects.equals(command[1], "admin")){

            }

        } else if(Objects.equals(command[0], "signup")){

        } else if(Objects.equals(command[0], "exit")){

        }
    }

    private void helpMenu() {
        System.out.println("login customer            (for excited account)");
        System.out.println("signup customer           (for new account)");
        System.out.println("login admin               (for excited account)");
        System.out.println("login seller              (for excited account)");
        System.out.println("signup seller             (for new account)");
        System.out.println("exit                      (for exit)");
    }

}