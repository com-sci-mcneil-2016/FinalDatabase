import mayflower.*;
public class Runner extends Mayflower
{
    public Runner() 
    {
    	super("Mayflower Tutorial", 736, 556);
    }
    @Override
    public void init()
    {
    	Mayflower.setFullScreen(false);
    	World startingWorld = new Level1World();
    	Mayflower.setWorld(startingWorld);
    }
    
    public static void main(String[] args)
    {
    	new Runner();
    }
}