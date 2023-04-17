package udc.services.internalgrpcapi.fakeclient.helpers;

import java.util.Random;

import org.ajbrown.namemachine.NameGenerator;

public class RandomHelpers {

    private static NameGenerator _personGenerator = new NameGenerator();

    public static String generateRandomNifIDDocumentNumber()
    {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char letter = chars.charAt(new Random().nextInt(chars.length()));
        return String.format("%d%c", new Random(10000000).nextInt(99999999), letter);
    }

    public static String generateRandomFirstName()
    {
    	return _personGenerator.generateName().getFirstName();
    }

    public static String generateRandomLastName()
    {
    	return _personGenerator.generateName().getLastName();
    }

    public static String generateRandomUsername()
    {
    	String randomFirstName = _personGenerator.generateName().getFirstName();
    	String randomSurname = _personGenerator.generateName().getLastName();
    	String randomSecondSurname = _personGenerator.generateName().getLastName();
        return String.format("%s%s%s", randomFirstName, randomSurname, randomSecondSurname);
    }

    public static String generateRandomUsername(String firstName, String surname, String secondSurname)
    {
        return secondSurname == null || secondSurname.isEmpty()
            ? String.format("%s.%s", firstName, surname).toLowerCase()
            : String.format("%s.%s.%s", firstName, surname, secondSurname).toLowerCase();
    }
}	
