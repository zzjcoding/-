import java.util.*;

public class Player {
    private TreeSet<String> puke;
    private int status;
    private int callScore;
    private Table table;
    private int first;
    private String name;
    private int[] pk=new int[18];
    public Player(){};
    public Player( Table table, int first){
        this.table=table;
        this.first=first;
    }
    public  void playCard(){}

    public TreeSet<String> getPuke() {
        return puke;
    }

    public void setPuke(TreeSet<String> puke) {
        this.puke = puke;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCallScore() {
        return callScore;
    }

    public void setCallScore(int callScore) {
        this.callScore = callScore;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getPk() {
        return pk;
    }

    public int judgeClass(int[] c,Card cd){
        TreeMap<Integer,Integer> cc=new TreeMap<Integer,Integer>(new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        int max_cnt=0;
        int max_value=0;
        cd.setValue(0);
        cd.setType(0);
        cd.setCnt(0);
        cd.setA3(0);
        for(int i=3;i<=17;i++){
            if(c[i]>0){
                cd.setCnt(cd.getCnt()+c[i]);
            }
            if(c[i]>=max_cnt){
                max_cnt=c[i];
                max_value=i;
            }
        }
        /*
         *            牌的类型
         *
         *             1.单张
         *             2.对子
         *             3.三张相同的牌
         *             4.三带一
         *             5.单顺
         *             6.双顺
         *             7.三顺
         *             8.四带二
         *             9.飞机带2单
         *             10.飞机带2顺
         *             11.炸弹
         *             12.王炸
         *             13.三带一对
         *             14.四带一对
         */
        if(cd.getCnt()<=4){

            if(cd.getCnt()==1){//单张
                cd.setType(1);
                cd.setValue(max_value);
                return 1;
            }else if(cd.getCnt()==2){
                if(c[16]==1&&c[17]==1){//王炸
                    cd.setValue(17);
                    cd.setType(12);
                    return 1;
                }else if(max_cnt==2){//对子
                    cd.setType(2);
                    cd.setValue(max_value);
                    return 1;
                }else{
                    return 0;
                }
            }else if(cd.getCnt()==3){
                if(max_cnt==3){//三张
                    cd.setValue(max_value);
                    cd.setType(3);
                    return 1;
                }else{
                    return 0;
                }
            }else{
                if(max_cnt==4){//炸弹
                    cd.setType(11);
                    cd.setValue(max_value);
                    return 1;
                }else if(max_cnt==3){//三带一
                    cd.setValue(max_value);
                    cd.setType(4);
                    return 1;
                }else{
                    return 0;
                }
            }
        }//牌 四张以下
        else{
            //四张以上
            //max_v=1 =2 =3 =4
            if(max_cnt==3){
                List<Integer> a1=new ArrayList<>();
                List<Integer> a2=new ArrayList<>();
                List<Integer> a3=new ArrayList<>();
                for(int i=3;i<18;i++){
                    if(c[i]==3){
                        a3.add(i);
                    }else if(c[i]==2){
                        a2.add(i);
                    }else if(c[i]==1){
                        a1.add(i);
                    }
                }
//                System.out.println(""+a3.size()+" "+a2.size()+" "+a1.size());
                if(a3.size()==1){
                    if(a2.size()==1&&a1.size()==0){
                        cd.setType(13);
                        cd.setValue(max_value);
                        cd.setA3(max_value);
                        return 1;
                    }
                    return 0;
                }
            }
            if(max_cnt==1){//单顺
                if(max_value>=15){
                    System.out.println(1);
                    return 0;
                }
                for(int i=max_value;i>max_value-cd.getCnt();i--){
                    if(c[i]!=1){
//                        System.out.println(max_value);
//                        System.out.println(cd.getCnt());
//                        System.out.println(i);
//                        System.out.println(c[i]);
//                        System.out.println(2);
                        return 0;
                    }
                }
                cd.setType(5);
                cd.setValue(max_value);
                return 1;
            }else if(max_cnt==2){//双顺
                if(max_value>=15||cd.getCnt()%2!=0){
                    return 0;
                }
                for(int i=max_value;i>max_value-cd.getCnt()/2;i--){
                    if(c[i]!=2){
                        return 0;
                    }
                }
                List<Integer> a2=new ArrayList<>();
                for(int i=max_value;i>max_value-cd.getCnt()/2;i--){
                    if(c[i]==2){
                        a2.add(i);
                    }
                }
                cd.setA3(a2.size());
                cd.setValue(max_value);
                cd.setType(6);
                return 1;
            }else if(max_cnt==3){//飞机或者 飞机带翅膀
                List<Integer> a1=new ArrayList<>();
                List<Integer> a2=new ArrayList<>();
                List<Integer> a3=new ArrayList<>();
                for(int i=3;i<18;i++){
                    if(c[i]==3){
                        a3.add(i);
                    }else if(c[i]==2){
                        a2.add(i);
                    }else if(c[i]==1){
                        a1.add(i);
                    }
                }
                if(a1.size()!=0&&a2.size()!=0) {
                    return 0;
                }
                if(max_value>=15){
                    return 0;
                }
                for(int i=1;i<a3.size();i++){
                    if(a3.get(i)!=a3.get(i-1)+1){
                        return 0;
                    }
                }
                if(a1.size()!=0){
                    if(a1.size()!=2){
                        return 0;
                    }
                    cd.setType(9);
                    cd.setValue(max_value);
                    cd.setA3(a3.size());
                    return 1;
                }
                if(a2.size()!=0){
                    cd.setA2(a2.size());
                    cd.setType(10);
                    cd.setValue(max_value);
                    cd.setA3(a3.size());
                    return 1;
                }
                if(a3.size()>=2) {
                    cd.setType(7);
                    cd.setValue(max_value);
                    cd.setA3(a3.size());
                    return 1;
                }
                return 0;
            }else if(max_cnt==4){
                if (cd.getCnt() == 6 && cd.getCnt() == 8) {
                    List<Integer> a1 = new ArrayList<>();
                    List<Integer> a2 = new ArrayList<>();
                    for (int i = 3; i < 18; i++) {
                        if (c[i] == 2) {
                            a2.add(i);
                        } else if (c[i] == 1) {
                            a1.add(i);
                        }
                    }
                    if (a1.size() == 2 && a2.size() == 0) {
                        cd.setType(8);
                        cd.setValue(max_value);
                        return 1;
                    } else if (a1.size() == 0 && a2.size() == 2) {
                        cd.setValue(max_value);
                        cd.setType(14);
                        return 1;
                    }
                }
                return 0;
            }
            return 0;
        }

    }//判断牌的类型

    public void setPk(int[] pk) {
        this.pk = pk;
    }
}
