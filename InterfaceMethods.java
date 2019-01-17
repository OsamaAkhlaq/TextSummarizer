package textsummarizer;
public interface InterfaceMethods
{

public String LowerCase();
public String removeStopWords();
public int[] FM(String[] s_array,String[] w_array,int s_c, int w_c);
public String readFile(String filename);

}

