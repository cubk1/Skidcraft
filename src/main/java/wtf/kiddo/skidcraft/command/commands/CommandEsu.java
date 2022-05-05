package wtf.kiddo.skidcraft.command.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.src.CommandException;
import wtf.kiddo.skidcraft.command.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class CommandEsu extends Command {
    public CommandEsu() {
        super("esu", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        try {
            if (args.length == 0) {
                return ".esu <QQ>";
            }
            String jsonData = sendGet("https://zy.xywlapi.cc/qqcx?qq=" + args[0], null);
            jsonData = deleteCharInString(jsonData, '\n');
            jsonData = deleteCharInString(jsonData, '\r');
            jsonData = deleteCharInString(jsonData, ' ');
            try {
                String mobile = getJsonData(jsonData, "phone");
                String Check = "QQ:"+args[0] +", 电话:" + mobile;
                Check = deleteCharInString(Check, '\"');
                return Check;
            } catch (Exception e) {
                return "这个主播可能年龄比较小没有入库";
            }
        } catch (Exception e) {
            return e.toString();
        }
    }
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setDoOutput(true);
            connection.setReadTimeout(99781);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String str : map.keySet()) ;
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result = String.valueOf(result) + line;
            }
        } catch (Exception exception) {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ignored) {
            }
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    public static String getJsonData(String jsonString, String targetString) {
        JsonParser parser = new JsonParser();
        JsonElement jsonNode = parser.parse(jsonString);
        if (jsonNode.isJsonObject()) {
            JsonObject jsonObject = jsonNode.getAsJsonObject();
            JsonElement jsonElementId = jsonObject.get(targetString);
            return jsonElementId.toString();
        }
        return null;
    }

    public static String deleteCharInString(String str, char delChar) {
        StringBuilder delStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != delChar) {
                delStr.append(str.charAt(i));
            }
        }
        return delStr.toString();
    }
}

