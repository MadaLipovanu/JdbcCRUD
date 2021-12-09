package com.company.service;

import com.company.model.MySqlAccess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(" Operations you can do on database: ");
        System.out.println("1. Create ");
        System.out.println("2. Read ");
        System.out.println("3. Update ");
        System.out.println("4. Delete ");
        System.out.println("Your option is: ");
        Scanner sc = new Scanner(System.in);
        int choise = sc.nextInt();
        MySqlAccess read = new MySqlAccess();
        // switch statement with int data type
        switch (choise) {

            case 1:
                read.create();
                break;
            case 2:
                read.read();
                break;
            case 3:
                read.update();
                break;
            case 4:
                read.delete();
                break;
            default:
                System.out.println("Invalid method");

        }
    }
}



