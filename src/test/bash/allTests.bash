#!/bin/bash

BASEURL="http://localhost:8080/"

#check is server is responding to GETs
function init {
	RESPONSE=$(curl -s $BASEURL"api/get/") 
	if [ $? -ne 0 ]; then
		echo "FAIL : curl error on initial GET"
		exit 1;
	fi
}
function createProject {
	local PROJECT=${1}
    	local keys=${2}	
	#be sure no project by that name exists before creating
	curl -s $BASEURL"api/get/" | grep --silent $PROJECT
	if [ $? -eq 0 ]; then
		echo "FAIL : unable to perform test; Table with identical random string exists"
		exit 1;
	fi
	#create a new project
	RESPONSE=$(curl -s -X POST --header "Content-type: text/plain" --data "$KEYS" $BASEURL"api/post/$PROJECT/")
	if [ "OK" != "$RESPONSE" ]; then
		echo "Service did not say OK to my create project POST";
		exit 1;
	fi
	GREPSTRING='{"projects":.*"'"$PROJECT"'".*}'
	curl -s $BASEURL"api/get/" | grep --silent $GREPSTRING
	if [ $? -eq 1 ]; then
		echo "FAIL : Unable to locate the freshly created project"
	fi

	#GSON outputs with no spaces; YMMV on the $KEYS string
	curl -s $BASEURL"api/get/$PROJECT/keys" | grep --silent "$KEYS"
	if [ $? -ne 0 ]; then
		echo "FAIL : Did not find the keys I just POSTed"
		exit 1;
	fi
}
function addValues {
	local PROJECT=${1}
    	local ENVIRONEMT=${2}	
    	local KEYVALUES=${3}	
	local VALUE3=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 12 | head -n 1)
	#echo $KEYVALUES
	#echo $VALUE3

	#post a few
	RESPONSE=$(curl -s -X POST --header "Content-type: text/plain" --data "$KEYVALUES" $BASEURL"api/post/$PROJECT/$ENVIRONEMT/")
	if [ "OK" != "$RESPONSE" ]; then
		echo "Service did not say OK to my add values POST";
		exit 1;
	fi
	RESPONSE=$(curl -s $BASEURL"api/get/$PROJECT/$ENVIRONEMT/")
	GREPSTRING='"'$KEY0'":"'$VALUE0'"'
	echo $RESPONSE | grep --silent $GREPSTRING
	if [ $? -ne 0 ]; then
		echo "FAIL : Key value pair 0 missing"
		exit 1;
	fi
	GREPSTRING='"'$KEY1'":"'$VALUE1'"'
	echo $RESPONSE | grep --silent $GREPSTRING
	if [ $? -ne 0 ]; then
		echo "FAIL : Key value pair 1 missing"
		exit 1;
	fi
	GREPSTRING='"'$KEY2'":"'$VALUE2'"'
	echo $RESPONSE | grep --silent $GREPSTRING
	if [ $? -ne 0 ]; then
		echo "FAIL : Key value pair 2 missing"
		exit 1;
	fi

	#put one
	RESPONSE=$(curl -s -X PUT $BASEURL"api/put/$PROJECT/$ENVIRONEMT/$KEY2/$VALUE3/")
	if [ "OK" != "$RESPONSE" ]; then
		echo "FAIL : PUT update on key did not say OK"
	fi
	curl -s $BASEURL"api/get/$PROJECT/$ENVIRONEMT/$KEY2/" | grep --silent "$VALUE3"
	if [ $? -ne 0 ]; then
		echo "FAIL : Key value pair 3 missing"
		exit 1;
	fi
}
function deleteProject {
	local PROJECT=${1}
	RESPONSE=$(curl -s -X DELETE $BASEURL"api/delete/$PROJECT/")
	if [ "OK" != "$RESPONSE" ]; then
		echo "Service did not say OK to my DELETE";
		exit 1;
	fi
}
#create, validate, add keys, validate, delete, a project
function cvkvdvp {
	PROJECT=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 12 | head -n 1)
	#you could make a better test that creates a random number of keys
	KEY0=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 12 | head -n 1)
	KEY1=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 12 | head -n 1)
	KEY2=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 12 | head -n 1)
	KEYS="[\""$KEY0"\",\""$KEY1"\",\""$KEY2"\"]"
	#echo $KEYS; exit 0;

	ENVIRONMENT=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 12 | head -n 1)
	VALUE0=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 12 | head -n 1)
	VALUE1=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 12 | head -n 1)
	VALUE2=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 12 | head -n 1)
	KEYVALUES="{\""$KEY0"\":\"$VALUE0\",\""$KEY1"\":\"$VALUE1\",\""$KEY2"\":\""$VALUE2"\"}"
	#echo $KEYVALUES; exit 0;


	createProject $PROJECT $KEYS
	addValues $PROJECT $ENVIRONMENT $KEYVALUES
	deleteProject $PROJECT
}
init
cvkvdvp
