#/bin/sh

set -e

# --info
./gradlew assemble 

java -jar build/libs/weather_report-0.0.1-SNAPSHOT.jar