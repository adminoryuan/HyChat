package com.HyChat.server.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String Admin;
    String Password;
    String Name;
    boolean sex;
}
