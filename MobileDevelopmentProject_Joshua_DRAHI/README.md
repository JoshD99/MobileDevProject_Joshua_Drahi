# Mobile Development Project Report - Joshua DRAHI

## Restraining access
To limit the access to the application, I used a password to protect all the application. It is mandatory to use the application (the password is "MotDePasse" and is case-sensitive).
To secure a bit more the application, the password is not displayed clearly in the code because I used MD5 encryption to only compare hashes.

## Saving data
The data is saved using the MODE_PRIVATIVE argument to write in a file the string returned by the API. The file is suppressed in the event of an app uninstallation.

## Hiding API URL
I did not manage to hide the API URL, even though I tried with different methods including modifying the gradle.properties file to try to create a property in which would the URL be saved, so I would not have to write it in the classes.

## Screenshots
Five screenshots are included in the archive

 1. Login page (first activity)
 2. Login page in the case of an incorrect password (Toast function)
 3. Empty MyAccounts page (we did not synchronise with the API yet).
 4. Complete MyAccounts page (with 4G on)
 5. Complete MyAccounts page (without wireless connexion, after app restart)



