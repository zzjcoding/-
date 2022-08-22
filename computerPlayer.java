import java.util.TreeSet;
import java.util.Random;

//电脑玩家
public class computerPlayer extends Player {
    public computerPlayer(Table table, int first, String name){
        super(table,  first);
        super.setName(name);
        super.setCallScore(-1);
        this.setPuke(new TreeSet<>());
    }
    public  void playCard(){
        // 电脑的出牌逻辑 两种情况
        //1.前面无玩家出牌 或者自己出的牌没人要{
        //      出牌顺序
        //      1.炸弹
        //      2.王炸
        //      3.单张
        // }
        int[] my=this.getPk();
        Card preCard=getTable().getC();
        int[] c=new int[18];
        Card nowCard=new Card();
        int found=0;
        Table table=this.getTable();
        if(preCard.getType()==0||table.getP().equals(this)){
            //自己出牌
            //出炸弹
            for(int i=3;i<18;i++){
                if(my[i]==4){
                    c[i]=4;
                    found=1;
                    break;
                }
            }
            if(found==0){//出王炸
                if(my[16]==1&&my[17]==1){
                    c[16]=1;
                    c[17]=1;
                    found=1;
                }
            }
            if(found==0){//出单张
                for(int i=3;i<18;i++){
                    if(my[i]>=1){
                        c[i]=1;
                        found=1;
                        break;
                    }
                }
            }
        }
        else if(table.getP().getStatus()!=this.getStatus()){
            //对方出牌
            switch (preCard.getType()){
                case 1://单张
                    for(int i=preCard.getValue()+1;i<18;i++){
                        if(my[i]>=1){
                            c[i]=1;
                            found=1;
                            break;
                        }
                    }
                    break;
                case 2://对子
                    for(int i=preCard.getValue()+1;i<18;i++){
                        if(my[i]>=2){
                            c[i]=2;
                            found=1;
                            break;
                        }
                    }
                    break;
                case 3://三张
                    for(int i=preCard.getValue()+1;i<18;i++){
                        if(my[i]>=3){
                            c[i]=3;
                            found=1;
                            break;
                        }
                    }
                    break;
                case 4://三代一
                    for(int i=preCard.getValue()+1;i<18;i++){
                        if(my[i]>=3){
                            c[i]=3;
                            found=1;
                            break;
                        }
                    }
                    if(found==1) {
                        found = 0;
                        for (int i = 3; i < 18; i++) {
                            if(c[i]!=3&&my[i]>=1){
                                c[i]=1;
                                found=1;
                                break;
                            }
                        }
                    }
                    break;
                case 5://单顺子
                    for(int i=14;i>=preCard.getValue()+1;i--){
                       if(i-preCard.getCnt()+1>=3){
                           int j;
                           for( j=i;j>=i-preCard.getCnt()+1;j--){
                               if(my[j]<1){
                                   break;
                               }
                           }
                           if(j==i-preCard.getCnt()){
                               for( j=i;j>=i-preCard.getCnt()+1;j--){
                                   c[j]=1;
                               }
                               found=1;
                               break;
                           }else{
                               i=j;
                           }
                       }
                    }
                    break;
                case 6://双顺
                    for(int i=14;i>=preCard.getValue()+1;i--){  //334455 3 14-3+1=12
                        if(i-preCard.getA3()+1>=3){
                            int j;
                            for( j=i;j>=i-preCard.getA3()+1;j--){
                                if(my[j]<2){
                                    break;
                                }
                            }
                            if(j==i-preCard.getA3()){
                                for( j=i;j>=i-preCard.getA3()+1;j--){
                                    c[j]=2;
                                }
                                found=1;
                                break;
                            }else{
                                i=j;
                            }
                        }
                    }
                    break;
                case 7://三顺
                    for(int i=14;i>=preCard.getValue()+1;i--){
                        if(i-preCard.getA3()+1>=3){
                            int j;
                            for( j=i;j>=i-preCard.getA3()+1;j--){
                                if(my[j]<3){
                                    break;
                                }
                            }
                            if(j==i-preCard.getA3()){
                                for( j=i;j>=i-preCard.getA3()+1;j--){
                                    c[j]=3;
                                }
                                found=1;
                                break;
                            }else{
                                i=j;
                            }
                        }
                    }
                    break;
                case 8://四带二
                    for(int i=15;i> preCard.getValue();i--){
                        if(my[i]==4){
                            c[i]=4;
                            found=1;
                            break;
                        }
                    }
                    if(found==1){
                        found=0;
                        for(int i=3;i<18;i++){
                            if(my[i]>=1&&my[i]!=4){
                                c[i]=1;
                                found++;
                                if(found==2){
                                    break;
                                }
                            }
                        }
                        if(found==2){
                            found=1;
                        }else{
                            found=0;
                        }
                    }
                    break;
                case 9://飞机带2单
                    for(int i=14;i>=preCard.getValue()+1;i--){
                        if(i-preCard.getA3()+1>=3){
                            int j;
                            for( j=i;j>=i-preCard.getA3()+1;j--){
                                if(my[j]<3){
                                    break;
                                }
                            }
                            if(j==i-preCard.getA3()){
                                for( j=i;j>=i-preCard.getA3()+1;j--){
                                    c[j]=3;
                                }
                                found=1;
                                break;
                            }else{
                                i=j;
                            }
                        }
                    }
                    if(found==1){
                        found=0;
                        for(int i=3;i<18;i++){
                            if(my[i]>=1&&c[i]!=3){
                                c[i]=1;
                                found++;
                                if(found==2){
                                    break;
                                }
                            }
                        }
                        if(found==2){
                            found=1;
                        }else{
                            found=0;
                        }

                    }
                    break;
                case 10://飞机带2顺
                    for(int i=14;i>=preCard.getValue()+1;i--){
                        if(i-preCard.getA3()+1>=3){
                            int j;
                            for( j=i;j>=i-preCard.getA3()+1;j--){
                                if(my[j]<3){
                                    break;
                                }
                            }
                            if(j==i-preCard.getA3()){
                                for( j=i;j>=i-preCard.getA3()+1;j--){
                                    c[j]=3;
                                }
                                found=1;
                                break;
                            }else{
                                i=j;
                            }
                        }
                    }
                    if(found==1){
                        found=0;
                        for(int i=3;i<18;i++){
                            if(my[i]>=2&&c[i]!=3){
                                c[i]=2;
                                found++;
                                if(found== preCard.getA2()){
                                    break;
                                }
                            }
                        }
                        if(found==preCard.getA2()){
                            found=1;
                        }else{
                            found=0;
                        }

                    }
                    break;
                case 11://炸弹
                    for(int i=preCard.getValue()+1;i<16;i++){
                        if(my[i]==4){
                            c[i]=4;
                            found=1;
                            break;
                        }
                    }
                    break;
                case 12://王炸最大不出牌
                    break;
                case 13:
                    for(int i=preCard.getValue()+1;i<18;i++){
                        if(my[i]>=3){
                            c[i]=3;
                            found=1;
                            break;
                        }
                    }
                    if(found==1) {
                        found = 0;
                        for (int i = 3; i < 18; i++) {
                            if(c[i]!=3&&my[i]>=2){
                                c[i]=2;
                                found=1;
                                break;
                            }
                        }
                    }
                    break;
                case 14://四带二
                    for(int i=preCard.getValue()+1;i<18;i++){
                        if(my[i]==4){
                            c[i]=4;
                            found=1;
                            break;
                        }
                    }
                    if(found==1) {
                        found = 0;
                        for (int i = 3; i < 18; i++) {
                            if(c[i]!=4&&my[i]>=2){
                                c[i]=2;
                                found=1;
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        if(table.getP().getStatus()!=this.getStatus()) {
            if (found == 0 && preCard.getType() != 12 && preCard.getType() != 11) {
                //出炸弹
                for (int i = 3; i < 18; i++) {
                    if (my[i] == 4) {
                        c[i] = 4;
                        found = 1;
                        break;
                    }
                }
                if (found == 0) {//出王炸
                    if (my[16] == 1 && my[17] == 1) {
                        c[16] = 1;
                        c[17] = 1;
                        found = 1;
                    }
                }
            }
        }
        if(found==1){
            table.setP(this);
            judgeClass(c,nowCard);
            pushCard(c);
            table.setC(nowCard);
        }else{
            System.out.println(this.getName()+" 不要");
        }
    }

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
        System.out.println(this.getName()+"出的牌为："+sub_puke);
    }//更新玩家手牌

    public void callScore(realPlayer p1, computerPlayer p2){
        Random r=new Random();
        r.setSeed(System.currentTimeMillis());
        int max_s,now_s;
        max_s=Math.max(p1.getCallScore(),p2.getCallScore());
        if(max_s!=3) {
            int tp = r.nextInt(2);
            if (tp == 0) {
                this.setCallScore(0);
                System.out.println(this.getName() + "不叫");
            } else {
                if (max_s == -1) {
                    this.setCallScore(r.nextInt(3) + 1);
                    System.out.println(this.getName() + " 叫地主 " + this.getCallScore() + "分");
                } else {
                    this.setCallScore(r.nextInt(3 - max_s) + 1 + max_s);
                    System.out.println(this.getName() + " 叫地主 " + this.getCallScore() + "分");
                }
            }
        }else {
            this.setCallScore(0);
        }
    }
}
