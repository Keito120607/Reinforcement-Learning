# coding: UTF-8

import csv
import glob

files = glob.glob('Result-*-*.txt')
games = set()
for file in files:
	games.add(file.split('-')[1])

print(games)

for game in games:
	result={}
	files = glob.glob('Result-'+game+'-*.txt')
	for file in files:
		spamReader = csv.reader(open(file, 'r'))
		i=0;
		for row in spamReader:
			if(i not in result):
				result[i]={0:0.0,1:0.0,2:0.0}
			for j in range(0,3):
				result[i][j]+=float(row[j])
			i+=1

	spamWriter = csv.writer(open('mean-'+game+'.txt', 'w'), lineterminator="\n")
	for k,v in result.items():
		tmp=[]
		for j in range(0,3):
			tmp.append(v[j]/len(files))
		spamWriter.writerow(tmp)