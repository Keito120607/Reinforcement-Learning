# coding: UTF-8

import csv
import glob

files = glob.glob('Result-*-*.txt')
games = set()
for file in files:
    games.add(file.split('-')[1])

print(games)

for game in games:
    files = glob.glob('Result-' + game + '-*.txt')
    result = {}

    storeflag = True
    head = []
    for file in files:
        spamReader = csv.reader(open(file, 'r'))
        i = 0
        headflag = True
        for row in spamReader:
            if (headflag and row[0][0] == "#"):
                headflag = False
                if (storeflag):
                    storeflag = False
                    head.append(row)
                continue
            if (i not in result):
                result[i] = {}
                for j in range(0, len(row)):
                    result[i][j] = 0.0
            for j in range(0, len(row)):
                result[i][j] += float(row[j])
            i += 1

    spamWriter = csv.writer(open('mean-' + game + '.txt', 'w'), lineterminator="\n")
    for v in head:
        spamWriter.writerow(v)
    for k, v in result.items():
        tmp = []
        for j in range(0, len(v)):
            tmp.append(v[j] / len(files))
        spamWriter.writerow(tmp)
