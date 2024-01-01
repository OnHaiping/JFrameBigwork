package Tools;
// 非空判断
public class toolUtil {
    public static boolean isEmpty(String str){
        if (str != null && !str.trim().isEmpty()){
            return false;
        }
        return true;
    }
}