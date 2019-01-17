package textsummarizer;
import java.util.ArrayList;

public class Preprocessing implements InterfaceMethods {
    //list of stop words is taken from link http://www.lextek.com/manuals/onix/stopwords1.html
    public static String[] stopwords = {"a", "do","as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own" "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding",
"regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero","is","a","the","to","and","was","about","in","on","of","above","across","after","again","against","all","almost","alone","along","already","also","although","always","an","another","any","anybody","anyone","anything","anywhere","are","area","areas","around","as","ask","at","away","back"," backed","backing","backs","be","became","because","become","becomes","been","before","began","behind","being","beings","best","better","between","big","both","but","by","came","can","cannot","can't","case","cases","certain","certainly","clear","clearly","come","could","do","does","done","down","downed","during","don't","won't","each","early","either","end","ended","ending","ends","enough","even","evenly","ever","every","everybody","everyone","everything","everywhere","face","faces","fact","facts","far","felt","few","find","finds","found","first","for","four","from","full","fully","further","furthered","furthering","furthers","gave","give","general","generally","get","gets","given","gives","go","going","good","goods","got","great","greater","greatest","group","grouped","grouping","groups","had","has","have","having","he","her","here","herself","high","higher","highest","him","himself","his","how","however","if","important","in","interest","interested","interesting","interests","into","is","its","itself","it","just","keep","keeps","keeping","kind","knew","know","known","knows","large","largely","last","later","latest","least","less","let","lets","like","long","longer","longest","made","make","making","man","many","may","me","member","members","men","might","more","most","mostly","mr","mrs","much","must","my","myself","necessary","need","needs","needing","needed","never","new","newer","newest","next","no","nobody","non","none","nothing","now","nowhere","number","numbered","of","off","often","old","older","oldest","on","once","one","only","open","opens","opening","opened","or","order","ordered","ordering","orders","other","others","our","our","over","part","parted","parting","per","perhaps","place","places","point","pointed","pointing","points","possible","possiblility","present","presenting","presented","presents","problem","problems"," put","puts","quite","quitely","rather","right","room","rooms","said","same","saw","say","saying","says","stated","see","seeing","seem","seems","seemed","several","sees","several","shall","she","should","show","showed","showing","shows","side","sides","since","small","smaller","smallest","so","some","somebody","someone","something","somewhere","state","states","still","such","sure","take","taken","than","that","the","their","them","then","there","therefore","these","they","thing","things","think","thinks","this","those","though","thought","thoughts","three","through","thus","today","together","too","took","toward","turn","turned","turning","turns","two","under","until", "up","upon","us","use","used","uses","very","want","wanted","wanting","wants","was","way","ways","we","well","wells","went","were","what","when","where","whether","which","while","who","whole","whose","why","will","with","within","without","work","worked","working","works","would","year","years","yet","you","young","younger","youngest","your","yours"};

public static ArrayList<String> wordsList = new ArrayList<String>();
    public static String string;
    public String preprocessing(String input){
         this.string=input;
        return null;
    }
     public int[] FM(String[] s_array,String[] w_array,int s_c, int w_c){return null;}
         public String readFile(String filename){return null;}
          public String LowerCase(){return null;}
    public String removeStopWords(){
            // Remove Special characters and lowercase
string = string.toLowerCase();
            string=string.replaceAll("[\\p{Punct}&&[^.]]", "");
            string = string.trim().replaceAll("\\s+"," ");
          
            String[] words = string.split(" ");
for (String word : words) {
                wordsList.add(word);
            }	
//remove stop words here from the temp list
            for (int i = 0; i < wordsList.size(); i++) {
                // get the item as string
                for (int j = 0; j < stopwords.length; j++) {
                    if (stopwords[j].contains(wordsList.get(i))) {
                        wordsList.remove(i);
                    }
                }
            }
            string = "";
            for (String str : wordsList) {
                string += str+" ";
            }
            

return string;


    }

}
