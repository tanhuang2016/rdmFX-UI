package atlantafx.xyz.hashdog.rdm.ui;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class DownloadCount {
    /**
     * 获取GitHub仓库的总下载量
     * @param owner 仓库所有者
     * @param repo 仓库名称
     * @return 下载总量
     */
    public long getGitHubDownloadCount(String owner, String repo) {
        try {
            String apiUrl = String.format("https://api.github.com/repos/%s/%s/releases", owner, repo);
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");
            connection.setRequestProperty("User-Agent", "Java-DownloadCount-App");

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("GitHub API request failed with code: " + responseCode);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonArray releases = JsonParser.parseString(response.toString()).getAsJsonArray();
            long totalDownloads = 0;

            for (int i = 0; i < releases.size(); i++) {
                JsonObject release = releases.get(i).getAsJsonObject();
                JsonArray assets = release.getAsJsonArray("assets");

                for (int j = 0; j < assets.size(); j++) {
                    JsonObject asset = assets.get(j).getAsJsonObject();
                    totalDownloads += asset.get("download_count").getAsLong();
                }
            }

            return totalDownloads;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取RedisFX项目的下载量
     * @return 下载总量
     */
    public long getRedisFXDownloadCount() {
        return getGitHubDownloadCount("tanhuang2016", "RedisFX");
    }

    public static void main(String[] args) {
        DownloadCount downloadCount = new DownloadCount();
        long downloadCount1 = downloadCount.getRedisFXDownloadCount();
        System.out.println("RedisFX下载量：" + downloadCount1);
    }
}
