package ch.zhaw.prog2.io;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DirList {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Print metadata of a given file, resp. all of its files if it is a directory.
     * Use {@link #printFileMetadata(File)} to print file metadata.
     */
    public static void main(String[] args) {
        String pathName = args.length >= 1 ? args[0] : ".";
        File file = new File(pathName);
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                System.out.println(printFileMetadata(subFile));
            }
        } else {
            System.out.println(printFileMetadata(file));
        }
    }

    /** Write metadata of given file on a line with the following format:<br>
     * - type of file ('d'=directory, 'f'=file)<br>
     * - readable   'r', '-' otherwise<br>
     * - writable   'w', '-' otherwise<br>
     * - executable 'x', '-' otherwise<br>
     * - hidden     'h', '-' otherwise<br>
     * - modified date in format 'yyyy-MM-dd HH:mm:ss'<br>
     * - length in bytes<br>
     * - name of the file<br>
     *
     * @param file file to print metadata
     * @return String formatted as described above.
     */
    public static String printFileMetadata(File file) {
        return String.format("%c%c%c%c%c %s %8d %s",
            file.isDirectory()? 'd' : 'f',
            file.canRead()? 'r' : '-',
            file.canWrite()? 'w' : '-',
            file.canExecute()? 'x' : '-',
            file.isHidden()? 'h' : '-',
            dateFormat.format(file.lastModified()),
            file.length(),
            file.getName());
    }
}
