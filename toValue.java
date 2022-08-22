public class toValue {
    /*
     *   此函数用于计算poker的面值
     */
    public static int TV(String s){//3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
        if(s.equals("大王"))
            return 17;
        else if(s.equals("小王"))
            return 16;
        else if(s.charAt(1)=='2'){
            return 15;
        }
        else if(s.charAt(1)=='A'){
            return 14;
        }
        else if(s.charAt(1)=='K'){
            return 13;
        }
        else if(s.charAt(1)=='Q'){
            return 12;
        }
        else if(s.charAt(1)=='J'){
            return 11;
        }
        else if(s.charAt(1)=='1'){
                return 10;
        }else{
            return s.charAt(1)-'0';
        }
    }
    public static int TV2(char s){//3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
        if(s=='大')
            return 17;
        else if(s=='小')
            return 16;
        else if(s=='2'){
            return 15;
        }
        else if(s=='A'){
            return 14;
        }
        else if(s=='K'){
            return 13;
        }
        else if(s=='Q'){
            return 12;
        }
        else if(s=='J'){
            return 11;
        }
        else if(s=='1'){
            return 10;
        }else if(s=='3'||s=='4'||s=='5'||s=='6'||s=='7'||s=='8'||s=='9'){
            return s-'0';
        } else{
            return -1;
        }
    }
}
