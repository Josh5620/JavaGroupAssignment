package org.example;

class Main{

    public static void main(String[] args) {
        while(!User.verified) {
            User.Start();
        }
    }
}
