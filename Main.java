import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("******斗地主*******");
        System.out.println("****1.开始游戏*****");
        System.out.println("****2.退出游戏*****");
        System.out.println("*****请输入序号*****");

        /*
         *             初始化牌堆
         */

        Scanner ss=new Scanner(System.in);
        String begin=ss.nextLine();
        while(!begin.equals("1")&&!begin.equals("2")){
            System.out.println("输入有误，请重新输入：");
            begin=ss.nextLine();
        }
        if(begin.equals("1")) {



            /*
             *                初始化 玩家 电脑 桌面  牌堆
             */

            //        牌堆
            ArrayList<String> card = new ArrayList<String>();
            initCard(card);

            //        桌面
            Card c=new Card(0,0,0);
            Table table = new Table(c);

            //        玩家 电脑
            realPlayer p1 = new realPlayer(table, 0);
            computerPlayer pc1 = new computerPlayer(table, 0, "电脑1");
            computerPlayer pc2 = new computerPlayer(table, 0, "电脑2");

            //        手牌
            cardComparator c1 = new cardComparator();
            TreeSet<String> player = new TreeSet<String>(c1);
            TreeSet<String> computer1 = new TreeSet<String>(c1);
            TreeSet<String> computer2 = new TreeSet<String>(c1);
            TreeSet<String> dp = new TreeSet<>();

            while(true) {

                /*
                 *                    游戏开始
                 *                1.洗牌 发牌 叫地主
                 */

                System.out.println("*****游戏开始*****");
                gameBegin(p1, pc1, pc2, player, computer1, computer2, dp, card, table);

                /*
                 *                2.    出牌
                 */

                play(p1, pc1, pc2, table);

                /*
                 *                3.    一轮游戏结束
                 */

                System.out.println("*****是否继续游戏（请输入“是”或者”否“）*****");
                begin=ss.nextLine();
                while(!begin.equals("是")&&!begin.equals("否")){
                    System.out.println("输入有误，请重新输入：");
                    begin=ss.nextLine();
                }
                if(begin.equals("否")){
                    System.out.println("游戏已退出");
                    break;
                }
            }
        }else{
            System.out.println("游戏已退出");
        }

    }

    public static void initCard(ArrayList<String> card){
        String[] color = {"♦", "♣", "♠", "♥"};
        String[] value = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        for (String i : color) {
            for (String j : value) {
                card.add(i + j);
            }
        }
        card.add("小王");
        card.add("大王");
    }

    public static void play(realPlayer p1, computerPlayer pc1, computerPlayer pc2, Table table){

        if(p1.getStatus()==1){
            while(!p1.getPuke().isEmpty()
                    &&!pc1.getPuke().isEmpty()
                    &&!pc2.getPuke().isEmpty())
            {
                p1.playCard();
                if(p1.getPuke().isEmpty()){break;}
                pc1.playCard();
                if(pc1.getPuke().isEmpty()){break;}
                pc2.playCard();
                if(pc2.getPuke().isEmpty()){break;}
            }
        }else if(pc1.getStatus()==1){
            while(!p1.getPuke().isEmpty()
                    &&!pc1.getPuke().isEmpty()
                    &&!pc2.getPuke().isEmpty())
            {
                pc1.playCard();
                if(pc1.getPuke().isEmpty()){break;}
                pc2.playCard();
                if(pc2.getPuke().isEmpty()){break;}
                p1.playCard();
                if(p1.getPuke().isEmpty()){break;}
            }
        }else if(pc2.getStatus()==1){
            while(!p1.getPuke().isEmpty()
                    &&!pc1.getPuke().isEmpty()
                    &&!pc2.getPuke().isEmpty())
            {
                pc2.playCard();
                if(pc2.getPuke().isEmpty()){break;}
                p1.playCard();
                if(p1.getPuke().isEmpty()){break;}
                pc1.playCard();
                if(pc1.getPuke().isEmpty()){break;}
            }
        }else{
            Random r=new Random();
            r.setSeed(System.currentTimeMillis());
            int first=r.nextInt(3);
            switch (first){
                case 0:
                    while(!p1.getPuke().isEmpty()
                            &&!pc1.getPuke().isEmpty()
                            &&!pc2.getPuke().isEmpty())
                    {
                        p1.playCard();
                        if(p1.getPuke().isEmpty()){break;}
                        pc1.playCard();
                        if(pc1.getPuke().isEmpty()){break;}
                        pc2.playCard();
                        if(pc2.getPuke().isEmpty()){break;}
                    }
                    break;
                case 1:
                    while(!p1.getPuke().isEmpty()
                            &&!pc1.getPuke().isEmpty()
                            &&!pc2.getPuke().isEmpty())
                    {
                        pc1.playCard();
                        if(pc1.getPuke().isEmpty()){break;}
                        pc2.playCard();
                        if(pc2.getPuke().isEmpty()){break;}
                        p1.playCard();
                        if(p1.getPuke().isEmpty()){break;}
                    }
                    break;
                case 2:
                    while(!p1.getPuke().isEmpty()
                            &&!pc1.getPuke().isEmpty()
                            &&!pc2.getPuke().isEmpty())
                    {
                        pc2.playCard();
                        if(pc2.getPuke().isEmpty()){break;}
                        p1.playCard();
                        if(p1.getPuke().isEmpty()){break;}
                        pc1.playCard();
                        if(pc1.getPuke().isEmpty()){break;}
                    }
                    break;
                default:
                    break;
            }

        }
        if(p1.getPuke().isEmpty()){
            System.out.println(p1.getName()+" 率先出完所有手牌");
           if(p1.getStatus()==1){
               System.out.println("地主 获得本轮游戏的胜利");
           }else{
               System.out.println("农民 获得本轮游戏的胜利");
           }
           p1.setFirst(1);
        }else if(pc1.getPuke().isEmpty()){
            System.out.println(pc1.getName()+" 率先出完所有手牌");
            if(pc1.getStatus()==1){
                System.out.println("地主 获得本轮游戏的胜利");
            }else{
                System.out.println("农民 获得本轮游戏的胜利");
            }
            pc1.setFirst(1);
        }else{
            System.out.println(pc2.getName()+" 率先出完所有手牌");
            if(pc2.getStatus()==1){
                System.out.println("地主 获得本轮游戏的胜利");
            }else{
                System.out.println("农民 获得本轮游戏的胜利");
            }
            pc2.setFirst(1);
        }
    }

    public static void Update(Player p, TreeSet<String> puke){
        p.setStatus(-1);
        p.setCallScore(-1);
        p.setPuke(puke);
        int[] tp=p.getPk();
        for(int i=0;i<=17;i++){
            tp[i]=0;
        }
        for(String i:puke){
           int tt=toValue.TV(i);
           tp[tt]++;
        }
    }

    public static void addDP(Player p,TreeSet<String> dp){
        for(String i:dp){
            p.getPuke().add(i);
            p.getPk()[toValue.TV(i)]++;
        }
    }

    public static void gameBegin(realPlayer p1, computerPlayer pc1, computerPlayer pc2, TreeSet<String> player,
                                 TreeSet<String> computer1, TreeSet<String> computer2,
                                 TreeSet<String> dp, ArrayList<String> card, Table table) {
        while (true) {

            /*
             *          洗牌 +发牌 + 叫地主
             *         （若均不叫地主 重新洗牌）
             */

            //洗牌
            Collections.shuffle(card);

            //发牌
            player.clear();
            computer1.clear();
            computer2.clear();
            for (int cnt = 0; cnt < 51; cnt++) {
                if (cnt % 3 == 0) {
                    player.add(card.get(cnt));
                } else if (cnt % 3 == 1) {
                    computer1.add(card.get(cnt));
                } else {
                    computer2.add(card.get(cnt));
                }
            }
            dp.clear();
            dp.add(card.get(51));
            dp.add(card.get(52));
            dp.add(card.get(53));

            /*
             *               初始化玩家和桌面
             */

            Card cd=new Card(0,0,0);
            table.setC(cd);
            p1.setName("你");
            pc1.setName("电脑1");
            pc2.setName("电脑2");
            Update(p1,player);
            Update(pc1,computer1);
            Update(pc2,computer2);
            System.out.println("你的手牌为");
            System.out.println(p1.getPuke());
            System.out.println("电脑1 17张，电脑2 17张，底牌 3张 ");
            System.out.println();
            if(p1.getFirst()==1){
                p1.callScore(pc1, pc2);
                pc1.callScore(p1, pc2);
                pc2.callScore(p1, pc1);
            }else if(pc1.getFirst()==1){
                pc1.callScore(p1, pc2);
                pc2.callScore(p1, pc1);
                p1.callScore(pc1, pc2);
            }else if(pc2.getFirst()==1){
                pc2.callScore(p1, pc1);
                p1.callScore(pc1, pc2);
                pc1.callScore(p1, pc2);
            }
            else{
                Random r = new Random();
                r.setSeed(System.currentTimeMillis());
                int order = r.nextInt(3);
                if (order == 0) {//系统随机决定顺序
                    p1.callScore(pc1, pc2);
                    pc1.callScore(p1, pc2);
                    pc2.callScore(p1, pc1);
                } else if (order == 1) {
                    pc1.callScore(p1, pc2);
                    pc2.callScore(p1, pc1);
                    p1.callScore(pc1, pc2);
                } else {
                    pc2.callScore(p1, pc1);
                    p1.callScore(pc1, pc2);
                    pc1.callScore(p1, pc2);
                }
            }
            p1.setFirst(0);
            pc1.setFirst(0);
            pc2.setFirst(0);
            int max_score=0;
            int a=p1.getCallScore(),b= pc1.getCallScore(),c=pc2.getCallScore();
            if(a>b&&a>c){
                max_score=1;
            }else if(b>a&&b>c){
                max_score=2;
            }else if(c>b&&c>a){
                max_score=3;
            }
            if(max_score==1){
                System.out.println("本轮 你 被选为地主");
                p1.setName("你（地主）");
                p1.setStatus(1);
                pc1.setStatus(0);
                pc2.setStatus(0);
                addDP(p1,dp);
                break;
            }else if(max_score==2){
                System.out.println("本轮 电脑1 被选为地主");
                pc1.setName("电脑1（地主）");
                addDP(pc1,dp);
                p1.setStatus(0);
                pc1.setStatus(1);
                pc2.setStatus(0);
                break;
            }else if(max_score==3){
                System.out.println("本轮 电脑2 被选为地主");
                pc1.setName("电脑2（地主）");
                addDP(pc2,dp);
                p1.setStatus(0);
                pc1.setStatus(0);
                pc2.setStatus(1);
                break;
            }
            System.out.println("无人叫地主 重新发牌...");
            System.out.println("-----------------");
        }
        System.out.println();
        System.out.println("底牌为\n"+dp);
        System.out.println();
        System.out.println(p1.getName()+"的手牌为");
        System.out.println(p1.getPuke());
        System.out.println();
        System.out.println(pc1.getName()+" "+pc1.getPuke().toArray().length+
                "张，"+pc2.getName()+" "+pc2.getPuke().toArray().length+"张");
    }
}
