package com.victoryil;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Data
@NoArgsConstructor
@Log4j
public class Configuration {
//Configuration to read oracle table in Spark
    private String url; //required
    private String user; //required
    private String password; //required
    private String query; //required or dbTable
    private String dbTable; //required or query

    public void validate() {
        log.info("Validating configuration");
        log.info(toString());
        System.out.println(this);
        // Validate if all required fields are present separated
        if(url == null ) {
            throw new IllegalArgumentException("url is required");
        }
        if(user == null ) {
            throw new IllegalArgumentException("user is required");
        }
        if(password == null ) {
            throw new IllegalArgumentException("password is required");
        }
        // if query is not present, then dbTable is required
        if(query == null && dbTable == null) {
            throw new IllegalArgumentException("query or dbTable is required");
        }
        // if query is present, then dbTable is not required
        if(query != null && dbTable != null) {
            throw new IllegalArgumentException("query and dbTable are mutually exclusive");
        }

    }
}
