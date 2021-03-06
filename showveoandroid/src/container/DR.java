package container;

import java.util.HashMap;

/*
 * A class used to resolve dependencies.
 */
public class DR {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members
	
	//	The underlying type dictionary.
	private final static HashMap<Class<?>,Object> _dictionary = new HashMap<Class<?>,Object>();
	
	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods
	
	/*
	 * Registers the given object into the dictionary using the given type.
	 * @param ifc The interface of the object to register.
	 * @param obj The object to register.
	 */
	public static <T> void register(Class<T> ifc, Object obj) {
		if (_dictionary.containsKey(ifc))
			_dictionary.remove(ifc);
		_dictionary.put(ifc, obj);
	}
	
	/*
	 * Retrieves a previously registered object using its interface.
	 * @param ifc The interface of the object to retrieve.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> ifc) {
		if (!_dictionary.containsKey(ifc))
			return null;
		return (T) _dictionary.get(ifc);
	}
	
}
