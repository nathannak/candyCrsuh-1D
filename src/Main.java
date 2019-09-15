import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<String> res = new ArrayList<>();

    public static void main(String[] args) {

        String s = "baaabbbabbccccd";
        //String s = "aaabbbc";
        //String s = "aabbbacd";
        //String s = "aaabbbacd";

        System.out.println(candyCrush1D(s));

    }

    public static String candyCrush1D(String s){

        for (int i=0 ; i < s.length()-2; i++ ) {

            int[] arr= threeOrMore(s,i);

            if( arr!=null ) {
                process(s,arr[0],arr[1]);
            }

        }

        if(res.size()==0)  return null;

        String ret=res.get(0);

        for(String str: res ){
            if(ret.length() > str.length()) ret=str;
        }

        return ret;

    }

    public static int[] threeOrMore(String s , int i ){

        int begin=i;
        int ret=-1;
        int counter=0;

        //captures three consecutive chars while moving the beginning char
        while(i < s.length() - 2) {

            while ((i + counter < s.length() - 2) && (s.charAt(i + counter) == s.charAt(i + counter + 1)) && (s.charAt(i + 1) == s.charAt(i + counter + 2))) {

                ret = i + counter + 2;
                begin = i;
                counter++;

                //don't increment i here
                //i++;
            }

            i++;
        }

        if(ret==-1) return null;

        //get characters more than three distance away from current beginning [e.g. forth fifth ...]
        while( ret<s.length()-1 && (s.charAt(ret)==s.charAt(ret+1)) ) ret++;

        return new int[]{begin,ret};

    }

    public static void process(String s, int i, int j ){

        s = s.substring(0,i) + s.substring(j+1);
        if(s.length()==0) return;

        if(!res.contains(s))
            res.add(s);

        int[] arr = threeOrMore(s,0);

        if(arr!=null)
            process(s,arr[0],arr[1]);

        return;

    }

}
