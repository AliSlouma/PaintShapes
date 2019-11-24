package eg.edu.alexu.csd.oop.draw.GUI;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

    private String loadpath;

    FileChooser() {
        loadpath = "";
    }

    public String ChoosePlace() {


        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //f.showSaveDialog(null);
        //FileNameExtensionFilter filter = new FileNameExtensionFilter(
            //  "Json & XML Files", "JsOn", "Xml");
        f.addChoosableFileFilter(new XmlSaveFilter());
        f.addChoosableFileFilter(new JsonSaveFilter());
        int retrival = f.showSaveDialog(null);
        String ext = "";
        if (retrival == JFileChooser.APPROVE_OPTION) {

            String extension = f.getFileFilter().getDescription();

            if (extension.equals("*.Xml,*.xml")) {
                ext = "Xml";
            }
            if (extension.equals("*.JsOn,*.json")) {
                ext = "JsOn";
            }
        }
        if(ext.equals(""))
        {
            ext = "Xml";
        }
        return f.getSelectedFile().toString() + "\\save." + ext;
    }

    public String getPath() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Json & XML Files", "JsOn", "xml");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            this.loadpath = chooser.getSelectedFile().toString();

        }
        return this.loadpath;
    }


    private class XmlSaveFilter extends FileFilter {

        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }

            String s = f.getName();

            return s.endsWith(".xml") || s.endsWith(".Xml");
        }

        public String getDescription() {
            return "*.xml,*.Xml";
        }
    }

    private class JsonSaveFilter extends FileFilter {
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }

            String s = f.getName();

            return s.endsWith(".json") || s.endsWith(".JsOn");
        }

        public String getDescription() {
            return "*.json,*.JsOn";
        }
    }
}