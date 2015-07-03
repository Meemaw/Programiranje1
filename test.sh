echo "--- TESTING ---"
for i in {1..10}
do
    echo "--- Testing assigment $i ---"
    echo "____________________________"
    (cd Assigment$i && ./test.sh)
done
