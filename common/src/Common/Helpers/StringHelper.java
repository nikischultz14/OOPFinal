package Common.Helpers;

public class StringHelper {

    public static String getFileExtension(String fileExt) {

        try {
            return fileExt.substring(fileExt.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

}
