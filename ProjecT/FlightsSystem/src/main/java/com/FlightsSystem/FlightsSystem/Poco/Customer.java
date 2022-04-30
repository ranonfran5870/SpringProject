package com.FlightsSystem.FlightsSystem.Poco;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer implements IPoco {
    public long id;
    public String first_name;
    public String last_name;
    public String address;
    public String phone_no;
    public String credit_card_no;
    public long userId;
}
