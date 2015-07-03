#!/bin/bash -e

errors=0
for i in {1..50}
do

	(Echo "Test $i" && cd tests && javac Test$i.java && java Test$i)
done

if ((errors > 0))
then
    echo "Tests failed."
    exit 1
else
    echo "Tests succededed."
fi
(cd tests && rm *.class)
