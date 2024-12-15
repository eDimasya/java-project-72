run-install-dir :
	./app/gradlew -p ./app installDist

build :
	./app/gradlew -p ./app build

run :
	./app/gradlew -p ./app run

checkstyle-main :
	./app/gradlew -p ./app checkstyleMain

report:
	./app/gradlew -p ./app jacocoTestReport

.PHONY: build