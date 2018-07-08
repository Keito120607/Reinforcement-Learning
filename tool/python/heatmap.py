# coding: UTF-8
import csv
import sys

import re
import os
import os.path

from datetime import datetime





for_gnuplot=False
if(len(sys.argv)<2):
	print "need file path in argv"
	target=raw_input()
else:
	target=sys.argv[1]


colums=0

file = open(target)
lines=file.readlines()



label=["number","filename","B","LC","LD"]
#count colums
data=[]
for x in range(0,len(lines)):
	if(lines[x][0]=="#"):
		label=re.split(r',|\t',lines[x][1:-1].rstrip())
		continue
	data.append(re.split(r',|\t',lines[x].rstrip()))

colums=len(data[0])-1



for i in range(0,colums+1):
	if(i<len(label)):
		continue
	else:
		tmp=str(i)
	label.append(tmp)
print label


dirname="sample_excel"
if(not os.path.exists(dirname)):
	os.mkdir(dirname)

rows=10


for ind,name in enumerate(label):
	f=open(dirname+"/"+name+".txt","w+")
	for i in range(0,rows):

		tmp=[]
		for j in range(0,rows):
			tmp.append(str(data[j+i*rows][ind]))
		#print " | ".join(tmp)
		f.write(",".join(tmp)+"\n")
	f.close()



dirname="sample_gnuplot"
if(not os.path.exists(dirname)):
	os.mkdir(dirname)


for ind,name in enumerate(label):
	f=open(dirname+"/"+name+".txt","w+")
	for i in range(0,rows):
		for j in range(0,rows):
			tmp=[]
			tmp.append(str(i))
			tmp.append(str(j))
			tmp.append(str(data[i+j*rows][ind]))
			f.write("\t".join(tmp)+"\n")
		f.write("\n")
	f.close()



raw_input()
