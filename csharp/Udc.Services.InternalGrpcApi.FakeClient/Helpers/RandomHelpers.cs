using RandomNameGeneratorLibrary;

namespace Udc.Services.InternalGrpcApi.FakeClient.Helpers;

public static class RandomHelpers
{

    private static PersonNameGenerator _personGenerator = new();

    public static string GenerateRandomNifIDDocumentNumber()
    {
        string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char letter = chars[new Random().Next(0, chars.Length)];
        return $"{new Random().Next(10000000, 99999999)}{letter}";
    }

    public static string GenerateRandomFirstName()
    {
        return new PersonNameGenerator().GenerateRandomFirstName();
    }

    public static string GenerateRandomLastName()
    {
        return new PersonNameGenerator().GenerateRandomLastName();
    }

    public static string GenerateRandomUsername()
    {
        PersonNameGenerator personGenerator = new();
        string randomFirstName = personGenerator.GenerateRandomFirstName();
        string randomSurname = personGenerator.GenerateRandomLastName();
        string randomSecondSurname = personGenerator.GenerateRandomLastName();
        return $"{randomFirstName}.{randomSurname}.{randomSecondSurname}".ToLower();
    }

    public static string GenerateRandomUsername(string firstName, string surname, string? secondSurname)
    {
        return string.IsNullOrWhiteSpace(secondSurname)
            ? $"{firstName}.{surname}.{secondSurname}"
            : $"{firstName}.{surname}".ToLower();
    }
}

