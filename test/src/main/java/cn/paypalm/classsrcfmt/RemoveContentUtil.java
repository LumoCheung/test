package cn.paypalm.classsrcfmt;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/** 
 * <p> Description:  </p>
 * @Author zhangzilu
 * @Date [2016年5月30日] 
 * @Version V1.0 
 * @修改记录  
 * <pre>
 * 版本号   修改人  修改时间     修改内容描述
 * ----------------------------------------
 * V1.0		张子路	 2016年5月30日	新建文件.
 * 
 * </pre>
 */
public class RemoveContentUtil {
	private static String regex = "\\/\\*[\\p{ASCII}]*?\\*\\/\\s?";
    //反编译后的java文件目录
    private static String directory = "/data";
    private static String newDirectory = "/com-src";     
     
    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException
    {
        start();
    }
     
    public static void start() throws IOException{
      
        File folder = new File(directory);
        directory = folder.getCanonicalPath();
         
        File parentFolder = folder.getParentFile();
        newDirectory = parentFolder.getCanonicalPath() + "\\" + folder.getName() + System.currentTimeMillis() + "\\";
        File[] files = folder.listFiles();
        loopFile(files);
    }
     
    public static void loopFile(File[] files) throws IOException{
        if(files == null){
            return;
        }
         
        for(File f : files){
            if(f.isFile()){
                if(f.getName().endsWith(".java")){
                    removeContent(directory, newDirectory, f, true);
                }else{
                    removeContent(directory, newDirectory, f, false);
                }
            }else{
                loopFile(f.listFiles());
            }
        }
    }
     
    public static void removeContent(String oldDir, String newDir, File file, boolean remove) throws IOException{
        List<String> lines = FileUtils.readLines(file);
        String relocationString = relocationLineNumber(lines);
        String newContent = remove ? relocationString.replaceAll(regex, "") : relocationString;
        File newFile = new File(file.getCanonicalPath().replace(oldDir, newDir));
        FileUtils.write(newFile, newContent);
    }
     
    public static String relocationLineNumber(List<String> lines){
        int currentLine = 0;
        StringBuilder content = new StringBuilder();
        for(String line : lines){
            currentLine++;
            int num = readLineNumber(line);
            if(num != -1){
                while(currentLine < num){
                    currentLine++;
                    content.append(IOUtils.LINE_SEPARATOR);
                }
            }
            content.append(line).append(IOUtils.LINE_SEPARATOR);
        }
        //IOUtils.LINE_SEPARATOR;
        return content.toString();
    }
     
    private static int readLineNumber(String line){
        int start = line.indexOf("/*");
        int end = line.indexOf("*/");
        if(start > -1 && end > start){
            //check is annotation
            String left = line.substring(end + 2).trim();
            if(left.startsWith("@")){
                return -1;
            }
             
            String linNum = line.substring(start + 2, end).trim();
            try{
                return Integer.parseInt(linNum);
            }catch(NumberFormatException e){
                return -1;
            }
        }
        return -1;
    }
}
