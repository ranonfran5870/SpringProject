package com.FlightsSystem.FlightsSystem.MongoDB.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document("logCount")
public class LogCount {

        @Transient
        public static final String SEQUENCE_NAME = "users_sequence";
        @Id
        private long id;
        private LocalDateTime date;

        @Override
        public String toString() {
                return "LogCount{" +
                        "id=" + id +
                        ", date=" + date +
                        '}';
        }
}
