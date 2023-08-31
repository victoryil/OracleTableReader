package com.victoryil;

public class Driver {
    private static boolean isOracle = false;
    public static void main(String[] args) {
        Configuration configuration = parseArgs(args);

        if (!isOracle) new SparkExecutor(configuration).run();
        else new JDBCExecutor(configuration).run();
    }

    private static Configuration parseArgs(String[] args) {
        Configuration configuration = new Configuration();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-u":
                    configuration.setUrl(args[i + 1]);
                    break;
                case "-p":
                    configuration.setPassword(args[i + 1]);
                    break;
                case "-q":
                    configuration.setQuery(args[i + 1]);
                    break;
                case "-t":
                    configuration.setDbTable(args[i + 1]);
                    break;
                case "-U":
                    configuration.setUser(args[i + 1]);
                    break;
                case "-o":
                    isOracle = true;
                    break;
            }
        }
        configuration.validate();
        return configuration;
    }
}
