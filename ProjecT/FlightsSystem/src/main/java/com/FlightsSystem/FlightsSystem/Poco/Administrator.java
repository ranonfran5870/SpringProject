package com.FlightsSystem.FlightsSystem.Poco;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class  Administrator implements IPoco {
    public int id;
    public String first_name;
    public String last_name;
    public long user_id;
}
