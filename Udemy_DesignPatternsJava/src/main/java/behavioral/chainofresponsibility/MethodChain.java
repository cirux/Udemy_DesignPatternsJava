package behavioral.chainofresponsibility;


class Creature{
	public String name;
	public int attack, defense;
	
	public Creature(String name, int attack, int defense) {
		super();
		this.name = name;
		this.attack = attack;
		this.defense = defense;
	}

	@Override
	public String toString() {
		return "Creature [name=" + name + ", attack=" + attack + ", defense=" + defense + "]";
	}
	
}

class CreatureModifier{
	protected Creature creature;
	protected CreatureModifier next;
	
	public CreatureModifier(Creature creature) {
		this.creature = creature;
	}
	
	public void add(CreatureModifier cm) {
		if (next != null) {
			next.add(cm);
		} else {
			next = cm;
		}
	}
	
	public void handle() {
		// traverse the entire chain
		if(next != null) next.handle();
	}
}

class DoubleAttackModifier extends CreatureModifier{

	public DoubleAttackModifier(Creature creature) {
		super(creature);
	}

	@Override
	public void handle() {
		
		System.out.println("Doubling " + creature.name + "'s attack");
		creature.attack *= 2;
		
		super.handle();
	}
	
}

class IncreaseDefenseModifier extends CreatureModifier{ 

	public IncreaseDefenseModifier(Creature creature) {
		super(creature);
	}

	@Override
	public void handle() {
		
		System.out.println("Increasing " + creature.name + "'s defense");
		creature.defense += 3;
		
		super.handle();
	}
	
}

public class MethodChain {
	public static void main(String [] args) {
		
		Creature goblin = new Creature("Goblin", 2 ,2);
		System.out.println(goblin);
		
		// root element
		CreatureModifier root = new CreatureModifier(goblin);
		
		System.out.println("Let's double goblin's attack...");
		
		root.add(new DoubleAttackModifier(goblin));
		
		System.out.println("Lets' increase goblin's defense...");
		
		root.add(new IncreaseDefenseModifier(goblin));
		
		root.handle(); // traverses the entire chain of responsibility
		
		System.out.println(goblin);
	}
	
	
}
