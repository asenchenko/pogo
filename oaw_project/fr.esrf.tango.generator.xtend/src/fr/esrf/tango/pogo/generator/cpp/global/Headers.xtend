package fr.esrf.tango.pogo.generator.cpp.global


import fr.esrf.tango.pogo.pogoDsl.PogoDeviceClass
import com.google.inject.Inject
import static org.eclipse.xtext.xtend2.lib.ResourceExtensions.*
import fr.esrf.tango.pogo.pogoDsl.Attribute
import fr.esrf.tango.pogo.pogoDsl.Command

class Headers {
	@Inject
	extension CppUtil
	@Inject
	extension TypeDefinitions

	def cvsEscaped (String s)       { "$"   + s + "  $"   }
	def cvsEscapedForVar (String s) { "\"$" + s + "  $\"" }

	//======================================================
	// header for device.h
	//======================================================
	def deviceIncludeFileName(PogoDeviceClass clazz) {
		clazz.name+".h"
	}
	def deviceIncludeFileHeader(PogoDeviceClass clazz) {
		fileHeader(deviceIncludeFileName(clazz),
			"Include file for the " + clazz.name + " class",
			 clazz.description.title
		)
	}


	//======================================================
	// header for deviceClass.h
	//======================================================
	def deviceClassIncludeFileName(PogoDeviceClass clazz) {
		clazz.name + "Class.h"
	}
	def deviceClassIncludeFileHeader(PogoDeviceClass clazz) {
		fileHeader(deviceClassIncludeFileName(clazz),
			"Include for the " + clazz.name +" root class.\n"+
			"This class is the singleton class for\n"+
			" the " + clazz.name + " device class.\n"+
			"It contains all properties and methods which the \n" +
			clazz.name + " requires only once e.g. the commands.",
			 clazz.description.title
		)
	}


	//======================================================
	// header for device.cpp
	//======================================================
	def deviceSourceFileName(PogoDeviceClass clazz) {
		clazz.name + ".cpp"
	}
	def deviceSourceFileHeader(PogoDeviceClass clazz) {
		fileHeader(deviceSourceFileName(clazz), 
			"C++ source for the " + clazz.name + " and its commands.\n" +
			"The class is derived from Device. It represents the\n" +
			"CORBA servant object which will be accessed from the\n" +
			"network. All commands which can be executed on the\n" +
			clazz.name + " are implemented in this file.",
			clazz.description.title
		)
	}
	
	
	//======================================================
	// header for deviceClass.cpp
	//======================================================
	def deviceClassSourceFileName(PogoDeviceClass clazz) {
		clazz.name + "Class.cpp"
	}
	def deviceClassSourceFileHeader(PogoDeviceClass clazz) {
		fileHeader(deviceClassSourceFileName(clazz),
			"C++ source for the " + clazz.name +" root class.\n"+
			"This class is the singleton class for\n"+
			" the " + clazz.name + " device class.\n"+
			"It contains all properties and methods which the \n" +
			clazz.name + " requires only once e.g. the commands.",
			clazz.description.title
		)
	}
	
	//======================================================
	// header for StateMachine.cpp
	//======================================================
	def stateMachineFileName(PogoDeviceClass clazz) {
		clazz.name + "StateMachine.cpp"
	}
	def stateMachineFileHeader(PogoDeviceClass clazz) {
		fileHeader(stateMachineFileName(clazz),
			"State machine file for the " + clazz.name + " class",
			 clazz.description.title
		)
	}
	
	//======================================================
	// RcsId for .cpp files
	//======================================================
	def rcsId(String filename) {
		if (filename.endsWith(".cpp"))
			"static const char *RcsId = " + "Id:".cvsEscapedForVar + ";\n"
		else
			""
	}
	
	
	//======================================================
	// generic file header
	//======================================================
	def fileHeader(String fileName, String description, String title) {
		rcsId(fileName) +
		"//=============================================================================\n" +
		"//\n"+
		"// file :        "+ fileName + "\n" +
		"//\n" +
		"// description : " + description.comments("//               ") + "\n" +
		"//\n" +
		"// project :     " + title + "\n" +
		"//\n" +
		"// " + "Author:".cvsEscaped + "\n" +
		"//\n" +
		"// " + "Revision:".cvsEscaped + "\n" +
		"// " + "Date:".cvsEscaped + "\n" +
		"//\n" +
		"// " + "HeadURL:".cvsEscaped() + "\n" +
		"//\n" +
		"//=============================================================================\n" +
		"//                This file is generated by POGO\n" +
		"//        (Program Obviously used to Generate tango Object)\n" +
		"//=============================================================================\n"
	}








	//======================================================
	/*
	 * Methods headers
	 */
	//======================================================
	//======================================================
	//	Simple method header
	//======================================================
	def simpleMethodHeader(PogoDeviceClass cls, String method, String description) {
		"//--------------------------------------------------------\n" +
		"/**\n" +
		" *	Method      : "+ cls.name + "::" + method + "()\n" +
		" *	Description : " + description + "\n" +
		" */\n" +
		"//--------------------------------------------------------"
	}

	//======================================================
	//	Attribute method header
	//======================================================
	def attributeReadMethodHeader(Attribute attr) {
		"/**\n" +
		" *	Attribute " + attr.name + " related mehods\n" +
		" *	Description: " + attr.properties.description.comments("	 *		") + "\n" +
		" *\n" +
		" *	Data type:	" + attr.dataType.cppType + "\n" +
		" *	Attr type:	" + attr.attType + attr.attTypeDimentions + "\n" + 
		" */\n"		
	}


	//======================================================
	//	Command method header
	//======================================================
	def commandExecutionMethodHeader(Command cmd) {
		"/**\n" +
		" *	Command " + cmd.name + " related mehods\n" +
		" *	Description: " + cmd.description.comments("	 *		") + "\n" +
		" *\n" +
		" *	@param argin " + cmd.argin.description + "\n" +
		" *	@returns " + cmd.argout.description + "\n" +
		" */\n"		
	}
}