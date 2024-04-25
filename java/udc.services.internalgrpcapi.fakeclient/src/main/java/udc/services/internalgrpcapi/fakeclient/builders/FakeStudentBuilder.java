package udc.services.internalgrpcapi.fakeclient.builders;

import com.google.protobuf.StringValue;

import udc.services.internalgrpcapi.fakeclient.helpers.RandomHelpers;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeBaseEntity;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeBasePerson;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeIdDocument;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeIdDocumentType;
import udc.services.internalgrpcapi.fakeclient.protos.fake.Fake.FakeStudent;

public class FakeStudentBuilder {

    private FakeStudent _fakeStudent;

    public static final String TEST_ID = "10000";
    public static final FakeIdDocument FAKE_TEST_ID_DOCUMENT = FakeIdDocument.newBuilder().setNumber("36991036K").setType(FakeIdDocumentType.FAKE_ID_DOCUMENT_TYPE_NIF).build();
      
    public static final String TEST_FIRST_NAME = "José Filogonio";
    public static final String TEST_SURNAME = "Juez";
    public static final String TEST_SECOND_SURNAME = "Bañuelos";
    public static final String TEST_USERNAME = "filogonio.juez";

    public static final FakeIdDocument TEST_NOT_EXISTING_FAKE_ID_DOCUMENT = FakeIdDocument.newBuilder().setNumber("00010001A").setType(FakeIdDocumentType.FAKE_ID_DOCUMENT_TYPE_NIF).build();
    public static final FakeIdDocument TEST_NOT_EXISTING_ID_DOCUMENT = FakeIdDocument.newBuilder().setNumber("0001$001A").setType(FakeIdDocumentType.FAKE_ID_DOCUMENT_TYPE_NIF).build();
    public static final FakeIdDocument TEST_EMPTY_FAKE_ID_DOCUMENT_NUMBER = FakeIdDocument.newBuilder().setNumber(" ").setType(FakeIdDocumentType.FAKE_ID_DOCUMENT_TYPE_NIF).build();
    public static final FakeIdDocument TEST_EMPTY_FAKE_ID_DOCUMENT_TYPE = FakeIdDocument.newBuilder().setNumber("36991036K").setType(FakeIdDocumentType.FAKE_ID_DOCUMENT_TYPE_UNSPECIFIED).build();
    
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
        String randomIdDocumentNumber = RandomHelpers.generateRandomNifIdDocumentNumber();
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
    
    public FakeStudent withRandomValuesAndEmptyIdDocumentNumber()
    {
        String randomIdDocumentNumber = RandomHelpers.generateRandomNifIdDocumentNumber();
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

    public FakeStudent withNewId(FakeStudent fakeStudent, String newId)
    {
    	FakeBaseEntity fakeBaseEntity = FakeBaseEntity
    			.newBuilder()
    			.setId(StringValue.newBuilder().setValue(newId))
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

    public FakeStudent withNewIdDocumentNumber(FakeStudent fakeStudent, String idDocumentNumber, FakeIdDocumentType fakeIdDocumentType)
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
    			.setFakeIdDocument(FakeIdDocument.newBuilder().setNumber(idDocumentNumber).setType(fakeIdDocumentType).build())
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
