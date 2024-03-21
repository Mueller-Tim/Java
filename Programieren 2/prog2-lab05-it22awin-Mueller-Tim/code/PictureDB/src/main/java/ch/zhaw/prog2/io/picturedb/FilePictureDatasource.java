package ch.zhaw.prog2.io.picturedb;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Implements the PictureDatasource Interface storing the data in
 * Character Separated Values (CSV) format, where each line consists of a record
 * whose fields are separated by the DELIMITER value ";"
 * See example file: db/picture-data.csv
 */
public class FilePictureDatasource implements PictureDatasource {
    // Charset to use for file encoding.
    protected static final Charset CHARSET = StandardCharsets.UTF_8;
    // Delimiter to separate record fields on a line
    protected static final String DELIMITER = ";";
    // Date format to use for date specific record fields
    protected static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final Logger logger = Logger.getLogger(LogConfiguration.class.getCanonicalName());

    private File original;

    private File tempfile;

    /**
     * Creates the FilePictureDatasource object with the given file path as datafile.
     * Creates the file if it does not exist.
     * Also creates an empty temp file for write operations.
     *
     * @param filepath of the file to use as database file.
     * @throws IOException if accessing or creating the file fails
     */
    public FilePictureDatasource(String filepath) throws IOException {
        // ToDo: Implement
        original = new File(filepath);
        if(original.isDirectory()){
            logger.severe("Filepath is a directory!");
            throw new IOException("Filepath is a directory!");
        }
        if(!original.exists()){
            original.createNewFile();
            logger.info("Created new file: " + original.getAbsolutePath());
        }
        tempfile = new File("db/temp.csv");
        if(tempfile.exists()){
            tempfile.delete();
            logger.info("Deleted old temp file: " + tempfile.getAbsolutePath());
        }
        tempfile.createNewFile();
        logger.info("Created new file: " + tempfile.getAbsolutePath());
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void insert(Picture picture) {
        // ToDo: Implement
        try(PrintWriter writer = new PrintWriter(new FileWriter(tempfile, true))) {
            for (Picture pic : findAll()) {
                writer.append(createExportStringFromPicture(pic)).append("\n");
                logger.info("Writing picture to temp file: " + pic);
                writer.flush();
            }
            picture.setId(getNextID());
            writer.append(createExportStringFromPicture(picture));
            logger.info("Writing picture to temp file: " + picture);
            logger.fine("Finsihed writing to temp file!");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while inserting to file!", e);
        }
        try {
            Files.move(tempfile.toPath(), original.toPath() ,REPLACE_EXISTING);
            logger.info("Moved " + tempfile.getAbsolutePath() + " to " + original.getAbsolutePath());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error moving file!", e);
        }
    }

    private String createExportStringFromPicture(Picture picture){
        return picture.getId() + DELIMITER + dateFormat.format(picture.getDate()) + DELIMITER + picture.getLongitude() + DELIMITER + picture.getLatitude() + DELIMITER + picture.getTitle() + DELIMITER + picture.getUrl();
    }

    private int getNextID() {
        int id = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("db/picture-data.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(DELIMITER);
                if(Integer.parseInt(fields[0]) > id){
                    id = Integer.parseInt(fields[0]);
                }
            }
            logger.fine("Max ID: " + id);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading from file!", e);
        }
        logger.fine("Next ID: " + (id + 1));
        return id + 1;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void update(Picture picture) throws RecordNotFoundException {
        // ToDo: Implement
        if(findById(picture.getId()) == null){
            logger.severe("Picture does not exist!");
            throw new RecordNotFoundException("Picture does not exist!");
        }
        try(PrintWriter writer = new PrintWriter(new FileWriter(tempfile, true))) {
            for (Picture pictureInCollection : findAll()){
                if(pictureInCollection.getId() == picture.getId()){
                    writer.append(createExportStringFromPicture(picture));
                    writer.append("\n");
                    logger.info("Writing picture to temp file: " + picture);
                } else {
                    writer.append(createExportStringFromPicture(pictureInCollection));
                    writer.append("\n");
                    logger.info("Writing picture to temp file: " + pictureInCollection);
                }
            }
            logger.fine("Finsihed writing to temp file!");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while updating records!", e);
        }
        try {
            Files.move(tempfile.toPath(), original.toPath() ,REPLACE_EXISTING);
            logger.info("Moved " + tempfile.getAbsolutePath() + " to " + original.getAbsolutePath());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error moving file!", e);
        }
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void delete(Picture picture) throws RecordNotFoundException {
        // ToDo: Implement
        if(findById(picture.getId()) == null){
            logger.severe("Picture does not exist!");
            throw new RecordNotFoundException("Picture does not exist!");
        }
        try(PrintWriter writer = new PrintWriter(new FileWriter(tempfile, true))) {
            for (Picture pictureInCollection : findAll()){
                if(!pictureInCollection.equals(picture)){
                    writer.append(createExportStringFromPicture(pictureInCollection));
                    logger.info("Writing picture to temp file: " + pictureInCollection);
                }
            }
            logger.fine("Finsihed writing to temp file!");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error deleting picture from file!", e);
        }
        try {
            Files.move(tempfile.toPath(), original.toPath() ,REPLACE_EXISTING);
            logger.info("Moved " + tempfile.getAbsolutePath() + " to " + original.getAbsolutePath());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error moving file!", e);
        }
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public long count() {
        // ToDo: Correct Implementation
        return findAll().size();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Picture findById(long id) {
        // ToDo: Correct Implementation
        for (Picture picture : findAll()){
            if(picture.getId() == id){
                logger.info("Found picture with id " + id);
                return picture;
            }
        }
        logger.warning("Picture with id " + id + " not found!");
        return null;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Collection<Picture> findAll() {
        // ToDo: Correct Implementation
        Collection<Picture> pictures = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(original))) {
            String line;
            while ((line = reader.readLine()) != null) {
                pictures.add(parseToPicture(line));
                logger.info("Added picture to collection: " + line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading from file while finding all pictures!", e);
        }
        logger.info("Found " + pictures.size() + " pictures!");
        return pictures;
    }

    private Picture parseToPicture(String data){
        String[] values = data.split(DELIMITER);
        Picture picture = null;
        try {
            picture = new Picture(Integer.parseInt(values[0]), new URL(values[5]), dateFormat.parse(values[1]), values[4], Float.parseFloat(values[2]), Float.parseFloat(values[3]));
        } catch (ParseException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return picture;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Collection<Picture> findByPosition(float longitude, float latitude, float deviation) {
        // ToDo: Correct Implementation
        Collection<Picture> pictures = new ArrayList<>();
        for (Picture picture : findAll()){
            if(picture.getLongitude() >= longitude - deviation && picture.getLongitude() <= longitude + deviation && picture.getLatitude() >= latitude - deviation && picture.getLatitude() <= latitude + deviation){
                pictures.add(picture);
                logger.info("Found picture with position " + longitude + " " + latitude + " " + deviation);
            }
        }
        logger.info("Found " + pictures.size() + " pictures!");
        return pictures;
    }

}
