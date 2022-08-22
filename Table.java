public class Table {
    private Player p;
    private Card c;

    public Table(Card c) {
        this.c = c;
    }

    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

    public Card getC() {
        return c;
    }

    public void setC(Card c) {
        this.c = c;
    }
}
