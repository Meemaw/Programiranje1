#!/bin/bash -e

javac SkritiTest.java
java SkritiTest
if ((errors > 0))
then
    echo "Tests failed."
    exit 1
else
    echo "Tests succededed."
fi
rm *.class