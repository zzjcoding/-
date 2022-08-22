public class Card {
    private int value,cnt,type,a3,a2;

    /*
     *            牌的类型
     *
     *             x1.单张
     *             x2.对子
     *           x  3.三张相同的牌
     *             x4.三带一
     *             x5.单顺
     *            x 6.双顺
     *            x 7.三顺
     *            x 8.四带二
     *             9.飞机带2单
     *             10.飞机带2顺
     *             11.炸弹
     *             12.王炸
     *             13.三带一对
     *             14.四带一对
     */

    public Card() {
    }

    public Card(int value, int cnt, int type) {
        this.value = value;
        this.cnt = cnt;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getA3() {
        return a3;
    }

    public void setA3(int a3) {
        this.a3 = a3;
    }

    public int getA2() {
        return a2;
    }

    public void setA2(int a2) {
        this.a2 = a2;
    }
}
