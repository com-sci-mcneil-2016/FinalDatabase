import java.util.*;
/**
 * Logan Keim, Kyle Dodds, Connor Simpson
 * 5/12/2017
 * Period 5
 */
public class MAIN
{
    private ArrayList<Result> things;
    public MAIN()
    {
        things = new ArrayList();
    }
    public void add(int id, String name, int value)
    {
        Result thing = new Result(id,name,value);
        things.add(thing);
    }
	public void remove(int index)
	{
		things.remove(index);
	}
}
