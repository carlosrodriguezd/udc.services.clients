package udc.services.internalgrpcapi.fakeclient.builders;

import com.google.protobuf.StringValue;

import udc.services.internalgrpcapi.fakeclient.helpers.RandomHelpers;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeBaseCenter;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeBaseEntity;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeBasePerson;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeIDDocument;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeIDDocumentType;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeStudent;

public class FakeStudentBuilder {

    private FakeStudent _fakeStudent;

    public static final String TEST_ID = "10000";
    public static final FakeIDDocument FAKE_TEST_ID_DOCUMENT = FakeIDDocument.newBuilder().setNumber("36991036K").setType(FakeIDDocumentType.FAKE_ID_DOCUMENT_TYPE_NIF).build();
      
    public static final String TEST_FIRST_NAME = "José Filogonio";
    public static final String TEST_SURNAME = "Juez";
    public static final String TEST_SECOND_SURNAME = "Bañuelos";
    public static final String TEST_USERNAME = "filogonio.juez";

    public static final FakeIDDocument TEST_NOT_EXISTING_FAKE_ID_DOCUMENT = FakeIDDocument.newBuilder().setNumber("00010001A").setType(FakeIDDocumentType.FAKE_ID_DOCUMENT_TYPE_NIF).build();
    public static final FakeIDDocument TEST_NOT_EXISTING_ID_DOCUMENT = FakeIDDocument.newBuilder().setNumber("0001$001A").setType(FakeIDDocumentType.FAKE_ID_DOCUMENT_TYPE_NIF).build();
    public static final FakeIDDocument TEST_EMPTY_FAKE_ID_DOCUMENT_NUMBER = FakeIDDocument.newBuilder().setNumber(" ").setType(FakeIDDocumentType.FAKE_ID_DOCUMENT_TYPE_NIF).build();
    public static final FakeIDDocument TEST_EMPTY_FAKE_ID_DOCUMENT_TYPE = FakeIDDocument.newBuilder().setNumber("36991036K").setType(FakeIDDocumentType.FAKE_ID_DOCUMENT_TYPE_UNSPECIFIED).build();
    
    public static final String TEST_EMPTY_STRING = " ";
    public static final String TEST_NOT_EXISTING_ID = "00000";
    public static final String TEST_INVALID_ID = "00$11";
    public static final String TEST_INVALID_FIRSTNAME = "Filo-gonio";
    public static final String TEST_INVALID_SURNAME = "Ju-ez";
    public static final String TEST_INVALID_SECOND_SURNAME = "Bañu-elos";
    public static final String TEST_INVALID_USERNAME = "filo-gonio.juez";	
	   
    public FakeStudentBuilder()
    {
        _fakeStudent = withDefaultValues();
    }

    public FakeStudent build()
    {
        return _fakeStudent;
    }

    public FakeStudent withDefaultValues()
    {
    	FakeBaseEntity fakeBaseEntity = FakeBaseEntity
    			.newBuilder()
    			.setId(StringValue.newBuilder().setValue(TEST_ID))
    			.build();
       	FakeBasePerson fakeBasePerson = FakeBasePerson
    			.newBuilder()
    			.setFakeBaseEntity(fakeBaseEntity)
    			.setFakeIdDocument(FAKE_TEST_ID_DOCUMENT)
    			.setFirstName(TEST_FIRST_NAME)
    			.setSurname(TEST_SURNAME)
    			.setSecondSurname(StringValue.newBuilder().setValue(TEST_SECOND_SURNAME))
    			.build();
    	_fakeStudent = FakeStudent
     		.newBuilder()
     		.setFakeBasePerson(fakeBasePerson)
     		.setUsername(StringValue.newBuilder().setValue(TEST_USERNAME))
     		.build();

    	return _fakeStudent;
    }

    public FakeStudent withRandomValuesAndNif()
    {
        String randomIDDocumentNumber = RandomHelpers.generateRandomNifIDDocumentNumber();
        String randomFirstName = RandomHelpers.generateRandomFirstName();
        String randomSurname = RandomHelpers.generateRandomLastName();
        String randomSecondSurname = RandomHelpers.generateRandomLastName();
        String username = RandomHelpers.generateRandomUsername(randomFirstName, randomSurname, randomSecondSurname);

    	FakeBaseEntity fakeBaseEntity = FakeBaseEntity
    			.newBuilder()
    			.build();
       	FakeBasePerson fakeBasePerson = FakeBasePerson
    			.newBuilder()
    			.setFakeBaseEntity(fakeBaseEntity)
    			.setFakeIdDocument(FAKE_TEST_ID_DOCUMENT)
    			.setFirstName(randomFirstName)
    			.setSurname(randomSurname)
    			.setSecondSurname(StringValue.newBuilder().setValue(randomSecondSurname).build())
    			.build();
    	_fakeStudent = FakeStudent
     		.newBuilder()
     		.setFakeBasePerson(fakeBasePerson)
     		.setUsername(StringValue.newBuilder().setValue(username).build())
     		.build();     
 
        return _fakeStudent;
    }	
    
    public FakeStudent withRandomValuesAndEmptyIDDocumentNumber()
    {
        String randomIDDocumentNumber = RandomHelpers.generateRandomNifIDDocumentNumber();
        String randomFirstName = RandomHelpers.generateRandomFirstName();
        String randomSurname = RandomHelpers.generateRandomLastName();
        String randomSecondSurname = RandomHelpers.generateRandomLastName();
        String username = RandomHelpers.generateRandomUsername(randomFirstName, randomSurname, randomSecondSurname);

    	FakeBaseEntity fakeBaseEntity = FakeBaseEntity
    			.newBuilder()
    			.build();
       	FakeBasePerson fakeBasePerson = FakeBasePerson
    			.newBuilder()
    			.setFakeBaseEntity(fakeBaseEntity)
    			.setFakeIdDocument(TEST_EMPTY_FAKE_ID_DOCUMENT_NUMBER)
    			.setFirstName(randomFirstName)
    			.setSurname(randomSurname)
    			.setSecondSurname(StringValue.newBuilder().setValue(randomSecondSurname).build())
    			.build();
    	_fakeStudent = FakeStudent
     		.newBuilder()
     		.setFakeBasePerson(fakeBasePerson)
     		.setUsername(StringValue.newBuilder().setValue(username).build())
     		.build();
        
 
        return _fakeStudent;
    }	

    public FakeStudent withNewID(FakeStudent fakeStudent, String newID)
    {
    	FakeBaseEntity fakeBaseEntity = FakeBaseEntity
    			.newBuilder()
    			.setId(StringValue.newBuilder().setValue(newID))
    			.build();
       	FakeBasePerson fakeBasePerson = FakeBasePerson
    			.newBuilder()
    			.setFakeBaseEntity(fakeBaseEntity)
    			.setFakeIdDocument(FAKE_TEST_ID_DOCUMENT)
    			.setFirstName(fakeStudent.getFakeBasePerson().getFirstName())
    			.setSurname(fakeStudent.getFakeBasePerson().getSurname())
    			.setSecondSurname(fakeStudent.getFakeBasePerson().getSecondSurname())
    			.build();
    	_fakeStudent = FakeStudent
     		.newBuilder()
     		.setFakeBasePerson(fakeBasePerson)
     		.setUsername(StringValue.newBuilder().setValue(fakeStudent.getUsername().getValue()))
     		.build();

    	return _fakeStudent;
    }

    public FakeStudent withNewIDDocumentNumber(FakeStudent fakeStudent, String idDocumentNumber, FakeIDDocumentType fakeIDDocumentType)
    {
  
    	FakeBaseEntity.Builder fakeBaseEntityBuilder = FakeBaseEntity.newBuilder();
    	if (fakeStudent.getFakeBasePerson().getFakeBaseEntity().getId().getValue() != null && !fakeStudent.getFakeBasePerson().getFakeBaseEntity().getId().getValue().isBlank())
    	{
    		fakeBaseEntityBuilder.setId(fakeStudent.getFakeBasePerson().getFakeBaseEntity().getId());
    	}
    	FakeBaseEntity fakeBaseEntity = fakeBaseEntityBuilder.build();
    	
       	FakeBasePerson fakeBasePerson = FakeBasePerson
    			.newBuilder()
    			.setFakeBaseEntity(fakeBaseEntity)
    			.setFakeIdDocument(FakeIDDocument.newBuilder().setNumber(idDocumentNumber).setType(fakeIDDocumentType).build())
    			.setFirstName(fakeStudent.getFakeBasePerson().getFirstName())
    			.setSurname(fakeStudent.getFakeBasePerson().getSurname())
    			.setSecondSurname(fakeStudent.getFakeBasePerson().getSecondSurname())
    			.build();
    	_fakeStudent = FakeStudent
     		.newBuilder()
     		.setFakeBasePerson(fakeBasePerson)
     		.setUsername(StringValue.newBuilder().setValue(fakeStudent.getUsername().getValue()))
     		.build();

    	return _fakeStudent;
    }
}
