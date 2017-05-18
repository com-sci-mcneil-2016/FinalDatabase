import java.util.*;
/**
 * Logan Keim, Kyle Dodds, Connor Simpson
 * 5/12/2017
 * Period 5
 */
public class MAIN
{
<<<<<<< HEAD
    private ArrayList<Result> things;
=======
    // instance variables - replace the example below with your own
    private ArrayList<Result> things;

    /**
     * Constructor for objects of class MAIN
     */
>>>>>>> refs/remotes/origin/master
    public MAIN()
    {
        things = new ArrayList();
    }
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/origin/master
    public void add(int id, String name, int value)
    {
        Result thing = new Result(id,name,value);
        things.add(thing);
    }
<<<<<<< HEAD
	public void remove(int index)
	{
		things.remove(index);
	}
=======
    public void remove(int index)
    {
        things.remove(index);
        
    }
    
    public Result searchByName(String key)
    {
        /*for (int i = 0; i < things.size(); i++)
        {
            if (key.equals(things.get(i).getName()))
            {
                return i;
            }
        } */
        for(Result i: things)
        {
            if(key.equals(i.getName()))
                return i;
                
        }
        return null;
       }
>>>>>>> refs/remotes/origin/master
}
