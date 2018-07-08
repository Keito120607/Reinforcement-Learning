# drawHisto2.R
Sys.setlocale("LC_CTYPE","English")
args <- commandArgs(trailingOnly = T) # コマンドライン引数を読み込む



filename <- args[1] # 1番目の引数を入力ファイル名として代入する




data <- read.table(paste(filename,".txt", sep = ""), header=F, sep=",")
yoko <- 1:nrow(data)
#print()

png(paste(filename,".png", sep = ""),height=960, width=960, res=144)
plot(0, 0, type = "n", xlim = range(yoko), ylim = range(0:1), xlab = "Step", ylab = "Value")
for (i in 1:3) {
 lines(yoko,data[,i],col = i)
}
legend("topleft", legend = c("B","LC","LD"), lty = 1, col = 1:3)
#
dev.off()
