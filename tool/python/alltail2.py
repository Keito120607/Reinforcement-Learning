# coding: UTF-8
import csv
import sys
import glob


#arg = sys.argv
arg = glob.glob("./mean-*");

print arg


f=open("alltail.txt","w")

headprinted=False

for num in range(0, len(arg)):
	filepath = arg[num]

	readnumber = 1

	try:
			file = open(filepath)

			lines = file.readlines()

			length = len(lines)

	finally:
			file.close()

	if(not headprinted):
		headprinted=True
		f.write("#number,filename,"+lines[0].strip("#"))
	for i in range(length - readnumber, length):
			str2 =str(num)+","+filepath+","+lines[i]
			f.write(str2) # ˆø”‚Ì•¶š—ñ‚ğƒtƒ@ƒCƒ‹‚É‘‚«‚Ş

f.close()