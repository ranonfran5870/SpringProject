package com.FlightsSystem.FlightsSystem.Poco;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ticket implements IPoco{

    public long id;
    public long flight_id;
    public long customer_id;
}
