# informationStore
Application to store project based information such as urls passwords and API endpoints
Intended to solve the problem of emailing dated spreadsheeting, or using community viewable and editable documents.
infomationStore is equiped with varying levels of read and write access 

informationStore is a database driven access controlled key/value store
===
* Custom security levels
* database can be stored in HA environments like CloudFoundry
* Backups can be automated and pipelined into typical environmental backups
* project wide variables
* custom UIs
* beautiful json API
* stable
* You may want to share you endpoint URLs with everyone, but not the admin password.
* Easily scriptable interaction

API enpoints
===
* GET http://<url>/api
  * @return json string of all projects
* GET http://<url>/api/<project>
  * @return json string of all project environments
* GET http://<url>/api/<project>/<environment>
  * @return json string of all key/value pairs for project environment

Problems with current common solutions are :

emailing documents
---
 * versioning
 * god source - only X person can make changes that are respected
 * passwords must be in seperate document or exposed
 * hope the god source never has a disk crash
 * ugly
 * can be information overload, too much data when you just want (for example) the username
 * not easy to script interaction

shared docs
---
 * fat fingering 
	because anyone can make changes
	have to copy and paste project wide variables
 * access rights to shared location
 * passwords must be in seperate document or exposed
 * sure we all trust google docs to not crash... but is that where you want your root passwords stored?
 * OneNote also has to be stored in a central location, whos in charge of backups? the other guy
 * ugly
 * must boot into GUI?
 * not easy to script interaction


