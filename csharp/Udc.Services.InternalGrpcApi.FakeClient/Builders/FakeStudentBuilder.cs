using Udc.Services.InternalGrpcApi.FakeClient.Helpers;
using Udc.Services.Protos.Fake;

namespace Udc.Services.InternalGrpcApi.FakeClient.Builders;

public class FakeStudentBuilder
{
    private FakeStudent _fakeStudent;

    public string TestId => "10000";
    public FakeIDDocument FakeTestIDDocument => new() { Number = "36991036K", Type = FakeIDDocumentType.Nif };
    public string TestFirstName => "José Filogonio";
    public string TestSurname => "Juez";
    public string TestSecondSurname => "Bañuelos";
    public string? TestUsername => "filogonio.juez";

    public FakeIDDocument TestNotExistingFakeIDDocument => new() { Number = "00010001A", Type = FakeIDDocumentType.Nif };
    public FakeIDDocument TestInvalidFakeIDDocument => new() { Number = "0001$001A", Type = FakeIDDocumentType.Nif };
    public FakeIDDocument TestEmptyFakeIDDocumentNumber => new() { Number = " ", Type = FakeIDDocumentType.Nif };
    public FakeIDDocument TestEmptyFakeIDDocumentType => new() { Number = "36991036K", Type = FakeIDDocumentType.Unspecified };

    public const string TestEmptyString = " ";
    public const string TestNotExistingId = "00000";
    public const string TestInvalidId = "00$11";
    public const string TestInvalidFirstname = "Filo-gonio";
    public const string TestInvalidSurname = "Ju-ez";
    public const string TestInvalidSecondSurname = "Bañu-elos";
    public const string TestInvalidUsername = "filo-gonio.juez";

    public FakeStudentBuilder()
    {
        _fakeStudent = WithDefaultValues();
    }

    public FakeStudent Build()
    {
        return _fakeStudent;
    }

    public FakeStudent WithDefaultValues()
    {
        _fakeStudent = new FakeStudent()
        {
            FakeBasePerson = new FakeBasePerson()
            {
                FakeBaseEntity = new FakeBaseEntity()
                {
                    Id = TestId
                },
                FakeIdDocument = FakeTestIDDocument,
                FirstName = TestFirstName,
                Surname = TestSurname,
                SecondSurname = TestSecondSurname,
            },
            Username = TestUsername
        };

        return _fakeStudent;
    }

    public FakeStudent WithRandomValuesAndNif()
    {
        string randomIDDocumentNumber = RandomHelpers.GenerateRandomNifIDDocumentNumber();
        string randomFirstName = RandomHelpers.GenerateRandomFirstName();
        string randomSurname = RandomHelpers.GenerateRandomLastName();
        string randomSecondSurname = RandomHelpers.GenerateRandomLastName();
        string username = RandomHelpers.GenerateRandomUsername(randomFirstName, randomSurname, randomSecondSurname);

        _fakeStudent = new FakeStudent()
        {
            FakeBasePerson = new FakeBasePerson()
            {
                FakeBaseEntity = new FakeBaseEntity()
                {
                    Id = null
                },
                FakeIdDocument = new() { Number = randomIDDocumentNumber, Type = FakeIDDocumentType.Nif },
                FirstName = randomFirstName,
                Surname = randomSurname,
                SecondSurname = randomSecondSurname,
            },
            Username = username
        };

        return _fakeStudent;
    }
}
