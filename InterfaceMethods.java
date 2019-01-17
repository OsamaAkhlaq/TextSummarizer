package textsummarizer;
public interface InterfaceMethods
{

public String LowerCase();
public String removeStopWords();
public int[] FM(String[] s_array,String[] w_array,int s_c, int w_c);
public String readFile(String filename);

}
package textsummarizer;
import java.io.Reader;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class TextSummarizer {
public static void main(String[] args)throws IOException {
NLP obj = new NLP();
obj.MyNewMethod();
}
}
