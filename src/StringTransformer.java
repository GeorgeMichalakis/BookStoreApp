
public class StringTransformer {
	
	public String AccentRemovalUpperCase(String untransformedString) {
		
		String transformedString = untransformedString.toUpperCase();
		
		transformedString = transformedString.replaceAll("�","�");
		transformedString = transformedString.replaceAll("�","�");
		transformedString = transformedString.replaceAll("�","�");
		transformedString = transformedString.replaceAll("�","�");
		transformedString = transformedString.replaceAll("�","�");
		transformedString = transformedString.replaceAll("�","�");
		transformedString = transformedString.replaceAll("�","�");
		transformedString = transformedString.replace("'", "");
		
		return transformedString;
	}

}
