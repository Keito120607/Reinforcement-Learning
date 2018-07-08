# coding: UTF-8
import csv
import sys

arg = sys.argv
print arg


for num in range(1, len(arg)):
	filepath = arg[num]

	readnumber = 1

	try:
			file = open(filepath)

			lines = file.readlines()

			length = len(lines)

	finally:
			file.close()

	for i in range(length - readnumber, length):
			str =lines[i]
			sys.stdout.write(str) # ˆø”‚Ì•¶š—ñ‚ğƒtƒ@ƒCƒ‹‚É‘‚«‚Ş

input()
