mkdir bin || true
mkdir result || true
javac src/entry/Entry.java -sourcepath src -d bin -encoding UTF-8 
java -cp bin entry.Entry
