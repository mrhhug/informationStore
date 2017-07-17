# informationStore
Application used to solve the problem of emailing dated spreadsheeting back and forth, or using community viewable and editable documents.

Current common solutions are :

emailing documents
---
 * versioning
 * god source - only X person can make changes
 * passwords must be in seperate document or exposed
 * hope the god source never has a hdd crash
 * ugly
 * can be information overload, too much data when you just want (for example) the username

shared docs
---
 * fat fingering because anyone can make changes
 * access rights to shared disk
 * passwords must be in seperate document or exposed
 * sure we all trust google docs to not crash... but is that where you want your root passwords stored?
 * OneNote also has to be stored in a central location, whos in charge of backups? the other guy
 * ugly
Spreadsheets get dated. "did you see I spelled the url wrong"? "No when did you change that"?
Spreadsheets and shared docs do not have a way to restrict information.
* You may want to share you endpoint URLs with everyone, but not the admin password.

database driven information store
===
* Custom security levels
* database can be stored in HA environments like CloudFoundry
* Backups can be automated and pipelined into regular environmental backups
* custom UIs
