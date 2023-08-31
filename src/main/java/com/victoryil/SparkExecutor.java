package com.victoryil;

import lombok.AllArgsConstructor;
import org.apache.spark.sql.SparkSession;

@AllArgsConstructor
public class SparkExecutor implements Runnable {
    Configuration configuration;

    @Override
    public void run() {
        // Create spark session and read oracle table
        SparkSession sparkSession = SparkSession.builder().appName("OracleExecutor").master("local[*]").getOrCreate();
        sparkSession.read().format("jdbc")
                .option("driver", "oracle.jdbc.OracleDriver")
                .option("url", configuration.getUrl())
                .option("user", configuration.getUser())
                .option("password", configuration.getPassword())
                .option("dbtable", configuration.getDbTable())
                .load()
                .show();


    }
}
