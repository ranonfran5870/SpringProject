package com.FlightsSystem.FlightsSystem.Poco;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Flight implements IPoco {

    public long id;
    public long airline_company_id;
    public int origin_country_id;
    public int destination_country_id;
    public Timestamp departure_time;
    public Timestamp landing_time;
    public int remaining_tickets;

    @Override
    public boolean equals(Object obj) {
        Flight other = (Flight) obj;
        return (id==other.id
                && airline_company_id==other.airline_company_id
                && origin_country_id==other.origin_country_id
                && destination_country_id==other.destination_country_id
                && departure_time.equals(other.departure_time)
                && landing_time.equals(other.landing_time)
                && remaining_tickets==other.remaining_tickets

        );

    }

}
