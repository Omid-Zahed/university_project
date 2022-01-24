package app;

import java.util.HashMap;
import java.util.List;

public class Config {

    public static HashMap<String, String> options = new HashMap<String, String>();

    public static String getOption(String option_name) {
        if (options.containsKey(option_name)) {
            return options.get(option_name);
        }else {
            return "";
        }

    }

    public static boolean setOption(String option_name, String option_value) {
        if (options.containsKey(option_name)) {
            options.replace(option_name, option_value);
            return true;
        } else {
           options.put(option_name, option_value);
           return true;
        }

    }
}
