package fr.jamailun.ooapi.odt;

import fr.jamailun.jamlogger.JamLogger;
import fr.jamailun.ooapi.common.BadClassFormatException;
import fr.jamailun.ooapi.odt.draw.*;
import fr.jamailun.ooapi.odt.text.*;
import fr.jamailun.ooapi.xml.XmlNode;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class LibrairyODT {
	private final static Map<String, Constructor<? extends ODNode>> constructors = new HashMap<>();

	static {
		try {
			registerClass(FrameNode.class);
			registerClass(ImageNode.class);
			registerClass(TextBoxNode.class);
			registerClass(CoreTextNode.class);
			registerClass(ParagraphNode.class);
			registerClass(RawTextNode.class);
			registerClass(SequenceNode.class);
			registerClass(SpanNode.class);
			registerClass(TabNode.class);

		} catch(BadClassFormatException e) {
			JamLogger.error("FATAL on loading default extension : " + e.getMessage());
		}
	}

	public static <T extends ODNode> void registerClass(Class<T> clazz) throws BadClassFormatException {
		registerClass(null, clazz);
	}

	public static <T extends ODNode> void registerClass(String xmlName, Class<T> clazz) throws BadClassFormatException {
		Constructor<T> constructor;
		try {
			constructor = clazz.getConstructor(XmlNode.class);
		} catch(NoSuchMethodException e) {
			throw new BadClassFormatException(clazz, "Could not find 'xmlnode' based on default construcotr.");
		}

		//XML_NAME constructor
		if(xmlName == null) {
			try {
				Field f = clazz.getDeclaredField("XML_NAME");
				xmlName = (String) f.get(null);
			} catch(NoSuchFieldException | IllegalAccessException | IllegalArgumentException | ClassCastException e) {
				throw new BadClassFormatException(clazz, "Could not find 'xmlnode' based on default construcotr.");
			}
		}

		constructors.put(xmlName, constructor);
	}

	public static Constructor<? extends ODNode> get(String xmlName) {
		return constructors.get(xmlName);
	}

	public static <T extends ODNode> T create(XmlNode xmlNode) {
		try {
			return (T) get(xmlNode.getName()).newInstance(xmlNode);
		} catch(InstantiationException | IllegalAccessException | InvocationTargetException e) {
			JamLogger.error("c'est mal codé");
			throw new RuntimeException("Weird error. " + e.getClass().getSimpleName() + " : " + e.getMessage());
		}
	}

	public static boolean contains(String xmlName) {
		return constructors.containsKey(xmlName);
	}

	public static boolean containsNodeName(XmlNode xmlNode) {
		return contains(xmlNode.getName());
	}
}