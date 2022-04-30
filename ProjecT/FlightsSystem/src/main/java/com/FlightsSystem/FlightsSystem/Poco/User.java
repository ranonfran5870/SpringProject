package com.FlightsSystem.FlightsSystem.Poco;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements IPoco {
    public long id;            ////////////////if have problem cheack
    public String username;
    public String password;
    public String email;
    public int user_role;
    public String thumbnail;
}
