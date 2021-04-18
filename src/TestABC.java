import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestABC {
    public final static Pattern ATUSER_PATTERN = Pattern.compile(
        "<card\\s+url=\"https?://[.[^\"|^<|^>]]*?\"\\s+title=\"[_\\.a-zA-Z0-9\u4e00-\u9fa5\\s]*\"\\s+source=\"[_\\.a-zA-Z0-9\u4e00-\u9fa5]*\"\\s+publish=\"[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-2][0-9]:[0-6][0-9]\"\\s*?/>");

    private final static String URL_REGEX      = "url=\"https?://[.[^\"|^<|^>]]*?\"";
    private final static String  SOURCE_REGEX   = "source=\"[_\\.a-zA-Z0-9\u4e00-\u9fa5]*\"";
    private final static String  TITLE_REGEX    = "title=\"[_\\.a-zA-Z0-9\u4e00-\u9fa5\\s]*\"";
    private final static String PUBLISH_REGEX  = "publish=\"[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-2][0-9]:[0-6][0-9]\"";
    private final static Pattern CARD_PATTERN   = Pattern
        .compile("<card\\s+" + URL_REGEX + "\\s+" + TITLE_REGEX + "\\s+" + SOURCE_REGEX + "\\s+"
                 + PUBLISH_REGEX + "\\s*?/>");
    //    private final static Pattern CARD_PATTERN   = Pattern.compile(
    //        "<card\\s+url=\"https?://[.[^\"|^<|^>]]*?\"\\s+title=\"[_\\.a-zA-Z0-9\u4e00-\u9fa5\\s]*\"\\s+source=\"[_\\.a-zA-Z0-9\u4e00-\u9fa5]*\"\\s+publish=\"[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-2][0-9]:[0-6][0-9]\"\\s*?/>");

	public static void main(String[] args) {
        String content = "<card url=\"http://www.taobao.com\" title=\"taobao\" source=\"taobao.com\" publish=\"2016-03-30 19:00\"/>";
        Matcher match = CARD_PATTERN.matcher(content);
        int positionCount = 0;
        while (match.find()) {
            String matchStr = match.group();

            //            test(matchStr);
            String imgLink = matchStr.substring(matchStr.indexOf('\"') + 1,
                matchStr.lastIndexOf('\"'));

            String userName = matchStr.substring(1);

            System.out.println(imgLink);
            System.out.println(matchStr);

            String URL_REGEX = "url=\"https?://[.[^\"|^<|^>]]*?\"";
            String result = splitWithPattern(matchStr, URL_REGEX);
            System.out.println(result);
            positionCount++;

        }

	}
	
    private static String splitWithPattern(String sourceStr, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceStr);
        String matchValue = "";
        while (matcher.find()) {
            String matchStr = matcher.group();
            matchValue = matchStr.substring(matchStr.indexOf('\"') + 1, matchStr.lastIndexOf('\"'));
            //            break;
        }
        return matchValue;
    }

    public static void test(String s) {
        Obj obj = new Obj();
        Pattern urlPattern = Pattern.compile("url=\"https?://[.[^\"|^<|^>]]*?\"");
        Pattern titlePattern = Pattern
            .compile("title=\"[_\\.a-zA-Z0-9\u4e00-\u9fa5\\s]*\"");
        Pattern sourcePattern = Pattern.compile("source=\"[_\\.a-zA-Z0-9\u4e00-\u9fa5]*\"");
        Pattern publishPattern = Pattern
            .compile("publish=\"[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-2][0-9]:[0-6][0-9]\"");

        Matcher matcher = urlPattern.matcher(s);
        while (matcher.find()) {
            String matchStr = matcher.group();
            String matchValue = matchStr.substring(matchStr.indexOf('\"') + 1,
                matchStr.lastIndexOf('\"'));
            obj.setUrl(matchValue);
        }

        matcher = titlePattern.matcher(s);
        while (matcher.find()) {
            String matchStr = matcher.group();
            String matchValue = matchStr.substring(matchStr.indexOf('\"') + 1,
                matchStr.lastIndexOf('\"'));
            obj.setTitle(matchValue);
        }

        matcher = sourcePattern.matcher(s);
        while (matcher.find()) {
            String matchStr = matcher.group();
            String matchValue = matchStr.substring(matchStr.indexOf('\"') + 1,
                matchStr.lastIndexOf('\"'));
            obj.setSource(matchValue);
        }

        matcher = publishPattern.matcher(s);
        while (matcher.find()) {
            String matchStr = matcher.group();
            String matchValue = matchStr.substring(matchStr.indexOf('\"') + 1,
                matchStr.lastIndexOf('\"'));
            obj.setPublish(matchValue);
        }
        System.out.println(obj.getUrl());
        System.out.println(obj.getTitle());
        System.out.println(obj.getSource());
        System.out.println(obj.getPublish());
    }

}

class Obj {
    String url     = null;
    String title   = null;
    String source  = null;
    String publish = null;

    public Obj() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

}
