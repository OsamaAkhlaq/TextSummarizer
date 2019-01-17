package textsummarizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//Reading &quot;Document1.txt&quot; in this class
public class FileReading {
public String readFile(String filename){
String FILENAME = filename;
String sCurrentLine = &quot;hello&quot;;
String result = &quot;&quot;;
try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

while ((sCurrentLine = br.readLine()) != null) {

result+=sCurrentLine;

}

} catch (IOException e) {
e.printStackTrace();
}

return result;

}
}
