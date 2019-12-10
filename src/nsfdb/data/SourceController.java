package nsfdb.data;

/**
 * Class for getting a new database object based on the set source\
 * @author Justin Theophile
 *
 */
public class SourceController {
	public static Class<?>[] sources = { LocalDatabase.class, SQLDatabase.class };
	public static int source = 0;

	public static Database getNewDataSource() {
		try {
			Database obj = (Database) sources[source].newInstance();
			return obj;
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
