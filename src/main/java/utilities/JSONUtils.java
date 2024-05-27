package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JSONUtils {


    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> T fromJson(String jsonFilePath, Type type) {
        try (FileReader reader = new FileReader(jsonFilePath)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void toJson(Object data, String jsonFilePath) {
        try (FileWriter writer = new FileWriter(jsonFilePath)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValueFromJson(String jsonFilePath, String key) {
        Map<String, Map<String, String>> data = fromJson(jsonFilePath, new com.google.gson.reflect.TypeToken<Map<String, Map<String, String>>>(){}.getType());
        Map<String, String> urls = data != null ? data.get("urls") : null;
        return urls != null ? urls.get(key) : null;
    }


}
