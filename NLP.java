package textsummarizer;
import com.sun.xml.internal.ws.util.StringUtils;
import java.io.Reader;
import java.io.*;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class NLP extends Preprocessing {
    public static int counter_sentences=0;
    public static int counter_words=0;
    public static int counter=0;
    public static String sentenceWord;
    public static String input;	
    public static String original;
    public void MyNewMethod() throws IOException
    {
        FileReading mynlp = new FileReading();
        original=input=mynlp.readFile("Document1.txt");
        Preprocessing pre = new Preprocessing();
        preprocessing(input);
        input=removeStopWords();
        StringTokenizer st = new StringTokenizer(input," "); 
        
         counter_words=st.countTokens();
         String[] word_array = new String[counter_words];

         int i=0; 
         String current="";
         // Storing tokens in an array
         while(st.hasMoreTokens()&&i<=counter_words)
         {
             word_array[i]=st.nextToken();
             if(current.contains(word_array[i]))
             {
                // if the word is already in the list don't add the word in the list 
             }
             else //otherwise add the word in the list
             {
                current += word_array[i]+" ";
                i++;
             }
             
         }
         // printing the string for checking
         for(int j =0; j<word_array.length && word_array[j]!=null; j++)
         {
           
            counter_words = j;
         }
        // to identify sentences
        Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)", Pattern.MULTILINE | Pattern.COMMENTS);
        Matcher reMatcher = re.matcher(input);
        String[] sentence_array = new String[counter_words];
        int s=0;
while (reMatcher.find()) {
        counter_sentences++;
        sentence_array[s] = reMatcher.group();
        s++;
        }
        FeatureMatrix matrix = new FeatureMatrix(counter_sentences,counter_words);
        int[] summary;
        summary=matrix.FM(sentence_array,word_array,counter_sentences,counter_words);
        Matcher Matcher1 = re.matcher(original);
        String[] original_array = new String[counter_sentences];
        int r=0;
        while (Matcher1.find()&&r<counter_sentences) {
        original_array[r] = Matcher1.group();
        r++;
        }
        FileWriter out = null;
        out = new FileWriter("output.txt");
        int temp =0 ;
        System.out.println("Summary: ");
        out.write("Summary: \n");
        for(int k=0 ; k<summary.length ; k++)
        {
            temp = summary[k];
            System.out.println(original_array[temp]);
            out.write("\n"+original_array[temp]);
        }
         if (out != null) {
            out.close();
         }
    }
}
