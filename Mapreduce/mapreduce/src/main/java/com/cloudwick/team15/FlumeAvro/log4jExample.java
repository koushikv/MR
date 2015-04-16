package com.cloudwick.team15.FlumeAvro;

/**
 * Created by kaushik on 3/30/15.
 */
import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class log4jExample {

    static Logger log = Logger.getRootLogger();

    public static void main(String[] args) throws IOException, SQLException {

        for (int i = 1; i <= 10; i++) {

            log.info("Apple is:" + i * 2);
            log.info("Samsung is:" + i);

        }
    }
}