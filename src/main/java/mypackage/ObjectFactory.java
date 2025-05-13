
package mypackage;

import jakarta.xml.bind.annotation.XmlRegistry;
import gestormusica.Canso;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mypackage package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mypackage
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PlaylisFts }
     * 
     */
    public PlaylisFts createPlaylisFts() {
        return new PlaylisFts();
    }

    /**
     * Create an instance of {@link PlaylisFts.Playlist }
     * 
     */
    public PlaylisFts.Playlist createPlaylisFtsPlaylist() {
        return new PlaylisFts.Playlist();
    }

    /**
     * Create an instance of {@link PlaylisFts.Playlist.Canso }
     * 
     */
    public Canso createPlaylisFtsPlaylistCanso() {
        return new Canso();
    }

}
