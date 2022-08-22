import java.util.Comparator;

public class cardComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        String s1=(String) o1;
        String s2=(String) o2;
        int v1=toValue.TV(s1);
        int v2=toValue.TV(s2);
        if(v1==v2){
            return s1.charAt(0)-s2.charAt(0);
        }
        return v1-v2;
    }
}
