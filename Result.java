import java.util.*;
/**
 * Write a description of class result here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Result
{
    private int id;
    private String name;
    private int value;
    public Result(int id,String name,int value)
    {
        id = id;
        name = name;
        value = value;       
    }
    
    public String getName()
    {
        return name;
    }
}
