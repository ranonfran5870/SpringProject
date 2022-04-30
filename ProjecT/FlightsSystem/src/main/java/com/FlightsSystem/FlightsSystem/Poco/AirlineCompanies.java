package com.FlightsSystem.FlightsSystem.Poco;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirlineCompanies implements IPoco {
    public long id;
    public String name;
    public int country_id;
    public long user_id;
}
