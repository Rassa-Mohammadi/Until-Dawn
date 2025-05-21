package com.tilldawn.model;

public enum Output {
    // fields
    EnterUsername("Enter username", "Entrez un nom d'utilisateur"),
    EnterPassword("Enter password", "Entrez un mot de passe"),
    EnterPasswordAgain("Enter password Again", "Entrez à nouveau le mot de passe"),
    Answer("Answer", "La Réponse"),
    EnterNewPassword("Enter new password", "Entrez un nouveau mot de passe"),
    // buttons
    Register("Register", "S'inscrire"),
    Login("Login", "Se connecter"),
    Exit("Exit", "Quitter"),
    Back("Back", "Retour"),
    Submit("Submit", "Soumettre"),
    Settings("Settings", "Paramètres"),
    Profile("Profile", "Profil"),
    Scoreboard("Scoreboard", "Tableau des scores"),
    Pregame("Pregame", "Avant-match"),
    Hint("Hint", "Indice"),
    ChangeLanguage("Change Language", "Changer de Langue"),
    PlayAsGuest("Play as Guest", "jouer en tant qu'invité"),
    Logout("Logout", "Déconnexion"),
    // titles
    RegisterMenu("Register Menu", "Menu d'inscription"),
    LoginMenu("Login Menu", "Menu de connexion"),
    MainMenu("Main Menu", "Menu principal"),
    SettingMenu("Setting Menu", "Menu de configuration"),
    RecoverPassword("Recover Password", "Récupérer le mot de passe"),
    // errors
    UsernameExists("Username already exists", "Ce nom d'utilisateur existe déjà"),
    PasswordEmpty("Password is empty", "Le mot de passe est vide"),
    AnswerEmpty("Answer is empty", "La réponse est vide"),
    ReenterPasswordError("Reenter password doesn't match", "Les mots de passe ne correspondent pas"),
    PasswordLength("Password must be at least 8 characters", "Le mot de passe doit contenir au moins 8 caractères"),
    PasswordSpecialCharacter("Password must contain special characters", "Le mot de passe doit contenir des caractères spéciaux"),
    PasswordNumber("Password must contain numbers", "Le mot de passe doit contenir des chiffres"),
    PasswordCapitalLetter("Password must contain capital letters", "Le mot de passe doit contenir des lettres majuscules"),
    UsernameNotFound("username not found", "nom d'utilisateur introuvable"),
    IncorrectPassword("Password is incorrect", "le mot de passe est incorrect"),
    IncorrectAnswer("Wrong answer", "Le mot de passe est incorrect"),
    // Security Questions
    FatherName("What is your father name?", "quel est le nom de ton père"),
    Turk("Are you Turk?", "Es tu Turk?"),
    // labels
    ForgotPassword("forgot password?", "mot de passe oublié?");

    private final String english;
    private final String french;

    Output(String english, String french) {
        this.english = english;
        this.french = french;
    }

    public String getString() {
        return App.isFrench()? french : english;
    }

    public static Output getPhrase(String phrase) {
        for (Output output : Output.values()) {
            if (output.english.equalsIgnoreCase(phrase) || output.french.equalsIgnoreCase(phrase))
                return output;
        }
        return null;
    }
}
