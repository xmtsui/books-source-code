package javathreads.examples.ch11.example3;

import java.net.*;

public class URLPingTask implements Runnable {

    public interface URLUpdate {
        public void isAlive(boolean b);
    }

    URL url;
    URLUpdate uu;

    public URLPingTask(URL url) {
        this(url, null);
    }

    public URLPingTask(URL url, URLUpdate uu) {
        this.url = url;
        this.uu = uu;
    }


    public void run() {
        try {
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setConnectTimeout(1000);
            huc.setReadTimeout(1000);
            int code = huc.getResponseCode();
            if (uu != null)
                uu.isAlive(true);
        } catch (Exception e) {
            if (uu != null)
               uu.isAlive(false);
        }
    }
}
