mkdir bin || true
mkdir result || true
javac src/entry/Entry.java -sourcepath src -d bin
java -cp bin entry.Entry
