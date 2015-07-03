#!/bin/bash -e

javac code.java
errors=0
for i in {1..50}
do
	result=$(java code < tests/vhod$i.txt)
    expected=$(cat tests/izhod$i.txt)
    if [ "$result" == "$expected" ]
        then
            echo "Test $i correct."
    else
        echo "Test $i failed."
        ((errors+=1))
    fi
done

if ((errors > 0))
then
    echo "Tests failed."
    exit 1
else
    echo "Tests succededed."
fi
rm *.class