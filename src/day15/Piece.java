package day15;

public abstract class Piece implements Comparable<Piece>{

	public final Type type;
	public int x, y, hp, attack;
	
	public Piece(int x, int y, int hp, int attack) {
		if(getClass().getSimpleName().equals("Goblin")) {
			type = Type.GOBLIN;
		}else {
			type = Type.ELF;
		}
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.attack = attack;
	}

	@Override
	public int compareTo(Piece o) {
		if(this.y != o.y) {
			return this.y - o.y;
		}
		return this.x - o.y;
	}
	
	public String toString() {
		return type + " @ (" + x + ", " + y + ") HP: " + hp;
	}
}
