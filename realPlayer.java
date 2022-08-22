import java.util.*;

//真实玩家
public class realPlayer extends Player{

    public realPlayer(Table table, int first){
        super(table,  first);
        super.setName("你");
        super.setCallScore(-1);
        this.setPuke(new TreeSet<String>());
    }

    public  void playCard(){
        Table t=this.getTable();
        Scanner s=new Scanner(System.in);
        String card= "";
        int[] my=this.getPk();
        int[] c=new int[18];
        Card cd = new Card();
        System.out.println(this.getName()+"的牌为："+this.getPuke());
        if(t.getC().getType()!=0&& !t.getP().equals(this)){
            System.out.println("轮到"+this.getName()+"出牌,请输入你要出的牌 或者 “不要”");
            card=s.nextLine();
        }
        else {
            System.out.println("轮到"+this.getName()+"出牌,请输入你要出的牌");
            card=s.nextLine();
            while(card.equals("不要")){
                System.out.println("现在"+this.getName()+"必须出牌");
                card=s.nextLine();
            }
        }
        while(true) {
            judgeIllegal(card, c, my);
            if(card.equals("不要")){
                break;
            }
            int ans=judgeClass(c,cd);
//            System.out.println("\n "+ans+" \n");
            if(ans==1) {
                if (t.getC().getType() == 0 || t.getP().equals(this)) {
                    pushCard(c);
                    t.setP(this);
                    t.setC(cd);
                    break;
                } else {
                    if (t.getC().getType() == cd.getType() && cd.getValue() > t.getC().getValue()) {
                        pushCard(c);
                        t.setP(this);
                        t.setC(cd);
                        break;
                    } else if (cd.getType() == 12) {
                        pushCard(c);
                        t.setP(this);
                        t.setC(cd);
                        break;
                    } else if (cd.getType() == 11 && t.getC().getType() != 12) {
                        pushCard(c);
                        t.setP(this);
                        t.setC(cd);
                        break;
                    }
                }
            }
            System.out.println("你出的牌不符合游戏规则请重新输入。");
            card=s.nextLine();
        }

    }


    public void judgeIllegal(String card,int[] c,int[] my){
        Scanner s=new Scanner(System.in);
        while(true) {
            int i;
            while (true) {
                for ( i = 0; i <= 17; i++) {
                    c[i] = 0;
                }
                if (!card.equals("不要")) {
                    for (i = 0; i < card.length(); i++) {
                        int tp;
                        if (card.charAt(i) == '大' || card.charAt(i) == '小') {
                            i++;
                            if (i == card.length()) {
                                i = i - 1;
                                break;
                            }
                            if (card.charAt(i) != '王') {
                                break;
                            } else {
                                c[toValue.TV2(card.charAt(i - 1))]++;
                            }
                        } else if (card.charAt(i) == '1') {
                            i++;
                            if (i == card.length()) {
                                i = i - 1;
                                break;
                            }
                            if (card.charAt(i) == '0') {
                                c[toValue.TV2(card.charAt(i - 1))]++;
                            } else {
                                break;
                            }
                        } else {
                            int tt = toValue.TV2(card.charAt(i));
                            if (tt == -1) {
                                break;
                            } else {
                                c[tt]++;
                            }
                        }
                    }
                    if (i == card.length()) {
                        break;
                    }
                } else {
                    break;
                }
                System.out.println("你输入有误请重新输入");
                card = s.nextLine();
            }
            for( i=0;i<18;i++){
                if(c[i]>my[i]){
                    break;
                }
            }
            if(i==18){
                break;
            }
            System.out.println("你出了你手上没有的牌，请重新出牌");
            card = s.nextLine();
        }
    }//仅判断输入是否正确&出牌数量是否正确

    public void callScore(computerPlayer p1, computerPlayer p2) {
        int max_s = Math.max(p1.getCallScore(), p2.getCallScore());
        if (max_s != 3) {
            System.out.println("轮到你叫地主（0==不叫）请输入：");
            System.out.println("0 1 2 3");
            Scanner s = new Scanner(System.in);
            int score = s.nextInt();
            if (max_s != -1 && max_s != 0) {
                while (score <= max_s && score != 0) {
                    System.out.println("你叫分必须大于前面玩家或者不叫，请重新输入");
                    score = s.nextInt();
                }
            }
            this.setCallScore(score);
        }else{
            this.setCallScore(0);
        }
    }//叫地主

    public void pushCard(int[] c){
        int[] pk=this.getPk();
        TreeSet<String> puke=this.getPuke();
        TreeSet<String> sub_puke=new TreeSet<>();
        for(int i=3;i<18;i++){
            pk[i]=pk[i]-c[i];
        }
        for(String i:puke){
            if(c[toValue.TV(i)]>0){
                c[toValue.TV(i)]--;
                sub_puke.add(i);
            }
        }
        for(String i:sub_puke){
            puke.remove(i);
        }
        System.out.println(this.getName()+"现在的手牌为"+this.getPuke());
    }//更新玩家手牌
}
