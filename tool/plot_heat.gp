set terminal png
set output 'oneSide.png'
set xlabel "S'"
set ylabel "L_D"
set pm3d 
set pm3d map
set ticslevel 0
set cbrange[0:2]
set palette defined ( 0 "green",1 "yellow", 2 "red")
set title "The number of one-way intaraction per agents"
splot "OneSideGP" notitle  with pm3d
