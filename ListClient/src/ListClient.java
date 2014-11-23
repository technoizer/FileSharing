
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muhammad Izzuddin
 */
public class ListClient implements Serializable{
    private ArrayList <String> names = new ArrayList<>();
    private File contentFile;

    /**
     * @return the names
     */
    public ArrayList <String> getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    public void setNames(ArrayList <String> names) {
        this.names = names;
    }

    /**
     * @return the contentFile
     */
    public File getContentFile() {
        return contentFile;
    }

    /**
     * @param contentFile the contentFile to set
     */
    public void setContentFile(File contentFile) {
        this.contentFile = contentFile;
    }
            
}
