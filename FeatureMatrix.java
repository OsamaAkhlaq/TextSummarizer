package textsummarizer;
public class FeatureMatrix implements InterfaceMethods {
    private  int M;             // number of rows     //  sentences
    private  int N;             // number of columns  // words
    public  static int[][] count;   // M-by-N array
    public static String[] sentence_array[] = null;
    public static String[] word_array[] = null;

    public FeatureMatrix(int M, int N) {
        this.M = M;
        this.N = N;
        count = new int[M][N];
    }
    public String LowerCase(){return null;}
        public String removeStopWords(){return null;}
        public String readFile(String filename){return null;}
    public int[] FM(String[] s_array,String[] w_array,int s_c, int w_c){
        String sentence;
        int sentence_counter = s_c;
        int word_counter = w_c;
        for(int i=0; i<s_c ; i++) {
            sentence=s_array[i];
            for(int j=0 ; j< w_c && w_array!=null; j++)
            {
            if(sentence.contains(w_array[j]+" ")){
               count[i][j]=count[i][j]+1;
            }

             }
        }
        // printing feature matrix
        for(int i=0; i<s_c ; i++) {
            for(int j=0 ; j< w_c && w_array!= null ; j++)
            {
            System.out.print(count[i][j]);
        }  
        System.out.println(" ");
    }
        // cosine similarity 
        //making a mean vector
        double[] sum = new double[w_c];
        double sum_temp = 0;
        for(int i=0; i<=w_c-1 ; i++)
        {
            for(int j=0; j<s_c ; j++)
            {
              sum_temp += count[j][i];  
            }
            sum[i]=(sum_temp/s_c);
            sum_temp = 0;
        }
        // printing the mean
        System.out.println("Mean Vector:");
        for(int k=0; k<=w_c-1 ; k++)

        {
            System.out.print(sum[k]);
        }
        // calculating cosine similarity
        double numerator=0;
double denominator = 0;
        double mag1;
        //String[] word_array = new String[counter_words]
        double[] sim = new double[w_c];
        double magnititude = 0.0;
        double mag = 0;
        // calculating the magnitude of mean array
        for(int j=0; j<w_c ; j++)
        {
            magnititude= magnititude + sum[j]*sum[j];
        }
        mag = Math.sqrt(magnititude);
        for(int i=0; i<s_c ; i++)
        {
            for(int j=0; j<w_c ; j++)
            {
                numerator = numerator +sum[j] * count[i][j];
                denominator =denominator + count[i][j]*count[i][j];
                          
            }
            mag1  = Math.sqrt(denominator);
            mag1 = mag1 * mag;	
            if(mag1 == 0 || numerator ==0)

            {
                sim[i] = 0;
            }
            else
            {
                sim[i] = numerator / mag1;
            }
            numerator = 0 ;
            denominator = 0;
        }
        // printing similarities
        System.out.println("Word Similarities\n Sentence \t\t similarity");
        for(int i=0; i< s_c ; i++)
        {
            System.out.println("Sentence "+i+"\t\t"+sim[i]);
        }
        // calculating 40% of the number of sentences
        double counter = s_c * 0.4;
        
        int c = (int) java.lang.Math.round(counter);
        if( c == 0)
        {
            c=1;
        }
        int[] Final = new int[c];
        int check=0;
        double max;
        int identifier=0;
for(int j=0; j<c; j++)
        {
            max = sim[j];
            for(int i =0; i< s_c ; i++)
            {
                if(max < sim[i])
                {
                    max = sim[i];
                    identifier = i;
                }
}
Final[check]=identifier;
                sim[identifier] = 0;
                check++;
                identifier = 0;
        }
       int temp ;
        for (int i = 0; i < c; i++) 
        {
            for (int j = i + 1; j < c; j++) 
            {
                if (Final[i] > Final[j]) 
                {
                    temp = Final[i];
                    Final[i] = Final[j];
                    Final[j] = temp;
                }
            }
        }
        return Final;
    }
}
